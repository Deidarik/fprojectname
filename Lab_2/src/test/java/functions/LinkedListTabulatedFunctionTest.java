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
        assertEquals(0, lltb1.floorIndexOfX(1));
        assertEquals(0, lltb1.floorIndexOfX(-9));
        assertEquals(1, lltb1.floorIndexOfX(5.6));
        assertEquals(1, lltb2.floorIndexOfX(6));
    }

    @Test
    void extrapolateLeft() {
        assertEquals(0,lltb1.extrapolateLeft(0));
        assertEquals(30,lltb1.extrapolateLeft(3));
        assertEquals(-10,lltb1.extrapolateLeft(-1));
        assertEquals(6.958333333333332,lltb2.extrapolateLeft(2.3));

    }

    @Test
    void extrapolateRight() {
        assertEquals(0, lltb1.extrapolateLeft(0));
        assertEquals(-10.0, lltb1.extrapolateLeft(-1));
        assertEquals(11.083333333333334, lltb2.extrapolateLeft(3.2));
    }

    @Test
    void interpolate() {
        assertEquals(22.5, lltb1.interpolate(2.25, lltb1.floorIndexOfX(3)));
        assertEquals(12, lltb1.interpolate(1.2, lltb2.floorIndexOfX(1.3)));
        assertEquals(-21.141666666666673, lltb2.interpolate(2.2, lltb1.floorIndexOfX(9)));
        assertEquals(29.57499999999999, lltb2.interpolate(5.6, lltb2.floorIndexOfX(8.7)));
    }

    @Test
    void getCount() {
        assertEquals(6, lltb1.getCount());
        assertEquals(26, lltb2.getCount());
    }

    @Test
    void getX() {
        assertEquals(1, lltb1.getX(0));
        assertEquals(8.75, lltb2.getX(3));
    }

    @Test
    void getY() {
        assertEquals(13.0, lltb1.getX(4));
        assertEquals(1, lltb2.getX(13));
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