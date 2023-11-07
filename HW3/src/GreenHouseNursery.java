import java.util.List;

public class GreenHouseNursery extends AbsGreenHouse implements Sensible{

    public GreenHouseNursery(){

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
