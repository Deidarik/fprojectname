package ru.ssau.OOP_lab.functions;

public class UnitFunction implements MathFunction{
    final private double x =1;
     double GetX()
    {
        return x;
    }
    public double apply(double x){
        return GetX();
    }
}
