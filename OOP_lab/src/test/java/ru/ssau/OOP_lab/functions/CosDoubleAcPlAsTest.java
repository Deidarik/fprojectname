package ru.ssau.OOP_lab.functions;

import org.junit.jupiter.api.Test;
import ru.ssau.OOP_lab.functions.CosDoubleAcPlAs;
import ru.ssau.OOP_lab.functions.MathFunction;

import static org.junit.jupiter.api.Assertions.*;

class CosDoubleAcPlAsTest {
    MathFunction g = new CosDoubleAcPlAs();
@Test
    public void testEquals(){
    assertEquals(-1, g.apply(0));
    }

}