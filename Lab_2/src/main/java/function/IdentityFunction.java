package function;

public class IdentityFunction implements MathFunction{
    public static double apply(double x) {
        return x * 2 - x;
    }


}
