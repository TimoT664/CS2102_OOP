public class SuperTempHumidReading extends TempHumidReading{

    public SuperTempHumidReading(){
        super(-999, -999);
    }

    public SuperTempHumidReading(TempHumidReading tempHumidReadingDTO){
        super(tempHumidReadingDTO.temperature, tempHumidReadingDTO.humidity);
    }

    public SuperTempHumidReading(double temperature, double humidity){
        super(temperature, humidity);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //TODO
        /*
        -> "{98.6F;33.4%}"
        -> "{Err;33.4%}"
        -> "{Err;Err}"
        -> "{98.6F;Err}"
        use String.format() and the pattern "%,.1f" to turn a double into a 1 decimal place string
        use the pattern "%%" to put in a literal % sign
        Replace the temperature, e.g. "98.6F", with "Err" when the temperature is -999.0
        Replace the humidity, e.g. "33.4%" with "Err" when the humidity is -999.0
         */
        return String.format("%,.1f");
    }

}