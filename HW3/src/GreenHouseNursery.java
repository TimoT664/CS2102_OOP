import java.util.List;

public class GreenHouseNursery extends AbsGreenHouse implements Sensible{

    public GreenHouseNursery(){

    }

    @Override
    public void pollSensorData(List<Double> values) {
        //TODO
        //pollSensorData() should be fast
    }

    @Override
    public TempHumidReading middleReading() {
        //TODO
        //middleReading() can perform most of the computation
        return null;
    }

    @Override
    public TempHumidReading middleReading(double onDate) {
        return null;
    }
}
