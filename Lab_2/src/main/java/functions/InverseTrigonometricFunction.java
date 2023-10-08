package functions;

public class InverseTrigonometricFunction implements MathFunction {

    private MathFunction func;
    private FunctionsType type;

    InverseTrigonometricFunction(FunctionsType t,MathFunction f)
    {
        this.type = t;
        this.func = f;
    }
    public FunctionsType getType()
    {
        return this.type;
    }
    public MathFunction getFunc(){return this.func;}
    public double apply(double x)
    {
        return switch (type) {
            case ASIN -> Math.asin(func.apply(x));
            case ACOS-> Math.acos(func.apply(x));
            case ATG -> Math.asin(func.apply(x)) / Math.acos(func.apply(x));
            case ACTG -> Math.acos(func.apply(x)) / Math.asin(func.apply(x));
            default -> 0;
        };
    }
}
