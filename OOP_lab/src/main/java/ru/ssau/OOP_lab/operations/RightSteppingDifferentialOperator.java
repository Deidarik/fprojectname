package ru.ssau.OOP_lab.operations;

import ru.ssau.OOP_lab.functions.MathFunction;

public class RightSteppingDifferentialOperator extends SteppingDifferentialOperator {

    public RightSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        return new MathFunction() {
            @Override
            public double apply(double x) {
                return (function.apply(x + getStep()) - function.apply(x)) / getStep();
            }
        };
    }

    @Override
    public double apply(double x) {
        return 0;
    }
}
