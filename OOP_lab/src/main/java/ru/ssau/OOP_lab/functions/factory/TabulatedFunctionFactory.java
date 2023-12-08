package ru.ssau.OOP_lab.functions.factory;

import ru.ssau.OOP_lab.functions.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);
}
