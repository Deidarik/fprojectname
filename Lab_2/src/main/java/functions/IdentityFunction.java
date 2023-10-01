package functions;

public class IdentityFunction implements MathFunction {
    double apply(double x)
    {
        return x*2 - x;
    }
}
