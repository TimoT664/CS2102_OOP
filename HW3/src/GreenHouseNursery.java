import java.util.ArrayList;
import java.util.List;

public class GreenHouseNursery extends AbsGreenHouse implements Sensible {

    private List<SuperTempHumidReading> sensorData = new ArrayList<>();

    @Override
    public void pollSensorData(List<Double> values) {
        for (int i = 0; i < values.size(); i++) {
            double value = values.get(i);

            // Check if the value is a datetime
            if (isDateTime(value)) {
                // Skip datetime value, expecting temperature and humidity pairs next
                continue;
            }

            // Check for valid temperature and humidity pairs
            if (value != -999.0 && i + 1 < values.size() && values.get(i + 1) != -999.0) {
                double temperature = value;
                double humidity = values.get(i + 1);
                SuperTempHumidReading reading = new SuperTempHumidReading(temperature, humidity);
                sensorData.add(reading);
                i++; // Skip next value as it's part of the current pair
            }
        }

        // Optionally process the data after adding new readings
        // processSensorData();
    }

    @Override
    public TempHumidReading middleReading() {
        // Logic to calculate middle reading
        // Ignore -999 values
        return calculateMiddleReading(sensorData);
    }

    private TempHumidReading calculateMiddleReading(List<SuperTempHumidReading> sensorData) {
        // Implement logic to calculate middle reading
        return null;
    }

    @Override
    public TempHumidReading middleReading(double onDate) {
        // Logic to calculate middle reading on a specific date
        // Ignore -999 values
        return calculateMiddleReadingOnDate(sensorData, onDate);
    }

    private TempHumidReading calculateMiddleReadingOnDate(List<SuperTempHumidReading> sensorData, double onDate) {
        // Implement logic to calculate middle reading on specific date
        return null;
    }

    /**
     * @param values
     */
    @Override
    public void pollSensorData1(List<Double> values) {

    }

    // Additional methods for processing sensor data...
}
