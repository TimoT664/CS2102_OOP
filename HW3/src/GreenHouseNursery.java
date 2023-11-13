import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GreenHouseNursery extends AbsGreenHouse implements Sensible {
    private List<SuperTempHumidReading> sensorData = new ArrayList<>();

    @Override
    public void pollSensorData(List<Double> values) {
        for (int i = 0; i < values.size(); i += 3) {
            // Check if there are enough elements left in the list
            if (i + 2 >= values.size()) {
                break;
            }

            double dateTime = values.get(i);
            double temperature = values.get(i + 1);
            double humidity = values.get(i + 2);

            // Skip error values
            if (temperature != -999.0 && humidity != -999.0) {
                SuperTempHumidReading reading = new SuperTempHumidReading(temperature, humidity);
                sensorData.add(reading);
            }
        }
    }

    @Override
    public TempHumidReading middleReading() {
        return calculateMiddleReading(sensorData);
    }

    private TempHumidReading calculateMiddleReading(List<SuperTempHumidReading> sensorData) {
        List<Double> temperatures = sensorData.stream()
                .filter(reading -> reading.temperature != -999)
                .map(reading -> reading.temperature)
                .sorted()
                .collect(Collectors.toList());

        List<Double> humidities = sensorData.stream()
                .filter(reading -> reading.humidity != -999)
                .map(reading -> reading.humidity)
                .sorted()
                .collect(Collectors.toList());

        double middleTemperature = getMiddleValue(temperatures);
        double middleHumidity = getMiddleValue(humidities);

        TempHumidReading result = new SuperTempHumidReading(middleTemperature, middleHumidity);
        System.out.println("DEBUG: Returning " + result); // Debug print
        return result;
    }

    private double getMiddleValue(List<Double> values) {
        if (values.isEmpty()) return -999;
        int middleIndex = values.size() / 2;
        return values.get(middleIndex);
    }

    @Override
    public TempHumidReading middleReading(double onDate) {
        List<SuperTempHumidReading> filteredData = sensorData.stream()
                .filter(reading -> matchesDate(reading, onDate))
                .collect(Collectors.toList());

        if (filteredData.isEmpty()) {
            return new TempHumidReading(-999, -999); // Error values
        }

        return calculateMiddleReading(filteredData);
    }

    private boolean matchesDate(SuperTempHumidReading reading, double onDate) {
        // Implement the logic to check if the reading's date matches onDate
        // This depends on how the date is represented in your data
        return true; // Placeholder
    }

    // Additional methods for processing sensor data...
}