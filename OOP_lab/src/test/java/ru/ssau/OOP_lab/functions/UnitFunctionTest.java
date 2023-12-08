package ru.ssau.OOP_lab.functions;

import org.junit.jupiter.api.Test;
import ru.ssau.OOP_lab.functions.UnitFunction;

import static org.junit.jupiter.api.Assertions.*;

class UnitFunctionTest {

    @Test
    void apply() {
        UnitFunction u = new UnitFunction();
        assertEquals(1, u.apply(1));
    }
}