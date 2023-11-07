import java.util.List;

public class GreenHouseProduce extends AbsGreenHouse implements Sensible{

    public GreenHouseProduce(){

    }

    @Override
    public void pollSensorData(List<Double> values) {

    }

    @Override
    public TempHumidReading middleReading() {
        return null;
    }

    @Override
    public TempHumidReading middleReading(double onDate) {

        return null;
    }
}