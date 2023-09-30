package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqrFunctionTest {

    @Test
    void apply() {
        assertAll(
                ()-> assertEquals(9, SqrFunction.apply(3)),
                ()-> assertEquals(9, SqrFunction.apply(-3)),
                ()-> assertEquals(0, SqrFunction.apply(0)));
    }
}