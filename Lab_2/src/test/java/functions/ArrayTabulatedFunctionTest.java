package functions;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class ArrayTabulatedFunctionTest extends TestCase {
    double[] xValue = {1, 3, 5, 7, 9};
    double[] yValue = {2, 4, 6, 8, 10};
    double[] xVal = {0,1,2,3};
    double[] yVal = {0,1,2,3};

    MathFunction sqrFunction = new SqrFunction();
    ArrayTabulatedFunction test_to_obj_methods = new ArrayTabulatedFunction(xVal, yVal);

    ArrayTabulatedFunction ar = new ArrayTabulatedFunction(xValue, yValue);
    ArrayTabulatedFunction sar =  new ArrayTabulatedFunction(sqrFunction, 0, 100, 10);

    public void testFloorIndexOfX() {
        assertEquals(0, ar.floorIndexOfX(-2));
        assertEquals(1, ar.floorIndexOfX(4.9));
        assertEquals(1, ar.floorIndexOfX(5));
        assertEquals(2, ar.floorIndexOfX(6));
    }

    public void testExtrapolateLeft() {
        assertEquals(1.0, ar.extrapolateLeft(0));
        assertEquals(0.0, ar.extrapolateLeft(-1));
        assertEquals(-22.2, sar.extrapolateLeft(-2), 0.1);
    }

    public void testExtrapolateRight() {
        assertEquals(12., ar.extrapolateRight(11));
        assertEquals(10377.7, sar.extrapolateRight(102), 0.1);
    }

    public void testInterpolate() {
        assertEquals(3.25, ar.interpolate(2.25, ar.floorIndexOfX(2.25)));
        assertEquals(3., ar.interpolate(2., sar.floorIndexOfX(2.)));
    }

    public void testGetCount() {
        assertEquals(5, ar.getCount());
        assertEquals(10, sar.getCount());
    }

    public void testGetX() {
        assertEquals(1., ar.getX(0));
        assertEquals(5., ar.getX(2));
        assertEquals(0., sar.getX(0));
        assertEquals(33.3, sar.getX(3), 0.1);
    }

    public void testGetY() {
        assertEquals(2., ar.getY(0));
        assertEquals(6., ar.getY(2));
    }

    public void testSetY() {
        ar.setY(0, -12);
        assertEquals(-12., ar.getY(0));
    }

    public void testIndexOfX() {
        assertEquals(0,ar.indexOfX(1));
        assertEquals(-1,ar.indexOfX(10));
    }

    public void testIndexOfY() {
        assertEquals(1,ar.indexOfY(4.));
        assertEquals(-1,ar.indexOfY(3.1));
    }

    public void testLeftBound() {
        assertEquals(1., ar.leftBound());
        assertEquals(0., sar.leftBound());
    }

    public void testRightBound() {
        assertEquals(9., ar.rightBound());
        assertEquals(100.00000000000001, sar.rightBound());
    }

    public void testInsert(){
        ar.insert(11,12);
        assertEquals(5, ar.indexOfX(11) );
        ar.insert(6,7);
        assertEquals(6, ar.indexOfX(11) );
        ar.insert(0.1,0.2);
        assertEquals(0, ar.indexOfX(0.1) );

    }


    public void testRemove()
    {
        ar.remove(2);
        assertEquals(7., ar.getX(2));
    }


    public void toStringTest(){
     String inside_array = "| x = 0.0 y = 0.0 |\n| x = 1.0 y = 1.0 |\n| x = 2.0 y = 2.0 |\n| x = 3.0 y = 3.0 |\n";
     assertEquals(inside_array,test_to_obj_methods.toString() );
    }

    Object test_to_obj_methods_new = new ArrayTabulatedFunction(xVal, yVal);

    public void equalsTest()
    {
        assertFalse(test_to_obj_methods.equals(ar));
        assertTrue(test_to_obj_methods.equals(test_to_obj_methods));
        assertFalse(test_to_obj_methods.equals(sqrFunction));
        assertTrue(test_to_obj_methods.equals(test_to_obj_methods_new));
    }


    public void hashCodeTest()
    {
        int square_array_hash = sar.hashCode();
        int test_to_obj_methods_hash = test_to_obj_methods.hashCode();
        int test_to_obj_methods_new_hash = test_to_obj_methods_new.hashCode();
        assertEquals(test_to_obj_methods_hash, test_to_obj_methods_new_hash);
        assertFalse(square_array_hash ==test_to_obj_methods_hash );
    }

    public void cloneTest() throws CloneNotSupportedException
    {
    Object copied_sar = sar.clone();
    assertEquals(sar, copied_sar);
    Object copied_test_to_obj_methods = test_to_obj_methods.clone();
    assertEquals(test_to_obj_methods, copied_test_to_obj_methods);
    }

    public void testGetXException() throws IndexOutOfBoundsException {
        try{
            ar.getX(100);
            fail("Expected IndexOutOfBoundsException!");
        }catch (IndexOutOfBoundsException exception)
        {
            assertEquals("Index 100 out of bounds for length 5", exception.getMessage());
        }
    }
    public void testConstuctorException() throws IllegalArgumentException{
        try{
            ArrayTabulatedFunction test = new ArrayTabulatedFunction(new double[]{1},new double[]{0});
            fail("Expected IllegalArgumentException!");
        }catch (IllegalArgumentException exception)
        {
            assertEquals("Size of array less than 2!", exception.getMessage());
        }
    }
    public void testFloorIndexOfXException() throws IllegalArgumentException{
        try{
            ar.floorIndexOfX(-1);
            fail("Expected IllegalArgumentException!");
        }catch(IllegalArgumentException exception){
            assertEquals("Arg less than left bound of the array!", exception.getMessage());
        }
    }
    public void testRemoveException() throws IllegalArgumentException{
        try{
            ar.remove(ar.getCount()+1);
            fail("Expected IllegalArgumentException!");
        }catch(IllegalArgumentException exception){
            assertEquals("Element with index = 6 doesn't exist",exception.getMessage());
        }
    }
}
