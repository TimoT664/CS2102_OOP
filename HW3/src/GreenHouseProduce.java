import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GreenHouseProduce extends AbsGreenHouse implements Sensible {

    //public int sensorDataSize;
    //public int numberOfReadings;
    private List<SuperTempHumidReading> sensorData = new ArrayList<>();
    //private SuperTempHumidReading cachedMiddleReading;

    // No-argument constructor
    public GreenHouseProduce() {
        // Initialization logic if required
    }
    @Override
    public void pollSensorData(List<Double> values) {
        for (int i = 0; i < values.size(); i++) {
            double currentValue = values.get(i);

            double dateTime = -1;
            double temperature = -999;
            double humidity = -999;
            if(isDate(currentValue)){
                // Check if there are enough elements left in the list
                if (i + 2 >= values.size()) {
                    break;
                }
                dateTime = currentValue;
                temperature = values.get(i + 1);
                humidity = values.get(i + 2);
                i = i +2;
            }
            else{
                if (i + 1 >= values.size()) {
                    break;
                }
                temperature = values.get(i);
                humidity = values.get(i + 1);
                i = i + 1;
            }
            //if (temperature != -999.0 && humidity != -999.0) {
            SuperTempHumidReading reading = new SuperTempHumidReading(temperature, humidity, toDate(dateTime));
            sensorData.add(reading);

            // Update cached middle reading after new data is polled
            //cachedMiddleReading = calculateMiddleReading(sensorData);
        }
        /*** ONLY FOR TESTING ***/
        int i = 0;
        for (SuperTempHumidReading sd : sensorData){
            System.out.println(sd.toString() + "date: " + sd.getDate());
            i++;
            if (i == 20 ){
                break;
            }
        }
        /*** END TESTING ***/
    }

    @Override
    public TempHumidReading middleReading() {
        /*if (cachedMiddleReading == null) {
            return new SuperTempHumidReading(-999, -999);
        }*/
        return calculateMiddleReading(sensorData);
    }

    @Override
    public SuperTempHumidReading middleReading(double onDate) {
        List<SuperTempHumidReading> filteredData = sensorData.stream()
                .filter(reading -> matchesDate(reading, onDate))
                .collect(Collectors.toList());

        if (filteredData.isEmpty()) {

            /*** REMOVE FOR CORRECT SOLUTION **/
            filteredData = sensorData.stream()
                    .filter(reading -> reading.getDate() == -1)
                    .collect(Collectors.toList());
            if(filteredData.isEmpty()){
                return new SuperTempHumidReading(-999, -999); // Error values
            }
            else{
                return filteredData.stream().max(Comparator.comparing(f -> f.temperature)).get();
            }
            /** END REMOCE**/

            //return new SuperTempHumidReading(-999, -999); // Error values
        }

        return calculateMiddleReading(filteredData);
    }
}