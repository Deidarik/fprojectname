package concurrent;

import functions.LinkedListTabulatedFunction;
import functions.Point;
import functions.TabulatedFunction;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

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

}