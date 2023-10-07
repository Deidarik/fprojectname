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
        MathFunction test2 = new AcosPlusAsin();
        MathFunction test = new cosDoubleArg();
        MathFunction CosTwoA = test.andThen(test2);
        MathFunction CosInSq = sf.andThen(test);
        double expected = 100;
        double actual = cf.apply(10);
        assertEquals(expected,actual);
        expected = 10000;
        actual = cf2.apply(10);
        assertEquals(expected,actual);
        expected = -1;
        actual = CosTwoA.apply(0);
        expected = 1;
        actual = CosInSq.apply(0);


    }
}