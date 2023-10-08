package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegralFunctionTest {

    @Test
    void apply() {
        InverseTrigonometricFunction asin = new InverseTrigonometricFunction(FunctionsType.ASIN, new IdentityFunction());
        InverseTrigonometricFunction acos = new InverseTrigonometricFunction(FunctionsType.ACOS, new IdentityFunction());
        IntegralFunction integ = new IntegralFunction(acos);
        double expected = 0.6575733718138603;
        double actual = integ.apply(0.5);
        assertEquals(expected,actual);
        integ = new IntegralFunction(asin);
        expected = 0.12782479158358795;
        actual = integ.apply(0.5);
        assertEquals(expected,actual);

    }
}