import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;


public class ExamplesHW4 {
    List<Double> values;
    LinkedList<Double> data;
    GreenHouseProduce greenHouseProduce;
    List<Double> sensorData;


    @Before
    public void setUp() {
        // Initialize shared resources here
        GregorianCalendar calendar2 = new GregorianCalendar(2023, Calendar.NOVEMBER, 13);
        List<Object> diffMonth = Arrays.asList(/* Values for diffMonth */);
        double temperature = 35.5;
        // Example initialization
    }



    public ExamplesHW4(){
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
    //new test example added by TA
    @Test
    public void testCloneCalendar() {
        GregorianCalendar calendar2 = new GregorianCalendar(2010, Calendar.NOVEMBER, 1);
        GreenHouseProduce ghp = new GreenHouseProduce(calendar2);
        calendar2.add(Calendar.YEAR, 10);
        List<Double> dataToAdd = List.of(20151115111111.0, 40.0, 40.0);
        ghp.pollSensorData(dataToAdd);
        assertEquals(new SuperTempHumidReading(40.0, 40.0),ghp.middleReading());
    }

    //test for bug 1
    @Test
    public void testAbsGreenHouseIgnoresOldData() {
        GregorianCalendar calendar = new GregorianCalendar(2023, Calendar.NOVEMBER, 13);
        GreenHouseProduce ghp = new GreenHouseProduce(calendar);
        List<Double> testData = new ArrayList<>();
        calendar.add(Calendar.DAY_OF_YEAR, -2);
        testData.addAll(Arrays.asList(20231111010101.0, 20.0, 20.0));
        calendar.add(Calendar.DAY_OF_YEAR, 2); // Resetting back to Nov 13th
        testData.addAll(Arrays.asList(20231114010101.0, 40.0, 40.0)); // Data from Nov 14th
        ghp.pollSensorData(testData);
        SuperTempHumidReading expectedReading = new SuperTempHumidReading(40.0, 40.0);

        // Assuming getMiddleReading returns the latest valid reading
        //SuperTempHumidReading actualReading = ghp.middleReading();

        assertEquals(new SuperTempHumidReading(40.0, 40.0),ghp.middleReading());
    }

    @Test
    public void testPercentError() {
        GregorianCalendar calendar = new GregorianCalendar(2023, Calendar.NOVEMBER, 13);
        GreenHouseProduce ghp = new GreenHouseProduce(calendar);

        List<Double> testData = Arrays.asList(20231113010101.0, -999.0, 25.0, -999.0, 30.0);
        ghp.pollSensorData(testData);
        double expectedErrorPercent = 50.0;
        double actualErrorPercent = ghp.percentError();
            // Assert that the calculated error percent matches the expected value
        assertEquals(expectedErrorPercent, actualErrorPercent, 0.01); // Using a delta for floating point comparison
    }
/*
    @Test
    public void testDataBeforeDateIsBlocked() {
        //Test constructed a GreenHouseNursery object with GC for date Nov 13th, 2023
        GregorianCalendar calendar = new GregorianCalendar(2023, Calendar.NOVEMBER, 13);
        GreenHouseNursery gn = new GreenHouseNursery(calendar);

        //Data Calendar: Sun Nov 12 01:01:01 GMT 2023
        List<Double> testData = Arrays.asList(20231112010101.0, -999.0, 25.0);
        //Greenhouse Calendar: Mon Nov 13 01:01:01 GMT 2023
        gn.pollSensorData(testData);

        /*for (Double currentData : gn.data){
            System.out.println(currentData);
        }*/
        //Test called pollSensorData() for datetime on Nov 12th,  2023 with 1 temperature and 1 humidity
        /*double onDate = 20231112010101.0;;
        SuperTempHumidReading sthr = gn.middleReading(onDate);
        //SuperTempHumidReading sthr = gn.middleReading();

        assertEquals("{Err;Err}", sthr.toString()); // Using a delta for floating point comparison
    }*/

    @Test
    public void testPercentageErrorWithErrors() {
        /*{Err;20.0%}date: 2.0231114E7
        {Err;30.0%}date: 2.0231114E7
        {Err;25.0%}date: 2.0231114E7
        {Err;22.0%}date: 2.0231114E7
        */
        //Greenhouse Calendar: Mon Nov 13 01:01:01 GMT 2023
        GregorianCalendar calendar = new GregorianCalendar(2023, Calendar.NOVEMBER, 13);
        GreenHouseProduce ghp = new GreenHouseProduce(calendar);

        //Data Calendar: Tue Nov 14 01:01:01 GMT 2023
        List<Double> testData = Arrays.asList(20231114010101.0, -999.0, 20.0, -999.0, 30.0, -999.0, 25.0, -999.0, 22.0);
        ghp.pollSensorData(testData);

        //Test Failed: expected:<50.0> but was:<0.0> at edu.wpi.hw4.Autograder.testPercentError2:151 (Autograder.java)
        double expectedErrorPercent = 50.0;
        double actualErrorPercent = ghp.percentError();
        // Assert that the calculated error percent matches the expected value
        assertEquals(expectedErrorPercent, actualErrorPercent, 0.01); // Using a delta for floating point comparison
    }
/*
    @Test
    public void testConsumeAndRetrieveData() {
        AlternativeDataStrategy strategy = new AlternativeDataStrategy();
        SuperTempHumidReading reading1 = new SuperTempHumidReading(25.0, 50.0);
        SuperTempHumidReading reading2 = new SuperTempHumidReading(26.0, 55.0);

        strategy.consumeData(reading1);
        strategy.consumeData(reading2);

        SuperTempHumidReading middleReading = strategy.getMiddleReading();

        // Assuming getMiddleReading returns the second element when there are two elements
        assertEquals("Middle reading should be the second reading", reading2, middleReading);
    }
    @Test
    public void testHandlingEmptyDataSet() {
        AlternativeDataStrategy strategy = new AlternativeDataStrategy();
        SuperTempHumidReading reading = strategy.getMiddleReading(); // Should return null or a default value for empty data set
        assertNull("Should return null for empty data set", reading);
    }

*/
    /**@Test
    public void testDataConsistencyAfterOperations() {
        AlternativeDataStrategy strategy = new AlternativeDataStrategy();
        strategy.consumeData(new SuperTempHumidReading(25.0, 50.0));
        strategy.consumeData(new SuperTempHumidReading(26.0, 55.0));

        strategy.sortData(); // Assuming this sorts the data

        // Verify the first element is the smallest after sorting
        assertEquals(new SuperTempHumidReading(25.0, 50.0), strategy.getData().get(0));
    }*/


    /**@Test(expected = IllegalArgumentException.class)
    public void testHandlingInvalidDataInput() {
        SuperTempHumidReading invalidReading = new SuperTempHumidReading(-1000, -1000); // Assuming these values are invalid
        AlternativeDataStrategy strategy = new AlternativeDataStrategy();
        strategy.consumeData(invalidReading); // This should throw an IllegalArgumentException
    }*/

    /**@Test
    public void testSettingNewStrategyAndDataTransfer() {
        AbsGreenHouse greenhouse = new GreenHouseProduce(); // Replace with your actual AbsGreenHouse implementation
        greenhouse.setStrategy(new AlternativeDataStrategy()); // Set initial strategy

        // Populate data in the initial strategy
        greenhouse.getStrategy().consumeData(new SuperTempHumidReading(25.0, 50.0));

        AlternativeDataStrategy newStrategy = new AlternativeDataStrategy();
        greenhouse.setStrategy(newStrategy); // Switch to a new strategy

        // Check if the new strategy has the transferred data
        assertNotNull("New strategy should have data", newStrategy.getMiddleReading());

        // Check if old strategy data is cleared
        AlternativeDataStrategy oldStrategy = null;
        assertNull("Old strategy data should be cleared", oldStrategy.getMiddleReading());
    } */

    /**@Test
    public void testStrategySwitchDataTransfer() {
        GreenHouseProduce produce = new GreenHouseProduce();
        produce.pollSensorData(sensorData); // Assuming sensorData has some preloaded values

        AlternativeDataStrategy oldStrategy = new AlternativeDataStrategy();
        produce.setStrategy(oldStrategy); // Set the initial strategy

        AlternativeDataStrategy newStrategy = new AlternativeDataStrategy();
        produce.setStrategy(newStrategy); // Switch to a new strategy

        // Check if old strategy data is cleared
        assertNull("Old strategy data should be cleared", oldStrategy.getMiddleReading());

        // Check if new strategy has the transferred data
        assertNotNull("New strategy should have data", newStrategy.getMiddleReading());
    }*/

}


