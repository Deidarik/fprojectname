package function;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentityFunctionTest {



    @Test
    void apply() throws Exception{
        IdentityFunction i = new IdentityFunction();
        assertAll(() -> assertEquals(2, IdentityFunction.apply(2)),
                () -> assertEquals(3.7,IdentityFunction.apply(3.7)),
                () -> assertEquals(9023.7,IdentityFunction.apply(9023.7)),
                () -> assertEquals(7.907,IdentityFunction.apply(7.907)));
    }
}