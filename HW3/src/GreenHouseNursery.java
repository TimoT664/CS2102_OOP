import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GreenHouseNursery extends AbsGreenHouse1 implements Sensible {
    private List<SuperTempHumidReading1> sensorData = new ArrayList<>();

    @Override
    public void pollSensorData(List<Double> values) {
        for (int i = 0; i < values.size(); i += 3) {
            double dateTime = values.get(i);
            double temperature = values.get(i + 1);
            double humidity = values.get(i + 2);

            // Skip error values
            if (temperature != -999.0 && humidity != -999.0) {
                SuperTempHumidReading1 reading = new SuperTempHumidReading1(temperature, humidity);
                sensorData.add(reading);
            }
        }
    }

    @Override
    public TempHumidReading middleReading() {
        return calculateMiddleReading(sensorData);
    }

    private TempHumidReading calculateMiddleReading(List<SuperTempHumidReading1> sensorData) {
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
        // Assuming onDate is used to filter data
        return null;
    }

    // Additional methods for processing sensor data...
}

interface Sensible1 {
    void pollSensorData(List<Double> values);
    TempHumidReading middleReading();
    TempHumidReading middleReading(double onDate);
}

class TempHumidReading1 {
    double temperature;
    double humidity;

    public void TempHumidReading(double temperature, double humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    // Other methods...
}

class SuperTempHumidReading1 extends TempHumidReading {
    public SuperTempHumidReading1(double temperature, double humidity) {
        super(temperature, humidity);
    }

    // Other methods...
}

abstract class AbsGreenHouse1 {
    // Abstract class definition...
}
