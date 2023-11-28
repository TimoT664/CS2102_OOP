import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

public abstract class AbsGreenHouse {
    private GregorianCalendar calendar;
    private ParsedDataStrategy strategy;
    private int numberOfErrors;

    // Constructors
    public AbsGreenHouse(GregorianCalendar calendar) {
        this.calendar = (GregorianCalendar) calendar.clone();
        this.strategy = null; // Not initializing with a default strategy
    }
    // Default constructor
    public AbsGreenHouse() {
        this.calendar = new GregorianCalendar();
        this.strategy = null; // Not initializing with a default strategy
    }


    // Getters
    protected ParsedDataStrategy getStrategy() {
        return this.strategy;
    }

    // Setters
    public void setStrategy(ParsedDataStrategy otherStrategy) {
        // Clear any data on the other strategy
        otherStrategy.clearData();

        // Transfer data from the current strategy to the other strategy
        if (this.strategy != null) {
            this.strategy.transferDataTo(otherStrategy);
        }

        // Set the current strategy field to the parameter otherStrategy
        this.strategy = otherStrategy;
    }

    // Sensor data processing
    public void pollSensorData(List<Double> sensorData) {
        for (Double data : sensorData) {
            if (isDataDateValid(data)) {
                SuperTempHumidReading reading = parseSensorData(data);
                strategy.consumeData(reading);
            } else {
                numberOfErrors++;
            }
        }
    }

    // Method to ignore data before a given GregorianCalendar date
    protected List<Double> ignoreDataBeforeGC(List<Double> data) {
        List<Double> newData = new LinkedList<>();
        boolean flag = false;
        for (Double d : data) {
            if (isDate(d) && isDataDateValid(d)) {
                newData.add(d);
                flag = true;
            } else if (flag) {
                newData.add(d);
            }
        }
        return newData;
    }

    // Method to switch strategy
    public void switchStrategy(ParsedDataStrategy newStrategy) {
        strategy.switchStrategy(newStrategy);
        strategy = newStrategy;
    }

    // Method to parse sensor data into a SuperTempHumidReading object
    private SuperTempHumidReading parseSensorData(Double data) {
        // Implement parsing logic here
        // For now, just a placeholder returning a new object
        return new SuperTempHumidReading(data, data);
    }

    // Method to check if the data date is valid
    private boolean isDataDateValid(Double dataDate) {
        GregorianCalendar dataCalendar = convertToCalendar(dataDate);
        return !dataCalendar.before(this.calendar);
    }

    // Utility methods
    private GregorianCalendar convertToCalendar(Double dataDate) {
        // Conversion logic here
        return null;
    }

    public boolean isDate(double sensorDatum) {
        return sensorDatum > 19700101.0;
    }

    public boolean isDateTime(double sensorDatum) {
        return sensorDatum > 19700101000000.0;
    }

    public double toDate(double dateTime) {
        return Math.floor(dateTime / 1000000.0);
    }

    public boolean sameDate(double date1, double date2) {
        return Math.abs(date1 - date2) < 0.001;
    }

    protected SuperTempHumidReading calculateMiddleReading(List<SuperTempHumidReading> sensorData) {
        // Calculation logic here
        return null;
    }

    protected double getMiddleValue(List<Double> values) {
        if (values.isEmpty()) return -999;
        int middleIndex = values.size() / 2;
        return values.get(middleIndex);
    }

    protected boolean matchesDate(SuperTempHumidReading reading, double onDate) {
        double readingDate = reading.getDate();
        return isDate(onDate) && readingDate == onDate;
    }

    // ... Any additional methods or inner classes ...
}
