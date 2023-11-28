import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AlternativeDataStrategy implements ParsedDataStrategy {
    private List<SuperTempHumidReading> data;

    public List<SuperTempHumidReading> getDdata(){
        return this.data;
    };

    public AlternativeDataStrategy() {
        this.data = new ArrayList<>();
    }

    @Override
    public void consumeData(SuperTempHumidReading data) {
        this.data.add(data); // Add the incoming data to the list
    }

    @Override
    public void storeData() {
        this.data.sort(Comparator.comparingDouble(SuperTempHumidReading::getTemperature));
    }

    @Override
    public void sortData() {
        // Example: Sorting by temperature (Modify as needed)
        this.data.sort(Comparator.comparingDouble(SuperTempHumidReading::getTemperature));
    }

    @Override
    public SuperTempHumidReading getMiddleReading() {
        if (this.data.isEmpty()) {
            return null; // Or handle empty list as required
        }
        return this.data.get(this.data.size() / 2); // Return middle element
    }

    @Override
    public SuperTempHumidReading getMiddleReadingOnDate(double date) {
        // Filter readings for the specific date and then find the middle reading
        List<SuperTempHumidReading> filteredData = this.data.stream()
                .filter(reading -> reading.getDate() == date) // Replace with correct date check
                .collect(Collectors.toList());
        if (filteredData.isEmpty()) {
            return null; // Or handle empty list as required
        }
        return filteredData.get(filteredData.size() / 2);
    }

    @Override
    public void switchStrategy(ParsedDataStrategy otherStrategy) {
        // This method might not be necessary here as mentioned earlier
    }

    @Override
    public void clearData() {
        this.data.clear();
    }

    @Override
    public void transferDataTo(ParsedDataStrategy other) {
        if (other instanceof AlternativeDataStrategy) {
            AlternativeDataStrategy otherStrategy = (AlternativeDataStrategy) other;
            otherStrategy.data.addAll(this.data);
            this.clearData(); // Clear data after transfer
        }
        // Handle other types of strategies if necessary
    }

    public List<Object> getData() {
        return null;
    }

    // Other methods and internal logic
}
