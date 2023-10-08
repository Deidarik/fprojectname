package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DifferentiateFunctionTest {

    @Test
    void apply() {

        IdentityFunction x = new IdentityFunction();
        DifferentiateFunction dc = new DifferentiateFunction(x);
        double actual = dc.apply(5);
        double expected = 1;
        assertEquals(expected,actual);
        InverseTrigonometricFunction itf = new InverseTrigonometricFunction(FunctionsType.ASIN, x);
        dc = new DifferentiateFunction(itf);
        actual = dc.apply(0.5);
        System.out.print(actual);
    }
}