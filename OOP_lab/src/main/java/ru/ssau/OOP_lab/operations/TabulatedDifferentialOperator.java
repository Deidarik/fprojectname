package ru.ssau.OOP_lab.operations;

import ru.ssau.OOP_lab.functions.Point;
import ru.ssau.OOP_lab.functions.TabulatedFunction;
import ru.ssau.OOP_lab.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.OOP_lab.functions.factory.TabulatedFunctionFactory;
import ru.ssau.OOP_lab.concurrent.SynchronizedTabulatedFunction;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction>{
    public TabulatedFunctionFactory factory;
    public TabulatedDifferentialOperator(TabulatedFunctionFactory f){
        factory = f;
    }
    public TabulatedFunctionFactory getFactory(){
        return factory;
    }

    public TabulatedDifferentialOperator() {

        this.factory = new ArrayTabulatedFunctionFactory();
    }
    public void setFactory(TabulatedFunctionFactory fact){
        factory = fact;
    }
    public TabulatedFunction derive(TabulatedFunction function){
        Point[] points = TabulatedFunctionOperationService.asPoints(function);
        int len = points.length;
        double[] xValues = new double[len];
        double[] yValues = new double[len];
        for(int i = 0; i < len-1; i++) {
            xValues[i] = points[i].x;
            yValues[i] = (points[i+1].y - points[i].y)/(points[i+1].x - points[i].x);
        }
        yValues[len - 1] = (points[len-1].y - points[len-2].y)/(points[len - 1].x - points[len - 2].x);
        xValues[len -1 ] = points[len-1].x;
        return factory.create(xValues,yValues);
    }

    public SynchronizedTabulatedFunction deriveSynchronously(TabulatedFunction function) {
        SynchronizedTabulatedFunction synchronizedFunction = (function instanceof SynchronizedTabulatedFunction) ?
                (SynchronizedTabulatedFunction) function :
                new SynchronizedTabulatedFunction(function);

        return synchronizedFunction.doSynchronously(func -> new SynchronizedTabulatedFunction(derive(func)));
    }

    public double apply(double x){
        return x;
    }
}
