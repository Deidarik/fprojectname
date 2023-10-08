package functions;

import junit.framework.TestCase;

import static org.junit.jupiter.api.Assertions.*;

public class MockTabulatedFunctionTest extends TestCase {
    MockTabulatedFunction obj = new MockTabulatedFunction();

    public void testInterpolate() {
        assertEquals(0., obj.interpolate(1, 1, 0, 0, 1));
    }
    public void testApply(){
        assertEquals(1.0, obj.apply(0));
        assertEquals(0.0, obj.apply(1));
        assertEquals(-1.0, obj.apply(2));
        assertEquals(2.0, obj.apply(-1));
    }
}
