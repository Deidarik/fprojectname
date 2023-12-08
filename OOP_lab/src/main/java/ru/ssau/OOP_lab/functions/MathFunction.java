package ru.ssau.OOP_lab.functions;

public interface MathFunction {
       double apply(double x); //aaaaa
        default CompositeFunction andThen(MathFunction afterFunction)
       {
       CompositeFunction cf = new CompositeFunction(this, afterFunction);
           return cf;
       }
}
