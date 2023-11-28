import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

public class BasicDataStrategy implements ParsedDataStrategy {
    private List<SuperTempHumidReading> readings = new ArrayList<>();
    private List<Double> tempReadings = new ArrayList<>();
    private List<Double> humidityReadings = new ArrayList<>();
    private double filterDate;


       @Override
    public void consumeData(SuperTempHumidReading data) {
        // Add data to readings
        readings.add(data);
    }

    @Override
    public void storeData() {
        // Filter readings based on the date
        readings.removeIf(reading -> reading.getDate() < filterDate);

        // Sort readings, assuming you want to sort by date
        readings.sort(Comparator.comparing(SuperTempHumidReading::getDate));

        // Store temperature and humidity from the filtered and sorted readings
        for (SuperTempHumidReading newReading : readings) {
            tempReadings.add(newReading.getTemperature());
            humidityReadings.add(newReading.getHumidity());

       }
    }

    @Override
    public void sortData() {

    }

    /**
     * Sets the date from which readings should be considered.
     * @param filterDate The date in YYYYMMDDhhmmss.0 format.
     */
    public void setFilterDate(double filterDate) {
        this.filterDate = filterDate;
    }

    @Override
    public SuperTempHumidReading getMiddleReading() {
        // Calculate and return middle reading
        return null; // Placeholder
    }

    @Override
    public SuperTempHumidReading getMiddleReadingOnDate(double date) {
        // Find middle reading on specific date
        return null; // Placeholder
    }

    @Override
    public void switchStrategy(ParsedDataStrategy otherStrategy) {
        for (SuperTempHumidReading reading : readings) {
            otherStrategy.consumeData(reading);
        }
        readings.clear();
    }

    @Override
    public void clearData() {

    }

    @Override
    public void transferDataTo(ParsedDataStrategy otherStrategy) {

    }
}

