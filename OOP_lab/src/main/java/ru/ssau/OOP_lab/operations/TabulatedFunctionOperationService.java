package ru.ssau.OOP_lab.operations;

import ru.ssau.OOP_lab.exceptions.InconsistentFunctionsException;
import ru.ssau.OOP_lab.functions.Point;
import ru.ssau.OOP_lab.functions.TabulatedFunction;
import ru.ssau.OOP_lab.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.OOP_lab.functions.factory.TabulatedFunctionFactory;

import java.util.List;

public class TabulatedFunctionOperationService {
    private TabulatedFunctionFactory factory;

    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionOperationService() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        int size = tabulatedFunction.getCount();
        Point[] points = new Point[size];

        int i = 0;
        for (Point point : tabulatedFunction) {
            points[i] = point;
            i++;
        }

        return points;
    }
    public static double[][] listOfPointsAsMassive(List<Point> list){
        double[][] massive = new double[2][list.size()];
        int i = 0;
        for(Point pt: list){
            massive[0][i] = pt.getX();
            massive[1][i] = pt.getY();
            i++;
        }
        return massive;
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    private interface BiOperation{
        double apply(double u, double v);
    }

    private TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation) {
        int sizeA = a.getCount();
        int sizeB = b.getCount();

        if (sizeA != sizeB)
            throw new InconsistentFunctionsException("The functions have different numbers of points.");

        Point[] pointsA = asPoints(a);
        Point[] pointsB = asPoints(b);

        double[] xValues = new double[sizeA];
        double[] yValues = new double[sizeA];

        for (int i = 0; i < sizeA; i++) {
            xValues[i] = pointsA[i].getX();
            if (Math.abs(pointsA[i].getX() - pointsB[i].getX()) > 1e-9)
                throw new InconsistentFunctionsException("The functions have different x values.");

            yValues[i] = operation.apply(pointsA[i].getY(), pointsB[i].getY());
        }

        return factory.create(xValues, yValues);
    }

    public TabulatedFunction add(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (x, y) -> x + y);
    }

    public TabulatedFunction subtract(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (x, y) -> x - y);
    }

    public TabulatedFunction multiplication(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (x, y) -> x * y);
    }
    public TabulatedFunction division(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (x, y) -> x / y);
    }
}
