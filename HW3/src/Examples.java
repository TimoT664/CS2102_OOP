
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;


public class Examples {
    List<Double> values;
    LinkedList<Double> data;
    GreenHouseProduce greenHouseProduce;
    List<Double> sensorData;

    public Examples() {
        data = new LinkedList<>();
        data.add(20231106010101.0);
        for (int i = 0; i < 10000; i++) {
            data.add((double) (i % 50) * 2);

        }
        values = List.of(20231106010101.0, 45.5, 34.0, 46.6, 40.0, 20231130020202.0, 22.2, 20.0, 35.5, 30.0, -999.0, 31.0, 32.2, -999.0);

    }

    @Test
    public void testSCorrectValues() {
        SuperTempHumidReading sthr = new SuperTempHumidReading();

        sthr = new SuperTempHumidReading(98.6, 33.4);
        assertEquals("{98.6F;33.4%}", sthr.toString());
    }

    @Test
    public void testSWrongTemperature() {
        SuperTempHumidReading sthr = new SuperTempHumidReading();

        sthr = new SuperTempHumidReading(-999, 33.4);
        assertEquals("{Err;33.4%}", sthr.toString());
    }

    @Test
    public void testWrongValues() {
        SuperTempHumidReading sthr = new SuperTempHumidReading();

        sthr = new SuperTempHumidReading(-999, -999);
        assertEquals("{Err;Err}", sthr.toString());
    }

    @Test
    public void tesWrongHumidity() {
        SuperTempHumidReading sthr = new SuperTempHumidReading();

        sthr = new SuperTempHumidReading(98.6, -999);
        assertEquals("{98.6F;Err}", sthr.toString());
    }

    @Test
    public void testDtoWrongHumidity() {
        TempHumidReading thr = new TempHumidReading(98.6, -999);
        SuperTempHumidReading sthr = new SuperTempHumidReading(thr);

        assertEquals("{98.6F;Err}", sthr.toString());
    }

    @Test
    public void testDtoWrongTemperatur() {
        TempHumidReading thr = new TempHumidReading(-999, 33.4);
        SuperTempHumidReading sthr = new SuperTempHumidReading(thr);

        assertEquals("{Err;33.4%}", sthr.toString());
    }

    @Test
    public void testDtoWrongVals() {
        TempHumidReading thr = new TempHumidReading(-999, -999);
        SuperTempHumidReading sthr = new SuperTempHumidReading(thr);

        assertEquals("{Err;Err}", sthr.toString());
    }

    @Test
    public void testEquals() {
        SuperTempHumidReading sthr = new SuperTempHumidReading(12.2, 13.3);
        SuperTempHumidReading sthr1 = new SuperTempHumidReading(12.2, 13.3);

        assertEquals(true, sthr.equals(sthr1));
    }

    @Test
    public void testEqualsWithDifferentObject() {
        SuperTempHumidReading sthr = new SuperTempHumidReading(12.2, 13.3);
        String notATempHumidReading = "I am not a SuperTempHumidReading";

        assertFalse(sthr.equals(notATempHumidReading));
    }

    @Test
    public void testNotEquals() {
        SuperTempHumidReading sthr = new SuperTempHumidReading(12.2, 13.0);
        SuperTempHumidReading sthr1 = new SuperTempHumidReading(12.2, 13.3);

        assertEquals(false, sthr.equals(sthr1));
    }
    /**@Test public void testSuperTempHumidReadingToString(){

    assertEquals("{80.0F; 33.3%}",
    new SuperTempHumidReading(80.0,33.3).toString());
    }*/

    /**
     * @Test public void testDefaultReading1() {
     * SuperTempHumidReading sthr =  new SuperTempHumidReading();
     * <p>
     * assertEquals("{Err;Err}", sthr);
     * }
     */


    @Test
    public void testDefaultReading() {
        SuperTempHumidReading sthr = new SuperTempHumidReading();

        assertEquals("{Err;Err}", sthr.toString());
    }

    @Test
    public void testMediumReadingsNursery() {
        GreenHouseNursery green = new GreenHouseNursery();
        green.pollSensorData(List.of(20231106010101.0, 45.5, 34.0, 46.6, 40.0, 20231130020202.0, 22.2, 20.0, 35.5, 30.0, -999.0, 31.0, 32.2, -999.0));
        assertEquals(new SuperTempHumidReading(35.5, 31.0), green.middleReading());
    }

    @Test
    public void testTimingNurserySlowerThanProduce() {
        GreenHouseNursery nursery = new GreenHouseNursery();
        GreenHouseProduce produce = new GreenHouseProduce();

        long time1 = System.currentTimeMillis();
        nursery.pollSensorData(data);
        long time2 = System.currentTimeMillis();
        produce.pollSensorData(data);
        long time3 = System.currentTimeMillis();
        System.out.println(String.format("nursery.pollSensorData(data); : produce.pollSensorData(data) :: %s : %s", time2 - time1, time3 - time2));
        assertTrue(time2 - time1 < time3 - time2);
    }

    @Test
    public void testTimingProduceSlowerThanNursery() {
        GreenHouseNursery nursery = new GreenHouseNursery();
        GreenHouseProduce produce = new GreenHouseProduce();

        long time1 = System.currentTimeMillis();
        nursery.middleReading();
        long time2 = System.currentTimeMillis();
        produce.middleReading();
        long time3 = System.currentTimeMillis();
        System.out.println(String.format("nursery.middleReading() : produce.middleReading() :: %s : %s", time2 - time1, time3 - time2));
        assertFalse(time2 - time1 < time3 - time2);
    }


}



//assertEquals(new SuperTempHumidReading(44.4,33.54), myGreenHouse.middleReading());

/*
    @Test(expected = NullPointerException.class)
    public void testSomethingElse() {
        BirdZone bz = new BirdZone(null);
        bz.pets.get(0);
    }
     */



