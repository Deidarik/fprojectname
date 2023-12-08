package ru.ssau.OOP_lab.functions;

import org.junit.jupiter.api.Test;
import ru.ssau.OOP_lab.functions.AcosPlusAsin;
import ru.ssau.OOP_lab.functions.MathFunction;

import static org.junit.jupiter.api.Assertions.*;

class AcosPlusAsinTest{
MathFunction d = new AcosPlusAsin();
@Test
public void testEquals()
{
    assertEquals(Math.PI/2, d.apply(0));
}
}