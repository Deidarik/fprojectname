package ru.ssau.OOP_lab.functions;

import org.junit.jupiter.api.Test;
import ru.ssau.OOP_lab.functions.CosDoubleArg;
import ru.ssau.OOP_lab.functions.MathFunction;

import static org.junit.jupiter.api.Assertions.*;

class CosDoubleArgTest {
MathFunction f = new CosDoubleArg();
@Test
    public void testEquals()
{
    assertEquals(-1, f.apply(Math.PI/2));
    assertEquals(1, f.apply(Math.PI*2));
}
}