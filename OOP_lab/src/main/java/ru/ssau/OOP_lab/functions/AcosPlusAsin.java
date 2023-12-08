package ru.ssau.OOP_lab.functions;

public class AcosPlusAsin implements MathFunction {
    public double apply(double x) {
        return Math.asin(x)+Math.acos(x);
    }
}
