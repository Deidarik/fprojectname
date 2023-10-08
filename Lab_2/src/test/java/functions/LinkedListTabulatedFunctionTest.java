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
        assertEquals(130.0, lltb1.getY(4));
        assertEquals(1, lltb2.getY(13));
    }

    @Test
    void setY() {
        lltb1.setY(0, -12);
        assertEquals(-12., lltb1.getY(0));
        lltb2.setY(10, 100.890);
        assertEquals(100.890, lltb2.getY(10));
    }

    @Test
    void indexOfX() {
        assertEquals(4,lltb1.indexOfX(13));
        assertEquals(-1,lltb2.indexOfX(13));
    }

    @Test
    void indexOfY() {
        assertEquals(4,lltb1.indexOfY(13));
        assertEquals(-1,lltb2.indexOfY(13));
    }

    @Test
    void leftBound() {
        assertEquals(1., lltb1.leftBound());
        assertEquals(1., lltb2.leftBound());
    }

    @Test
    void rightBound() {
        assertEquals(20., lltb1.rightBound());
        assertEquals(31.999999999999993, lltb2.rightBound());
    }
}