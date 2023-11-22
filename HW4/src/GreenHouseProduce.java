import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

public class GreenHouseProduce extends AbsGreenHouse implements Sensible, QualityControlable {

    private List<SuperTempHumidReading> sensorData = new ArrayList<>();
    private SuperTempHumidReading cachedMiddleReading;

    // No-argument constructor
    public GreenHouseProduce(GregorianCalendar calendar) {
        // Initialization logic if required
        super(calendar);
    }
    public GreenHouseProduce() {
        super();
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
            // Create SuperTempHumidReading object and add it to sensorData list
            SuperTempHumidReading reading = new SuperTempHumidReading(temperature, humidity, toDate(savedDateTime));
            sensorData.add(reading);

            // Update cached middle reading after new data is polled
            cachedMiddleReading = calculateMiddleReading(sensorData);
        }
    }

    @Override
    public TempHumidReading middleReading() {
        // Return the calculated middle reading based on all sensor data
        return calculateMiddleReading(sensorData);
    }

    @Override
    public SuperTempHumidReading middleReading(double onDate) {
        // Filter sensorData for readings matching the specified date
        List<SuperTempHumidReading> filteredData = sensorData.stream()
                .filter(reading -> matchesDate(reading, onDate))
                .collect(Collectors.toList());

        if (filteredData.isEmpty()) {
            return new SuperTempHumidReading(-999, -999); // Return error values if no data matches
        }

        // Calculate and return the middle reading for the filtered data
        return calculateMiddleReading(filteredData);
    }

    public SuperTempHumidReading getMiddleReading() {
        return null;
    }

    /**
     * computes the current percentage of non-datetime sensor values that are -999.0s
     *
     * @return a percent value between 0.0 and 100.0 inclusive
     */
    @Override
    public double percentError() {
        if (sensorData == null || sensorData.isEmpty()) {
            return 0.0; // If the list is empty or null, return 0%
        }

        int totalNonDatetimeValues = 0;
        int negativeValues = 0;

        for (SuperTempHumidReading reading : sensorData) {
            if (!isDateTime(reading.getDate())) {
                if (reading.temperature == -999.0) {
                    negativeValues++;
                }
                if(reading.humidity == -999.0){
                    negativeValues++;
                }
                totalNonDatetimeValues+=2;
            }
        }

        if (totalNonDatetimeValues == 0) {
            return 0.0; // To avoid division by zero
        }

        double percentage = ((double) negativeValues / totalNonDatetimeValues) * 100.0;
        return percentage;
    }
}
