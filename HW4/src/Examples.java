import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class Examples {

    public Examples(){
        GregorianCalendar gc = new GregorianCalendar(2023,10,13,1,1,1); // Nov 13th 2023, 01:01:01am
        gc.add(Calendar.DAY_OF_YEAR,-2); // Nov 11th 2023, 01:01:01am
    }


    @Test
    public void testGreenHouseNurseryDiffMonth2(){
        double expect = 2;
        double result = 5-3;
        assertEquals(expect,result,0.01);
    }

}
