import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

public class BasicDataStrategy implements ParsedDataStrategy {
    private List<SuperTempHumidReading> readings = new ArrayList<>();

    @Override
    public void consumeData(SuperTempHumidReading data) {
        // Add data to readings
        readings.add(data);
    }

    @Override
    public void storeData() {
        // Implement storing logic
    }


    @Override
    public void sortData() {
        // Sort the readings list
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
}

