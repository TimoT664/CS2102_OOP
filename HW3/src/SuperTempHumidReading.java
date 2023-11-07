public class SuperTempHumidReading extends TempHumidReading {

    public SuperTempHumidReading() {
        super(-999, -999);
    }

    public SuperTempHumidReading(TempHumidReading tempHumidReadingDTO) {
        super(tempHumidReadingDTO.temperature, tempHumidReadingDTO.humidity);
    }

    public SuperTempHumidReading(double temperature, double humidity) {
        super(temperature, humidity);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SuperTempHumidReading that = (SuperTempHumidReading) obj;
        return Math.abs(this.temperature - that.temperature) < 0.001 &&
                Math.abs(this.humidity - that.humidity) < 0.001;
    }

    @Override
    public String toString() {
        String temperatureString = this.temperature == -999 ? "Err" : String.format("%,.1fF", this.temperature);
        String humidityString = this.humidity == -999 ? "Err" : String.format("%,.1f%%", this.humidity);
        return String.format("{%s;%s}", temperatureString, humidityString);
    }
}
