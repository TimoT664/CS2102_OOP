import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Examples {
    IBinTree mt;
    MaxHeapValidator validator;

    @Before
    public void setUp() {
        mt = new EmptyBT();
        validator = new MaxHeapValidator();
    }

    @Test
    public void validateAddedContainsElt() {
        assertTrue("Valid addition should return true",
                validator.validAdd(mt, 3, new NodeBT(3, mt, mt)));
        assertFalse("Invalid addition should return false",
                validator.validAdd(mt, 3, new NodeBT(4, mt, mt)));
    }
}
