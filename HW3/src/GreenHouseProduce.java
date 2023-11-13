import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GreenHouseProduce extends AbsGreenHouse implements Sensible {

    public int sensorDataSize;
    public int numberOfReadings;
    private List<SuperTempHumidReading> sensorData = new ArrayList<>();
    private TempHumidReading cachedMiddleReading;

    // No-argument constructor
    public GreenHouseProduce() {
        // Initialization logic if required
    }

    @Override
    public void pollSensorData(List<Double> values) {
        for (int i = 0; i < values.size(); i += 3) {
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

        // Update cached middle reading after new data is polled
        cachedMiddleReading = calculateMiddleReading(sensorData);
    }

    @Override
    public TempHumidReading middleReading() {
        if (cachedMiddleReading == null) {
            return new TempHumidReading(-999, -999);
        }
        return cachedMiddleReading;
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

        return new TempHumidReading(middleTemperature, middleHumidity);
    }

    private double getMiddleValue(List<Double> values) {
        if (values.isEmpty()) return -999;
        int middleIndex = values.size() / 2;
        return values.get(middleIndex);
    }

    @Override
    public TempHumidReading middleReading(double onDate) {
        // Implementation for date-specific middle reading
        return null;
    }

    // Additional methods for processing sensor data...
}

// Rest of the classes (Sensible1, TempHumidReading1, SuperTempHumidReading1, AbsGreenHouse1) remain the same