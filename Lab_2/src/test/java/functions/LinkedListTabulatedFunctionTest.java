package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTabulatedFunctionTest {

    double[] xValues = {1,5,7,9,13,20};
    double[] yValues = {10,50,70,90,130,200};

    SqrFunction sf = new SqrFunction();

    LinkedListTabulatedFunction lltb1 = new LinkedListTabulatedFunction(xValues,yValues);
    LinkedListTabulatedFunction lltb2 = new LinkedListTabulatedFunction(sf,1,30,13);
    @Test
    void floorIndexOfX() {
        assertEquals(0, lltb1.floorIndexOfX(-2));
        assertEquals(1, lltb1.floorIndexOfX(4.9));
        assertEquals(1, lltb1.floorIndexOfX(5));
        assertEquals(2, lltb1.floorIndexOfX(6));
    }

    @Test
    void extrapolateLeft() {
        assertEquals(1,lltb1.extrapolateLeft(0));
    }

    @Test
    void extrapolateRight() {
    }

    @Test
    void interpolate() {
    }

    @Test
    void getCount() {
    }

    @Test
    void getX() {
    }

    @Test
    void getY() {
    }

    @Test
    void setY() {
    }

    @Test
    void indexOfX() {
    }

    @Test
    void indexOfY() {
    }

    @Test
    void leftBound() {
    }

    @Test
    void rightBound() {
    }
}