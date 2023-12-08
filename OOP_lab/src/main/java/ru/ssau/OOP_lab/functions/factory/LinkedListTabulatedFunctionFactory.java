package ru.ssau.OOP_lab.functions.factory;

import ru.ssau.OOP_lab.functions.LinkedListTabulatedFunction;

public class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory{
    public LinkedListTabulatedFunction create(double[] xValues, double[] yValues){
        return new LinkedListTabulatedFunction(xValues, yValues);
    }
}
