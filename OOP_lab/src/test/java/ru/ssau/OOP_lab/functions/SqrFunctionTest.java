package ru.ssau.OOP_lab.functions;

import org.junit.jupiter.api.Test;
import ru.ssau.OOP_lab.functions.SqrFunction;

import static org.junit.jupiter.api.Assertions.*;

class SqrFunctionTest {
    SqrFunction sf = new SqrFunction();
    @Test
    void apply() {
        assertAll(
                ()-> assertEquals(9, sf.apply(3)),
                ()-> assertEquals(9, sf.apply(-3)),
                ()-> assertEquals(0, sf.apply(0)));
    }
}