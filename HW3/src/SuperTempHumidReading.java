public class SuperTempHumidReading extends TempHumidReading {

    public SuperTempHumidReading() {
        super(-999, -999);
    }

    public SuperTempHumidReading(double temperature, double humidity) {
        super(temperature, humidity);
    }

    public SuperTempHumidReading(TempHumidReading other) {
        super(other.getTemperature(), other.getHumidity());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SuperTempHumidReading that = (SuperTempHumidReading) obj;
        return Math.abs(temperature - that.temperature) < 0.001 &&
                Math.abs(humidity - that.humidity) < 0.001;
    }

    @Override
    public String toString() {
        String tempStr = (temperature == -999) ? "Err" : String.format("%,.1fF", temperature);
        String humidStr = (humidity == -999) ? "Err" : String.format("%,.1f%%", humidity);
        return "{" + tempStr + ";" + humidStr + "}";
    }
}
