import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An abstract superclass to provide template methods for performance specific subclasses.
 */
public abstract class AbsGreenHouse {

    // GIVEN CODE
    /**
     * Assume a sensor value is a date if it is greater jan 01, 1970
     * @param sensorDatum the datum which may be a date, datetime, temperature, or humidity
     * @return true if it is a formatted date number
     */
    public boolean isDate(double sensorDatum){
        return sensorDatum > 19700101.0;
    }

    /**
     * Assume a sensor value is a date if it is greater jan 01, 1970 00:00:00 represented as a double
     * @param sensorDatum the datum which may be a date, datetime, temperature, or humidity
     * @return true if it is a formatted date number
     */
    public boolean isDateTime(double sensorDatum){
        return sensorDatum > 19700101000000.0;
    }

    /**
     * Converts the double date time format to just the date part by dividing and rounding
     * @param dateTime YYYYMMDDhhmmss.0
     * @return YYYYMMDD.0
     */
    public double toDate(double dateTime){
        return Math.floor(dateTime / 1000000.0); // convert YYYYMMDDhhmmss -> YYYYMMDD
    }

    /**
     * compares two YYYYMMDD.0 for equality
     * @param date1 one YYYYMMDD.0
     * @param date2 another YYYYMMDD.0
     * @return true if they are within some error tolerance (0.001) of each other
     */
    public boolean sameDate(double date1, double date2){
        return Math.abs(date1 - date2) < 0.001;
    }


    protected SuperTempHumidReading calculateMiddleReading(List<SuperTempHumidReading> sensorData) {
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

        SuperTempHumidReading result = new SuperTempHumidReading(middleTemperature, middleHumidity);
        System.out.println("DEBUG: Returning " + result); // Debug print
        return result;
    }

    protected double getMiddleValue(List<Double> values) {
        if (values.isEmpty()) return -999;
        int middleIndex = values.size() / 2;
        return values.get(middleIndex);
    }

    protected boolean matchesDate(SuperTempHumidReading reading, double onDate) {
        // Implement the logic to check if the reading's date matches onDate
        // This depends on how the date is represented in your data
        double readingDate = reading.getDate();
        onDate = onDate/Math.pow(10, 6);
        onDate = Math.floor(onDate);
        if(readingDate == toDate(onDate)){
            return true;
        }else {
            return false;
        }
    }
}