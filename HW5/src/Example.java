import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;


public class Example {
    List<Double> values;

    @Before
    public void setUp() {
        // Initialize shared resources her
    }

    public Example(){

    }

    @Test
    public void testGreenHouseNurseryDiffMonth2(){
        double expect = 2;
        double result = 5-3;
        assertEquals(expect,result,0.01);
    }

    @Test
    public void testSCorrectValues() {
        assertEquals("{98.6F;33.4%}", "1234");
    }
}


