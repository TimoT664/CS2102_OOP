import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class Examples {
    List<Double> values;
    public Examples(){
        values = List.of(20231106010101.0,45.5,34.0,46.6,40.0,20231130020202.0,22.2,20.0,35.5,30.0,-999.0,31.0,32.2,-999.0);
    }

    @Test
    public void testSomething(){
        assertEquals(1,1, 0.01);
    }
/*
    @Test(expected = NullPointerException.class)
    public void testSomethingElse() {
        BirdZone bz = new BirdZone(null);
        bz.pets.get(0);
    }
     */

    //2) Find the bug: GreenHouseNursery.pollSensorData() is slower than GreenHouseProduce.pollSensorData() (0/4)


    //3) Find The Bug: GreenHouseProduce.middleReading() is slower than GreenHouseNursery.middleReading() (0/4)

}
