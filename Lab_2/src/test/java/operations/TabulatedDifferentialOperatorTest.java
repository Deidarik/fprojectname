package operations;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TabulatedDifferentialOperatorTest {

    double[] xValues = {1,2,3,4,5};
    double[] yValues = {9,18,27,36,45};
    double[] yDerValues = {9,9,9,9};


    TabulatedFunctionFactory tabulatedFunctionFactory = new LinkedListTabulatedFunctionFactory();
    TabulatedDifferentialOperator tabulatedDifferentialOperator = new TabulatedDifferentialOperator(tabulatedFunctionFactory);

    ArrayTabulatedFunction array = new ArrayTabulatedFunction(xValues, yValues);

    LinkedListTabulatedFunction lst = new LinkedListTabulatedFunction(xValues, yValues);
    @Test
    void derive() {
        LinkedListTabulatedFunction listTabulatedFunction2 = new LinkedListTabulatedFunction(xValues,yValues);
        TabulatedFunction listTabulatedFunction = tabulatedDifferentialOperator.derive(listTabulatedFunction2);
        for(int i = 0; yDerValues.length >i; i++)
            assertEquals(yDerValues[i],listTabulatedFunction.getY(i));

        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedFunction arrayTabulatedFunction1 = tabulatedDifferentialOperator.derive(arrayTabulatedFunction);
        for(int i = 0; yDerValues.length >i; i++)
            assertEquals(yDerValues[i],arrayTabulatedFunction1.getY(i));

    }

    @Test
    public void deriveTest1() {
        LinkedListTabulatedFunctionFactory fact = new LinkedListTabulatedFunctionFactory();
        TabulatedDifferentialOperator operation = new TabulatedDifferentialOperator(fact);
        TabulatedFunction function = operation.derive(lst);
        Assertions.assertEquals(9, function.getY(0));
        Assertions.assertEquals(9, function.getY(1));
        Assertions.assertEquals(9, function.getY(2));
        Assertions.assertEquals(9, function.getY(3));
    }

    @Test
    public void deriveTest2() {
        LinkedListTabulatedFunctionFactory fact = new LinkedListTabulatedFunctionFactory();
        TabulatedDifferentialOperator operation = new TabulatedDifferentialOperator(fact);
        TabulatedFunction function = operation.derive(array);
        Assertions.assertEquals(9, function.getY(0));
        Assertions.assertEquals(9, function.getY(1));
        Assertions.assertEquals(9, function.getY(2));
        Assertions.assertEquals(9, function.getY(3));
        Assertions.assertEquals(9, function.getY(4));
    }
}
