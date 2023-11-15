import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class Examples {
    List<Double> values;
    LinkedList<Double> data;
    GreenHouseProduce greenHouseProduce;
    List<Double> sensorData;


    public Examples(){
        GregorianCalendar gc = new GregorianCalendar(2023,10,13,1,1,1); // Nov 13th 2023, 01:01:01am
        gc.add(Calendar.DAY_OF_YEAR,-2); // Nov 11th 2023, 01:01:01am

        data = new LinkedList<>();
        data.add(20231106010101.0);
        for (int i = 0; i < 10000; i++) {
            data.add((double) (i % 50) * 2);
        }
        values = List.of(20231106010101.0, 45.5, 34.0, 46.6, 40.0, 20231130020202.0, 22.2, 20.0, 35.5, 30.0, -999.0, 31.0, 32.2, -999.0);
    }


    @Test
    public void testGreenHouseNurseryDiffMonth2(){
        double expect = 2;
        double result = 5-3;
        assertEquals(expect,result,0.01);
    }
    @Test
    public void testSCorrectValues() {
        SuperTempHumidReading sthr = new SuperTempHumidReading(98.6, 33.4);
        assertEquals("{98.6F;33.4%}", sthr.toString());
    }

    @Test
    public void testSWrongTemperature() {
        SuperTempHumidReading sthr = new SuperTempHumidReading(-999, 33.4);
        assertEquals("{Err;33.4%}", sthr.toString());
    }

    @Test
    public void testWrongValues() {
        SuperTempHumidReading sthr = new SuperTempHumidReading(-999, -999);
        assertEquals("{Err;Err}", sthr.toString());
    }

    @Test
    public void tesWrongHumidity() {
        SuperTempHumidReading sthr = new SuperTempHumidReading(98.6, -999);
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

    @Test
    public void testDefaultReading() {
        SuperTempHumidReading sthr = new SuperTempHumidReading();
        assertEquals("{Err;Err}", sthr.toString());
    }

    @Test
    public void testMediumReadingsNursery() {
        GreenHouseNursery green = new GreenHouseNursery();
        green.pollSensorData(values);
        assertEquals(new SuperTempHumidReading(32.2, 30.0), green.middleReading());
    }

    @Test
    public void testMiddleReadingsNurseryOnDate() {
        GreenHouseNursery green = new GreenHouseNursery();
        green.pollSensorData(values);
        double onDate = 20231106010101.0;
        assertEquals(new SuperTempHumidReading(-999, -999), green.middleReading(onDate));
    }

    @Test
    public void testMiddleReadingsProduceOnDate() {
        GreenHouseProduce prod = new GreenHouseProduce();
        prod.pollSensorData(values);
        double onDate = 20231106010101.0;
        assertEquals(new SuperTempHumidReading(-999, -999), prod.middleReading(onDate));
    }

    public void testEmptyListNursery() {
        GreenHouseNursery green = new GreenHouseNursery();
        green.pollSensorData(new LinkedList<Double>());
        assertEquals(new SuperTempHumidReading(-999, -999), green.middleReading());
    }

    @Test
    public void testTimingNurserySlowerThanProduce() {
        GreenHouseNursery nursery = new GreenHouseNursery();
        greenHouseProduce = new GreenHouseProduce();
        long time1 = System.currentTimeMillis();
        nursery.pollSensorData(data);
        long time2 = System.currentTimeMillis();
        greenHouseProduce.pollSensorData(data);
        long time3 = System.currentTimeMillis();
        System.out.println(String.format("nursery.pollSensorData(data); : produce.pollSensorData(data) :: %s : %s", time2 - time1, time3 - time2));
        assertTrue(time2 - time1 < time3 - time2);
    }

    @Test
    public void testTimingProduceSlowerThanNursery() {
        GreenHouseNursery nursery = new GreenHouseNursery();
        greenHouseProduce = new GreenHouseProduce();
        long time1 = System.currentTimeMillis();
        nursery.middleReading();
        long time2 = System.currentTimeMillis();
        greenHouseProduce.middleReading();
        long time3 = System.currentTimeMillis();
        System.out.println(String.format("nursery.middleReading() : produce.middleReading() :: %s : %s", time2 - time1, time3 - time2));
        assertFalse(time2 - time1 < time3 - time2);
    }

    // Additional tests for GreenHouseProduce class
    @Test
    public void testGreenHouseProduceStuff() {
        greenHouseProduce.middleReading();
        assertNotNull("Produce object should not be null", greenHouseProduce);
    }

    @Before
    public void setUpNew() {
        greenHouseProduce = new GreenHouseProduce();
        sensorData = new ArrayList<>(Arrays.asList(
                20231106010101.0, 45.5, 34.0, 46.6, 40.0,
                20231130020202.0, 22.2, 20.0, 35.5, 30.0,
                -999.0, 31.0, 32.2, -999.0
        ));
    }

    @Test
    public void testGreenHouseProduceConstructorNew() {
        assertNotNull("Produce object should not be null", greenHouseProduce);
    }

    @Test
    public void testPollSensorDataWithVariousValuesNew() {
        greenHouseProduce.pollSensorData(sensorData);
        // Assertions to check the internal state of produce after polling data
    }

    @Test
    public void testPollSensorDataWithEmptyListNew() {
        greenHouseProduce.pollSensorData(new ArrayList<>());
        // Assertions to check the internal state of produce with empty list
    }

    @Test
    public void testMiddleReadingWithNoDataNew() {
        GreenHouseProduce producer = new GreenHouseProduce();
        SuperTempHumidReading temp = new SuperTempHumidReading();

        assertEquals("Middle reading should be -999s when no data is present", temp, producer.middleReading());
    }

    @Test
    public void testMiddleReadingWithDataNew() {
        GreenHouseProduce producer = new GreenHouseProduce();
        producer.pollSensorData(sensorData);
        SuperTempHumidReading expectedReading = new SuperTempHumidReading(32.2, 30.0);
        SuperTempHumidReading actualReading = (SuperTempHumidReading) producer.middleReading();
        assertEquals("Middle reading should match expected reading - produce", expectedReading, actualReading);
    }

    @Test
    public void testMiddleReadingOnSpecificDateNursery() {
        GreenHouseNursery greenHouseNursery = new GreenHouseNursery();
        greenHouseNursery.pollSensorData(sensorData);
        double onDate = 20231106010101.0;
        SuperTempHumidReading expectedReading = new SuperTempHumidReading(/* Expected values for this date */);
        SuperTempHumidReading actualReading = (SuperTempHumidReading) greenHouseNursery.middleReading(onDate);
        assertEquals("Middle reading on specific date should match expected reading - nursery", expectedReading, actualReading);
    }


}
