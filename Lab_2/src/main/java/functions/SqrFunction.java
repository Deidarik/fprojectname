package functions;

import static java.lang.Math.pow;

public class SqrFunction implements MathFunction {
    double x;
    public static double apply(double x)
    {
        return pow(x,2);
    }
}
