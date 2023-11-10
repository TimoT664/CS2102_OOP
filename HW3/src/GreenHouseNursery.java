import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GreenHouseNursery extends AbsGreenHouse implements Sensible {

    private List<Double> sensorData;

    public GreenHouseNursery() {
        // Initialize the sensor data list
        sensorData = new ArrayList<>();
    }

    @Override
    public void pollSensorData(List<Double> values) {
        // Quickly store the data, filtering out the error values (-999.0)
        sensorData = values.stream()
                .filter(value -> value != -999.0)
                .collect(Collectors.toList());
    }

    @Override
    public TempHumidReading middleReading() {
        // Perform the computation to find the middle reading
        // Assuming that the sensorData list is already sorted by date and time
        if (sensorData.isEmpty()) {
            return null; // or throw an exception if appropriate
        }

        // Find the middle index for temperature and humidity
        int middleIndex = sensorData.size() / 4; // Each reading has 2 values (temp and humidity), so we divide by 4
        double middleTemperature = sensorData.get(middleIndex * 2);
        double middleHumidity = sensorData.get(middleIndex * 2 + 1);

        return new TempHumidReading(middleTemperature, middleHumidity);
    }

    @Override
    public TempHumidReading middleReading(double onDate) {
        // Find the middle reading for a specific date
        // This method will require parsing the date and finding the corresponding readings
        // For now, it's not implemented as it requires more context on how the data is structured
        return null;
    }

    public int getSensorDataSize() {
        return 0;
    }

    public void clearSensorData() {
    }

    public int getErrorReadingsCount() {
        return 0;
    }

    public void addProduce(GreenHouseProduce produce) {
    }

    public int getProduceCount() {
        return 0;
    }

    public void removeProduce(GreenHouseProduce produce) {
    }

    public Object getAggregateData() {
        return null;
    }

    public SuperTempHumidReading getReadingOnDate(double specificDate) {
        return null;
    }
}
