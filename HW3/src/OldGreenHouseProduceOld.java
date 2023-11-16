import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OldGreenHouseProduceOld extends OldAbsGreenHouse implements OldSensible {

    private List<OldSuperOldTempHumidReading> sensorData = new ArrayList<>();
    private OldSuperOldTempHumidReading cachedMiddleReading;

    // No-argument constructor
    public OldGreenHouseProduceOld() {
        // Initialization logic if required
    }

    @Override
    public void pollSensorData(List<Double> values) {
        double savedDateTime = -1;
        for (int i = 0; i < values.size(); i++) {
            double currentValue = values.get(i);

            double temperature = -999;
            double humidity = -999;
            if(isDate(currentValue)){
                // Check if there are enough elements left in the list
                if (i + 2 >= values.size()) {
                    break;
                }
                savedDateTime = currentValue;
                temperature = values.get(i + 1);
                humidity = values.get(i + 2);
                i = i +2;
            }
            else{
                if (i + 1 >= values.size()) {
                    break;
                }
                temperature = values.get(i);
                humidity = values.get(i + 1);
                i = i + 1;
            }
            // Create OldSuperOldTempHumidReading object and add it to sensorData list
            OldSuperOldTempHumidReading reading = new OldSuperOldTempHumidReading(temperature, humidity, toDate(savedDateTime));
            sensorData.add(reading);

            // Update cached middle reading after new data is polled
            cachedMiddleReading = calculateMiddleReading(sensorData);
        }
    }

    @Override
    public OldTempHumidReading middleReading() {
        // Return the calculated middle reading based on all sensor data
        return calculateMiddleReading(sensorData);
    }

    @Override
    public OldSuperOldTempHumidReading middleReading(double onDate) {
        // Filter sensorData for readings matching the specified date
        List<OldSuperOldTempHumidReading> filteredData = sensorData.stream()
                .filter(reading -> matchesDate(reading, onDate))
                .collect(Collectors.toList());

        if (filteredData.isEmpty()) {
            return new OldSuperOldTempHumidReading(-999, -999); // Return error values if no data matches
        }

        // Calculate and return the middle reading for the filtered data
        return calculateMiddleReading(filteredData);
    }
}