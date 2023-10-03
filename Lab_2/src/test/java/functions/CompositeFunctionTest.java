package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompositeFunctionTest {

    @Test
    void apply() {
        IdentityFunction iF = new IdentityFunction();
        SqrFunction sf = new SqrFunction();
        CompositeFunction cf = new CompositeFunction(iF,sf);
        CompositeFunction cf2 =  new CompositeFunction(sf,cf);
        double expected = 100;
        double actual = cf.apply(10);
        assertEquals(expected,actual);
        expected = 10000;
        actual = cf2.apply(10);
        assertEquals(expected,actual);

    }
}