import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


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

    @Test
    public void testNotEquals() {
        SuperTempHumidReading sthr =  new SuperTempHumidReading(12.2,13.0);
        SuperTempHumidReading sthr1 =  new SuperTempHumidReading(12.2,13.3);

        assertEquals(false, sthr.equals(sthr1));
    }

    @Test
    public void testDefaultReading() {
        SuperTempHumidReading sthr =  new SuperTempHumidReading();

        assertEquals("{Err;Err}", sthr.toString());
    }

    @Test
    public void testMediumReadingsNursery() {
        GreenHouseNursery green = new GreenHouseNursery();
        green.pollSensorData(List.of(20231106010101.0,45.5,34.0,46.6,40.0,20231130020202.0,22.2,20.0,35.5,30.0,-999.0,31.0,32.2,-999.0));
        assertEquals(new SuperTempHumidReading(35.5,31.0), green.middleReading());
    }



    public class GreenHouseProduceTest {

        private GreenHouseProduce greenHouseProduce;
        private List<Double> sensorData;

        @Before
        public void setUp() {
            greenHouseProduce = new GreenHouseProduce();
            sensorData = List.of(
                    20231106010101.0, 45.5, 34.0, 46.6, 40.0,
                    20231130020202.0, 22.2, 20.0, 35.5, 30.0,
                    -999.0, 31.0, 32.2, -999.0
            );
            greenHouseProduce.pollSensorData(sensorData);
        }

        @Test
        public void testMiddleReadingProduce() {
            // Assuming that the middle reading has already been calculated in pollSensorData
            // and that middleReading() will be a fast retrieval
            long startTime = System.currentTimeMillis();
            TempHumidReading middleReading = greenHouseProduce.middleReading();
            long endTime = System.currentTimeMillis();

            // Check that the middle reading is correct
            assertEquals(new SuperTempHumidReading(35.5, 31.0), middleReading);

            // Check that the retrieval was fast
            assertTrue("middleReading should be fast", endTime - startTime < 10);
        }

        @Test
        public void testPollSensorDataPerformance() {
            // This test is to ensure that pollSensorData is doing the heavy lifting
            // and that it takes longer than middleReading, which should be a simple lookup
            GreenHouseProduce greenHouseProduceForTest = new GreenHouseProduce();

            long startTimePoll = System.currentTimeMillis();
            greenHouseProduceForTest.pollSensorData(sensorData);
            long endTimePoll = System.currentTimeMillis();

            long startTimeMiddle = System.currentTimeMillis();
            greenHouseProduceForTest.middleReading();
            long endTimeMiddle = System.currentTimeMillis();

            long pollDuration = endTimePoll - startTimePoll;
            long middleDuration = endTimeMiddle - startTimeMiddle;

            System.out.println(String.format("pollSensorData() : middleReading() :: %s : %s", pollDuration, middleDuration));

            // Assert that pollSensorData takes more time than middleReading
            assertTrue("pollSensorData should do most of the computation and take longer", pollDuration > middleDuration);
        }

        // can add more tests for other methods like middleReading(double onDate) here
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
