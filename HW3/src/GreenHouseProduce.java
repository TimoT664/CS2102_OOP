import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class
GreenHouseProduce extends AbsGreenHouse implements Sensible {

    private List<TempHumidReading> readings;
    private TempHumidReading middleReading;

    public GreenHouseProduce() {
        // Initialize the readings list
        readings = new ArrayList<>();
    }

    @Override
    public void pollSensorData(List<Double> values) {
        // Perform the computation to parse and clean the data
        readings.clear(); // Clear previous readings
        List<Double> cleanedData = values.stream()
                .filter(value -> value != -999.0)
                .collect(Collectors.toList());

        // Assuming the cleanedData list is in the format: date, temp, humidity, temp, humidity, ...
        for (int i = 0; i < cleanedData.size(); i += 3) {
            double temperature = cleanedData.get(i + 1);
            double humidity = cleanedData.get(i + 2);
            readings.add(new TempHumidReading(temperature, humidity));
        }

        // Compute and store the middle reading for fast retrieval
        if (!readings.isEmpty()) {
            int middleIndex = readings.size() / 2;
            middleReading = readings.get(middleIndex);
        }
    }

    @Override
    public TempHumidReading middleReading() {
        // Return the precomputed middle reading
        return middleReading;
    }

    @Override
    public TempHumidReading middleReading(double onDate) {
        // This method would require additional logic to find the reading for a specific date
        // For now, it's not implemented as it requires more context on how the data is structured
        return null;
    }
}
