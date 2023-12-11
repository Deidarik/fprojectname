package ru.ssau.OOP_lab.functions.factory;

import ru.ssau.OOP_lab.functions.ArrayTabulatedFunction;
import ru.ssau.OOP_lab.functions.MathFunction;
import ru.ssau.OOP_lab.functions.TabulatedFunction;

import java.io.Serializable;

public class ArrayTabulatedFunctionFactory implements TabulatedFunctionFactory, Serializable {
    public ArrayTabulatedFunction create(double[] xValues,double[] yValues){
        return new ArrayTabulatedFunction(xValues,yValues);
    }

    @Override
    public ArrayTabulatedFunction create(MathFunction func, double xFrom, double xTo, int size) {
        return new ArrayTabulatedFunction(func, xFrom,xTo,size);
    }

}
