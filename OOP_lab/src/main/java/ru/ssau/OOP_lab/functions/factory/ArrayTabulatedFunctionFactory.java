package ru.ssau.OOP_lab.functions.factory;

import ru.ssau.OOP_lab.functions.ArrayTabulatedFunction;

public class ArrayTabulatedFunctionFactory implements TabulatedFunctionFactory{
    public ArrayTabulatedFunction create(double[] xValues,double[] yValues){
        return new ArrayTabulatedFunction(xValues,yValues);
    }
}
