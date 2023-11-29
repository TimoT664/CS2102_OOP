import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OldGreenHouseNursery extends OldAbsGreenHouse implements OldSensible {
    // List to store sensor data readings
    private List<OldSuperOldTempHumidReading> sensorData = new ArrayList<>();

    // Method to poll sensor data and process it
    @Override
    public void pollSensorData(List<Double> values) {
        double savedDateTime = -1; // Variable to store the date/time value

        // Loop through the list of values
        for (int i = 0; i < values.size(); i++) {
            double currentValue = values.get(i);

            double temperature = -999; // Variable to store temperature
            double humidity = -999; // Variable to store humidity

            // Check if the current value represents a date/time
            if (isDate(currentValue)) {
                // Check if there are enough elements left in the list
                if (i + 2 >= values.size()) {
                    break; // Break the loop if insufficient data is available
                }
                savedDateTime = currentValue; // Save the date/time value
                temperature = values.get(i + 1); // Get temperature value
                humidity = values.get(i + 2); // Get humidity value
                i = i + 2; // Increment index
            } else {
                if (i + 1 >= values.size()) {
                    break; // Break the loop if insufficient data is available
                }
                temperature = values.get(i); // Get temperature value
                humidity = values.get(i + 1); // Get humidity value
                i = i + 1; // Increment index
            }

            // Create a sensor reading object and add it to the sensorData list
            OldSuperOldTempHumidReading reading = new OldSuperOldTempHumidReading(temperature, humidity, toDate(savedDateTime));
            sensorData.add(reading);
        }

        // Code below is for testing purposes to print sensor data (can be removed in production)
        /*int i = 0;
        for (OldSuperOldTempHumidReading sd : sensorData){
            System.out.println(sd.toString() + "date: " + sd.getDate());
            i++;
            if (i == 30 ){
                break;
            }
        } */
        // End of testing code
    }

    // Method to calculate the middle reading of all sensor data
    @Override
    public OldTempHumidReading middleReading() {
        return calculateMiddleReading(sensorData);
    }

    // Method to calculate the middle reading based on a specified date
    @Override
    public OldSuperOldTempHumidReading middleReading(double onDate) {
        // Filter sensorData for readings matching the specified date
        List<OldSuperOldTempHumidReading> filteredData = sensorData.stream()
                .filter(reading -> matchesDate(reading, onDate))
                .collect(Collectors.toList());

        // If no data matches the date, return error values
        if (filteredData.isEmpty()) {
            return new OldSuperOldTempHumidReading(-999, -999);
        }

        // Calculate and return the middle reading for the filtered data
        return calculateMiddleReading(filteredData);
    }
}
