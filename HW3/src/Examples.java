import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class Examples {

    List<Double> values;
    public Examples(){
        values = List.of(20231106010101.0,45.5,34.0,46.6,40.0,20231130020202.0,22.2,20.0,35.5,30.0,-999.0,31.0,32.2,-999.0);
    }




    //AbsGreenHouse.java: 0 out of 9 covered

    //GreenHouseNursery.java: 0 out of 7 covered
    //GreenHouseProduce.java: 0 out of 9 covered
    //SuperTempHumidReading.java: 0 out of 6 covered
 /*   @Test
    public void testSCorrectValues() {
        SuperTempHumidReading sthr =  new SuperTempHumidReading();

        sthr = new SuperTempHumidReading(98.6,33.4);
        assertEquals("{98.6F;33.4%}", sthr.toString());
    }*/
    @Test
    public void testSWrongTemperature() {
        SuperTempHumidReading sthr =  new SuperTempHumidReading();

        sthr = new SuperTempHumidReading(-999,33.4);
        assertEquals("{Err;33.4%}", sthr.toString());
    }
    @Test
    public void testWrongValues() {
        SuperTempHumidReading sthr =  new SuperTempHumidReading();

        sthr = new SuperTempHumidReading(-999,-999);
        assertEquals("{Err;Err}", sthr.toString());
    }
    @Test
    public void tesWrongHumidity() {
        SuperTempHumidReading sthr =  new SuperTempHumidReading();

        sthr = new SuperTempHumidReading(98.6,-999);
        assertEquals("{98.6F;Err}", sthr.toString());
    }

    @Test
    public void testDtoWrongHumidity() {
        TempHumidReading thr = new TempHumidReading(98.6,-999);
        SuperTempHumidReading sthr =  new SuperTempHumidReading(thr);

        assertEquals("{98.6F;Err}", sthr.toString());
    }
    @Test
    public void testDtoWrongTemperatur() {
        TempHumidReading thr = new TempHumidReading(-999,33.4);
        SuperTempHumidReading sthr =  new SuperTempHumidReading(thr);

        assertEquals("{Err;33.4%}", sthr.toString());
    }
    @Test
    public void testDtoWrongVals() {
        TempHumidReading thr = new TempHumidReading(-999,-999);
        SuperTempHumidReading sthr =  new SuperTempHumidReading(thr);

        assertEquals("{Err;Err}", sthr.toString());
    }
    @Test
    public void testEquals() {
        SuperTempHumidReading sthr =  new SuperTempHumidReading(12.2,13.3);
        SuperTempHumidReading sthr1 =  new SuperTempHumidReading(12.2,13.3);

        assertEquals(true, sthr.equals(sthr1));
    }
    //assertEquals(new SuperTempHumidReading(44.4,33.54), myGreenHouse.middleReading());

/*
    @Test(expected = NullPointerException.class)
    public void testSomethingElse() {
        BirdZone bz = new BirdZone(null);
        bz.pets.get(0);
    }
     */


}
