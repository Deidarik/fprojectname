package ru.ssau.OOP_lab.functions.factory;

import ru.ssau.OOP_lab.functions.ArrayTabulatedFunction;
import ru.ssau.OOP_lab.functions.MathFunction;
import ru.ssau.OOP_lab.functions.TabulatedFunction;

public class ArrayTabulatedFunctionFactory implements TabulatedFunctionFactory{
    public ArrayTabulatedFunction create(double[] xValues,double[] yValues){
        return new ArrayTabulatedFunction(xValues,yValues);
    }

    @Override
    public ArrayTabulatedFunction create(MathFunction func, double xFrom, double xTo, int size) {
        return new ArrayTabulatedFunction(func, xFrom,xTo,size);
    }

}
