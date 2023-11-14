import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class ExamplesHW3 {

    LinkedList<Double> sameMonth = new LinkedList<>();
    LinkedList<Double> diffMonth = new LinkedList<>();
    LinkedList<Double> multiTimes = new LinkedList<>();

    LinkedList<Double> longData = new LinkedList<>();
    public ExamplesHW3(){
        sameMonth.addAll(List.of(20231106010101.0,45.5,34.0,46.6,40.0,20231130020202.0,22.2,20.0,35.5,30.0,-999.0,31.0,32.2,-999.0));
        diffMonth.addAll(sameMonth);
        diffMonth.addAll(List.of(20231206010101.0,45.5,34.0,46.6,40.0,20231230020202.0,22.2,20.0,35.5,30.0,-999.0,31.0,32.2,-999.0));
        multiTimes.addAll(List.of(20231110010101.0,1.1,1.1,2.2,2.2,3.3,3.3,4.4,4.4,
                20231110020202.0,9.9,9.9,8.8,8.8,7.7,7.7,6.6,6.6,5.5,5.5));
    }


    @Test
    public void testGreenHouseNurseryDiffMonth2(){
        OldSensible ghn = new OldGreenHouseNursery();
        ghn.pollSensorData(diffMonth);
        assertEquals(new OldSuperOldTempHumidReading(32.2,30.0), ghn.middleReading(20231230.0));
    }

    @Test
    public void testGreenHouseProduceDiffMonth2(){
        OldSensible ghp = new OldGreenHouseProduceOld();
        ghp.pollSensorData(diffMonth);
        assertEquals(new OldSuperOldTempHumidReading(32.2,30.0), ghp.middleReading(20231230.0));
    }

    @Test
    public void nurseryMultipleDatetimesForSameDate(){
        OldSensible ghn = new OldGreenHouseNursery();
        ghn.pollSensorData(multiTimes);
        assertEquals(new OldSuperOldTempHumidReading(5.5,5.5),ghn.middleReading(20231110));
    }

    @Test
    public void produceMultipleDatetimesForSameDate(){
        OldSensible gh = new OldGreenHouseProduceOld();
        gh.pollSensorData(multiTimes);
        assertEquals(new OldSuperOldTempHumidReading(5.5,5.5),gh.middleReading(20231110));
    }

    /*
    @Test
    public void testDate() {
        OldGreenHouseNursery greenHouseNursery = new OldGreenHouseNursery();
        greenHouseNursery.pollSensorData(values);
        double onDate = 20231106010101.0;
        double date = greenHouseNursery.toDate(onDate);
        System.out.println(date);
        onDate = onDate/Math.pow(10, 6);
        onDate = Math.floor(onDate);
        System.out.println(onDate);
        //assertEquals("Middle reading on specific date should match expected reading - nursery", expectedReading, actualReading);
    }

    @Test
    public void testMiddleReadingOnMonthDateNurseryNoDate() {
        OldGreenHouseNursery greenHouseNursery = new OldGreenHouseNursery();
        greenHouseNursery.pollSensorData(values);
        double onDate = 20231106010101.0;
        OldSuperOldTempHumidReading expectedReading = new OldSuperOldTempHumidReading(36.4,31.0);
        OldSuperOldTempHumidReading actualReading = (OldSuperOldTempHumidReading) greenHouseNursery.middleReading();
        assertEquals("Middle reading on specific date should match expected reading - nursery", expectedReading, actualReading);
    }

    @Test
    public void testMiddleReadingOnMonthDateNurseryOnDate() {
        OldGreenHouseNursery greenHouseNursery = new OldGreenHouseNursery();
        greenHouseNursery.pollSensorData(values);
        double onDate = 20231106010101.0;
        OldSuperOldTempHumidReading expectedReading = new OldSuperOldTempHumidReading(45.0,34.0);
        OldSuperOldTempHumidReading actualReading = (OldSuperOldTempHumidReading) greenHouseNursery.middleReading(onDate);
        assertEquals("Middle reading on specific date should match expected reading - nursery", expectedReading, actualReading);
    }

    @Test
    public void testMiddleReadingOnMonthDateNurseryOnADateNotInList() {
        OldGreenHouseNursery greenHouseNursery = new OldGreenHouseNursery();
        greenHouseNursery.pollSensorData(values);
        double onDate = 20210106010101.0;
        OldSuperOldTempHumidReading expectedReading = new OldSuperOldTempHumidReading(45.0,34.0);
        OldSuperOldTempHumidReading actualReading = (OldSuperOldTempHumidReading) greenHouseNursery.middleReading(onDate);
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
        OldTempHumidReading thr = new OldTempHumidReading(98.6, -999);
        SuperTempHumidReading2 sthr = new SuperTempHumidReading2(thr);
        assertEquals("{98.6F;Err}", sthr.toString());
    }

    @Test
    public void testDtoWrongTemperatur() {
        OldTempHumidReading thr = new OldTempHumidReading(-999, 33.4);
        SuperTempHumidReading2 sthr = new SuperTempHumidReading2(thr);
        assertEquals("{Err;33.4%}", sthr.toString());
    }

    @Test
    public void testDtoWrongVals() {
        OldTempHumidReading thr = new OldTempHumidReading(-999, -999);
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
        String notATempHumidReading = "I am not a OldSuperOldTempHumidReading";
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
        OldGreenHouseNursery green = new OldGreenHouseNursery();
        green.pollSensorData(values);
        assertEquals(new SuperTempHumidReading2(35.5, 31.0), green.middleReading());
    }

    @Test
    public void testMiddleReadingsNurseryOnDate() {
        OldGreenHouseNursery green = new OldGreenHouseNursery();
        green.pollSensorData(values);
        double onDate = 20231106010101.0;
        assertEquals(new SuperTempHumidReading2(-999, -999), green.middleReading(onDate));
    }

    @Test
    public void testMiddleReadingsProduceOnDate() {
        OldGreenHouseProduceOld prod = new OldGreenHouseProduceOld();
        prod.pollSensorData(values);
        double onDate = 20231106010101.0;
        assertEquals(new SuperTempHumidReading2(-999, -999), prod.middleReading(onDate));
    }

    public void testEmptyListNursery() {
        OldGreenHouseNursery green = new OldGreenHouseNursery();
        green.pollSensorData(new LinkedList<Double>());
        assertEquals(new SuperTempHumidReading2(-999, -999), green.middleReading());
    }

    @Test
    public void testTimingNurserySlowerThanProduce() {
        OldGreenHouseNursery nursery = new OldGreenHouseNursery();
        greenHouseProduce = new OldGreenHouseProduceOld();
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
        OldGreenHouseNursery nursery = new OldGreenHouseNursery();
        greenHouseProduce = new OldGreenHouseProduceOld();
        long time1 = System.currentTimeMillis();
        nursery.middleReading();
        long time2 = System.currentTimeMillis();
        greenHouseProduce.middleReading();
        long time3 = System.currentTimeMillis();
        System.out.println(String.format("nursery.middleReading() : produce.middleReading() :: %s : %s", time2 - time1, time3 - time2));
        assertFalse(time2 - time1 < time3 - time2);
    }

    // Additional tests for OldGreenHouseProduceOld class
    @Test
    public void testGreenHouseProduceStuff() {
        greenHouseProduce.middleReading();
        assertNotNull("Produce object should not be null", greenHouseProduce);
    }

    @Before
    public void setUpNew() {
        greenHouseProduce = new OldGreenHouseProduceOld();
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
        OldGreenHouseProduceOld producer = new OldGreenHouseProduceOld();
        OldSuperOldTempHumidReading temp = new OldSuperOldTempHumidReading();

        assertEquals("Middle reading should be -999s when no data is present", temp, producer.middleReading());
    }*/
/*
    @Test
    public void testMiddleReadingWithDataNew() {
        OldGreenHouseProduceOld producer = new OldGreenHouseProduceOld();
        producer.pollSensorData(sensorData);
        SuperTempHumidReading2 expectedReading = new SuperTempHumidReading2(35.5, 31.0);
        SuperTempHumidReading2 actualReading = (SuperTempHumidReading2) producer.middleReading();
        assertEquals("Middle reading should match expected reading - produce", expectedReading, actualReading);
    }

    @Test
    public void testMiddleReadingOnSpecificDateNursery() {
        OldGreenHouseNursery greenHouseNursery = new OldGreenHouseNursery();
        greenHouseNursery.pollSensorData(values);
        System.out.println();
        double onDate = 20231130020.0;//20231106010101.0;
        OldSuperOldTempHumidReading expectedReading = new OldSuperOldTempHumidReading(45.5,34.0);
        OldSuperOldTempHumidReading actualReading = (OldSuperOldTempHumidReading) greenHouseNursery.middleReading(onDate);
        assertEquals("Middle reading on specific date should match expected reading - nursery", expectedReading, actualReading);
    }

    @Test
    public void testMiddleReadingOnMonthDateNursery() {
        OldGreenHouseNursery greenHouseNursery = new OldGreenHouseNursery();
        greenHouseNursery.pollSensorData(values);
        //double onDate = 20231106010101.0;
        //double date = greenHouseNursery.toDate(20231106010101.0);
        //double month = greenHouseNursery.toMonth(date);
        OldSuperOldTempHumidReading expectedReading = new OldSuperOldTempHumidReading(45.5,34.0);
        OldSuperOldTempHumidReading actualReading = (OldSuperOldTempHumidReading) greenHouseNursery.middleReading();
        //assertEquals( 11, month, 0.01);
        assertEquals("Middle reading on specific date should match expected reading - nursery", expectedReading, actualReading);
    }


    /**@Test
    public void testAddProduce() {
        OldGreenHouseNursery nursery = new OldGreenHouseNursery();
        OldGreenHouseProduceOld produce = new OldGreenHouseProduceOld();
        nursery.addProduce(produce);
        assertEquals("Produce should be added to nursery", 1, nursery.getProduceCount());
    } */

    /**@Test
    public void testRemoveProduce() {
        OldGreenHouseNursery nursery = new OldGreenHouseNursery();
        OldGreenHouseProduceOld produce = new OldGreenHouseProduceOld();
        nursery.addProduce(produce);
        nursery.removeProduce(produce);
        assertEquals("Produce should be removed from nursery", 0, nursery.getProduceCount());
    }*/
    /**@Test
    public void testNurseryWithNoProduce() {
        OldGreenHouseNursery nursery = new OldGreenHouseNursery();
        assertNull("No data should be returned when nursery has no produce", nursery.getAggregateData());
    }*/
   /** @Test
    public void testSpecificDateDataRetrieval() {
        OldGreenHouseNursery nursery = new OldGreenHouseNursery();
        OldGreenHouseProduceOld produce = new OldGreenHouseProduceOld();
        produce.pollSensorData(sensorData);
        nursery.addProduce(produce);
        double specificDate = 20231106010101.0;
        OldSuperOldTempHumidReading expectedReading = new OldSuperOldTempHumidReading(45.5, 34.0, 46.6, 40.0,)
        OldSuperOldTempHumidReading actualReading = nursery.getReadingOnDate(specificDate);
        assertEquals("Data on specific date should match expected values", expectedReading, actualReading);
    }*/

    // END NEW TESTS
}
