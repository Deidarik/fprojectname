package ru.ssau.OOP_lab.functions.factory;

import ru.ssau.OOP_lab.functions.LinkedListTabulatedFunction;
import ru.ssau.OOP_lab.functions.MathFunction;
import ru.ssau.OOP_lab.functions.TabulatedFunction;

import java.io.Serializable;

public class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory, Serializable {
    public LinkedListTabulatedFunction create(double[] xValues, double[] yValues){
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    @Override
    public LinkedListTabulatedFunction create(MathFunction func, double xFrom, double xTo, int size) {
        return new LinkedListTabulatedFunction(func, xFrom,xTo,size);
    }
}
