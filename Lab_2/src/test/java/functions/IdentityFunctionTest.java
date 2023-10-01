package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentityFunctionTest {

    IdentityFunction i = new IdentityFunction();
    @Test
    void apply() {
        assertAll(
                ()-> assertEquals(6.3290, i.apply(6.3290)),
                ()-> assertEquals(-4.9320d, i.apply(-4.9320d)),
                ()-> assertEquals(0, i.apply(0)));
    }
}