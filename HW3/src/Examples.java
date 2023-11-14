import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

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
       //values = List.of(20231106010101.0, 45.5, 34.0, 46.6, 40.0, 20231130020202.0, 22.2, 20.0, 35.5, 30.0, -999.0, 31.0, 32.2, -999.0);
        //values = List.of(20231106010101.0, 46.0, 34.0, 20231106010101.0, 44.0, 34.0, 46.6, 40.0, 20231130020202.0, 22.2, 20.0, 35.5, 30.0, -999.0, 31.0, 32.2, -999.0);
       values = List.of(45.5,34.0, 20231106010101.0,40.0,202.0, 20.0,35.5);
    }

    @Test
    public void testMiddleReadingOnMonthDateNurseryNoDate() {
        GreenHouseNursery greenHouseNursery = new GreenHouseNursery();
        greenHouseNursery.pollSensorData(values);
        double onDate = 20231106010101.0;
        SuperTempHumidReading expectedReading = new SuperTempHumidReading(36.4,31.0);
        SuperTempHumidReading actualReading = (SuperTempHumidReading) greenHouseNursery.middleReading();
        assertEquals("Middle reading on specific date should match expected reading - nursery", expectedReading, actualReading);
    }

    @Test
    public void testMiddleReadingOnMonthDateNurseryOnDate() {
        GreenHouseNursery greenHouseNursery = new GreenHouseNursery();
        greenHouseNursery.pollSensorData(values);
        double onDate = 20231106010101.0;
        SuperTempHumidReading expectedReading = new SuperTempHumidReading(45.0,34.0);
        SuperTempHumidReading actualReading = (SuperTempHumidReading) greenHouseNursery.middleReading(onDate);
        assertEquals("Middle reading on specific date should match expected reading - nursery", expectedReading, actualReading);
    }

    @Test
    public void testMiddleReadingOnMonthDateNurseryOnADateNotInList() {
        GreenHouseNursery greenHouseNursery = new GreenHouseNursery();
        greenHouseNursery.pollSensorData(values);
        double onDate = 20210106010101.0;
        SuperTempHumidReading expectedReading = new SuperTempHumidReading(45.0,34.0);
        SuperTempHumidReading actualReading = (SuperTempHumidReading) greenHouseNursery.middleReading(onDate);
        assertEquals("Middle reading on specific date should match expected reading - nursery", expectedReading, actualReading);
    }
/*
    @Test
    public void testSCorrectValues() {
        SuperTempHumidReading2 sthr = new SuperTempHumidReading2(98.6, 33.4);
        assertEquals("{98.6F;33.4%}", sthr.toString());
    }

    @Test
    public void testSWrongTemperature() {
        SuperTempHumidReading2 sthr = new SuperTempHumidReading2(-999, 33.4);
        assertEquals("{Err;33.4%}", sthr.toString());
    }

    @Test
    public void testWrongValues() {
        SuperTempHumidReading2 sthr = new SuperTempHumidReading2(-999, -999);
        assertEquals("{Err;Err}", sthr.toString());
    }

    @Test
    public void tesWrongHumidity() {
        SuperTempHumidReading2 sthr = new SuperTempHumidReading2(98.6, -999);
        assertEquals("{98.6F;Err}", sthr.toString());
    }

    @Test
    public void testDtoWrongHumidity() {
        TempHumidReading thr = new TempHumidReading(98.6, -999);
        SuperTempHumidReading2 sthr = new SuperTempHumidReading2(thr);
        assertEquals("{98.6F;Err}", sthr.toString());
    }

    @Test
    public void testDtoWrongTemperatur() {
        TempHumidReading thr = new TempHumidReading(-999, 33.4);
        SuperTempHumidReading2 sthr = new SuperTempHumidReading2(thr);
        assertEquals("{Err;33.4%}", sthr.toString());
    }

    @Test
    public void testDtoWrongVals() {
        TempHumidReading thr = new TempHumidReading(-999, -999);
        SuperTempHumidReading2 sthr = new SuperTempHumidReading2(thr);
        assertEquals("{Err;Err}", sthr.toString());
    }

    @Test
    public void testEquals() {
        SuperTempHumidReading2 sthr = new SuperTempHumidReading2(12.2, 13.3);
        SuperTempHumidReading2 sthr1 = new SuperTempHumidReading2(12.2, 13.3);
        assertEquals(true, sthr.equals(sthr1));
    }

    @Test
    public void testEqualsWithDifferentObject() {
        SuperTempHumidReading2 sthr = new SuperTempHumidReading2(12.2, 13.3);
        String notATempHumidReading = "I am not a SuperTempHumidReading";
        assertFalse(sthr.equals(notATempHumidReading));
    }

    @Test
    public void testNotEquals() {
        SuperTempHumidReading2 sthr = new SuperTempHumidReading2(12.2, 13.0);
        SuperTempHumidReading2 sthr1 = new SuperTempHumidReading2(12.2, 13.3);
        assertEquals(false, sthr.equals(sthr1));
    }

    @Test
    public void testDefaultReading() {
        SuperTempHumidReading2 sthr = new SuperTempHumidReading2();
        assertEquals("{Err;Err}", sthr.toString());
    }

    @Test
    public void testMediumReadingsNursery() {
        GreenHouseNursery green = new GreenHouseNursery();
        green.pollSensorData(values);
        assertEquals(new SuperTempHumidReading2(35.5, 31.0), green.middleReading());
    }

    @Test
    public void testMiddleReadingsNurseryOnDate() {
        GreenHouseNursery green = new GreenHouseNursery();
        green.pollSensorData(values);
        double onDate = 20231106010101.0;
        assertEquals(new SuperTempHumidReading2(-999, -999), green.middleReading(onDate));
    }

    @Test
    public void testMiddleReadingsProduceOnDate() {
        GreenHouseProduce prod = new GreenHouseProduce();
        prod.pollSensorData(values);
        double onDate = 20231106010101.0;
        assertEquals(new SuperTempHumidReading2(-999, -999), prod.middleReading(onDate));
    }

    public void testEmptyListNursery() {
        GreenHouseNursery green = new GreenHouseNursery();
        green.pollSensorData(new LinkedList<Double>());
        assertEquals(new SuperTempHumidReading2(-999, -999), green.middleReading());
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
    }*/
/*
    @Test
    public void testMiddleReadingWithDataNew() {
        GreenHouseProduce producer = new GreenHouseProduce();
        producer.pollSensorData(sensorData);
        SuperTempHumidReading2 expectedReading = new SuperTempHumidReading2(35.5, 31.0);
        SuperTempHumidReading2 actualReading = (SuperTempHumidReading2) producer.middleReading();
        assertEquals("Middle reading should match expected reading - produce", expectedReading, actualReading);
    }

    @Test
    public void testMiddleReadingOnSpecificDateNursery() {
        GreenHouseNursery greenHouseNursery = new GreenHouseNursery();
        greenHouseNursery.pollSensorData(values);
        System.out.println();
        double onDate = 20231130020.0;//20231106010101.0;
        SuperTempHumidReading expectedReading = new SuperTempHumidReading(45.5,34.0);
        SuperTempHumidReading actualReading = (SuperTempHumidReading) greenHouseNursery.middleReading(onDate);
        assertEquals("Middle reading on specific date should match expected reading - nursery", expectedReading, actualReading);
    }

    @Test
    public void testMiddleReadingOnMonthDateNursery() {
        GreenHouseNursery greenHouseNursery = new GreenHouseNursery();
        greenHouseNursery.pollSensorData(values);
        //double onDate = 20231106010101.0;
        //double date = greenHouseNursery.toDate(20231106010101.0);
        //double month = greenHouseNursery.toMonth(date);
        SuperTempHumidReading expectedReading = new SuperTempHumidReading(45.5,34.0);
        SuperTempHumidReading actualReading = (SuperTempHumidReading) greenHouseNursery.middleReading();
        //assertEquals( 11, month, 0.01);
        assertEquals("Middle reading on specific date should match expected reading - nursery", expectedReading, actualReading);
    }


    /**@Test
    public void testAddProduce() {
        GreenHouseNursery nursery = new GreenHouseNursery();
        GreenHouseProduce produce = new GreenHouseProduce();
        nursery.addProduce(produce);
        assertEquals("Produce should be added to nursery", 1, nursery.getProduceCount());
    } */

    /**@Test
    public void testRemoveProduce() {
        GreenHouseNursery nursery = new GreenHouseNursery();
        GreenHouseProduce produce = new GreenHouseProduce();
        nursery.addProduce(produce);
        nursery.removeProduce(produce);
        assertEquals("Produce should be removed from nursery", 0, nursery.getProduceCount());
    }*/
    /**@Test
    public void testNurseryWithNoProduce() {
        GreenHouseNursery nursery = new GreenHouseNursery();
        assertNull("No data should be returned when nursery has no produce", nursery.getAggregateData());
    }*/
   /** @Test
    public void testSpecificDateDataRetrieval() {
        GreenHouseNursery nursery = new GreenHouseNursery();
        GreenHouseProduce produce = new GreenHouseProduce();
        produce.pollSensorData(sensorData);
        nursery.addProduce(produce);
        double specificDate = 20231106010101.0;
        SuperTempHumidReading expectedReading = new SuperTempHumidReading(45.5, 34.0, 46.6, 40.0,)
        SuperTempHumidReading actualReading = nursery.getReadingOnDate(specificDate);
        assertEquals("Data on specific date should match expected values", expectedReading, actualReading);
    }*/

    // END NEW TESTS
}
