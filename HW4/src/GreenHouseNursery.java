import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GreenHouseNursery extends AbsGreenHouse implements Sensible, QualityControlable {

    public GreenHouseNursery() {
        super();
    }

    public LinkedList<Double> data = new LinkedList<>();


    public GreenHouseNursery(GregorianCalendar calendar) {
        super(calendar);
        //code
    }

    private List<SuperTempHumidReading> sensorData = new ArrayList<>();

    @Override
    public void pollSensorData(List<Double> values) {
        double savedDateTime = -1;
        for (int i = 0; i < values.size(); i++) {
            double currentValue = values.get(i);

            double temperature = -999;
            double humidity = -999;
            if (isDate(currentValue)) {
                // Check if there are enough elements left in the list
                if (i + 2 >= values.size()) {
                    break;
                }
                savedDateTime = currentValue;
                temperature = values.get(i + 1);
                humidity = values.get(i + 2);
                i = i + 2;
            } else {
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
        }

        // Code below is for testing purposes to print sensor data (can be removed in production)
        /*int i = 0;
        for (SuperTempHumidReading sd : sensorData){
            System.out.println(sd.toString() + "date: " + sd.getDate());
            i++;
            if (i == 30 ){
                break;
            }
        } */
        // End of testing code
    }

    @Override
    public TempHumidReading middleReading() {
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

    /**
     * computes the current percentage of non-datetime sensor values that are -999.0s
     *
     * @return a percent value between 0.0 and 100.0 inclusive
     */
    @Override
    public double percentError() {
        return 0;
    }
}
