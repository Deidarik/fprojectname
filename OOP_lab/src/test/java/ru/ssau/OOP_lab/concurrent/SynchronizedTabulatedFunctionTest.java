package ru.ssau.OOP_lab.concurrent;

import ru.ssau.OOP_lab.concurrent.SynchronizedTabulatedFunction;
import ru.ssau.OOP_lab.functions.ArrayTabulatedFunction;
import ru.ssau.OOP_lab.functions.LinkedListTabulatedFunction;
import ru.ssau.OOP_lab.functions.Point;
import ru.ssau.OOP_lab.functions.TabulatedFunction;
import ru.ssau.OOP_lab.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.OOP_lab.operations.TabulatedDifferentialOperator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SynchronizedTabulatedFunctionTest {

    double[] xValues = {1, 3, 5, 7, 9};
    double[] yValues = {2, 4, 6, 8, 10};
    TabulatedFunction func = new LinkedListTabulatedFunction(xValues,yValues);
    SynchronizedTabulatedFunction syncFunctionTest = new SynchronizedTabulatedFunction(func);
    @Test
    void getCount() {
        assertEquals(func.getCount(),syncFunctionTest.getCount());
    }

    @Test
    void getX() {
        assertEquals(func.getX(0),syncFunctionTest.getX(0));
        assertEquals(func.getX(4),syncFunctionTest.getX(4));
    }

    @Test
    void getY() {
        assertEquals(func.getY(0),syncFunctionTest.getY(0));
        assertEquals(func.getX(4),syncFunctionTest.getX(4));
    }

    @Test
    void setY() {
    }

    @Test
    void indexOfX() {
        assertEquals(func.indexOfX(3), syncFunctionTest.indexOfX(3));

    }

    @Test
    void indexOfY() {
        assertEquals(func.indexOfY(3), syncFunctionTest.indexOfY(3));
    }

    @Test
    void leftBound() {
        assertEquals(func.leftBound(), syncFunctionTest.leftBound());
    }

    @Test
    void rightBound() {
        assertEquals(func.rightBound(), syncFunctionTest.rightBound());
    }

    @Test
    void apply() {
    }

    @Test
    void iterator() {
        int i = 0;
        for (Point pt : syncFunctionTest) {
            assertEquals(pt.x, func.getX(i));
            assertEquals(pt.y, func.getY(i));
            i++;
        }
    }

    @Test
    public void doSynchronously() {
        SynchronizedTabulatedFunction.Operation<Double> operation = func -> {
            double sum = 0;
            for (Point ord : syncFunctionTest)
                sum += ord.y;
            return sum;
        };
        double sumOfY = syncFunctionTest.doSynchronously(operation);
        assertEquals(30.0, sumOfY);
    }

    @Test
    void deriveTest() {
        LinkedListTabulatedFunctionFactory fact = new LinkedListTabulatedFunctionFactory();
        TabulatedDifferentialOperator operation = new TabulatedDifferentialOperator(fact);
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedFunction differential_func = operation.derive(func);
        assertEquals(1, differential_func.getY(0));
        assertEquals(1, differential_func.getY(1));
        assertEquals(1, differential_func.getY(2));
        assertEquals(1, differential_func.getY(3));
    }

    @Test
    void deriveSynchronouslyTest() {
        LinkedListTabulatedFunctionFactory fact = new LinkedListTabulatedFunctionFactory();
        TabulatedDifferentialOperator operation = new TabulatedDifferentialOperator(fact);
        ArrayTabulatedFunction func = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedFunction differential_func = operation.deriveSynchronously(func);
        assertEquals(1, differential_func.getY(0));
        assertEquals(1, differential_func.getY(1));
        assertEquals(1, differential_func.getY(2));
        assertEquals(1, differential_func.getY(3));
    }

}