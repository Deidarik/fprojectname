package ru.ssau.OOP_lab.operations;

import ru.ssau.OOP_lab.functions.MathFunction;

public interface DifferentialOperator<T> extends MathFunction {
    T derive( T function);

}
