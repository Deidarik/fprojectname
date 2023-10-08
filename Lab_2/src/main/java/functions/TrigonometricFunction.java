package functions;
import java.lang.Math;
public class TrigonometricFunction implements MathFunction{

    MathFunction func;
    FunctionsType type;

    TrigonometricFunction(FunctionsType t,MathFunction f)
    {
        this.type = t;
        this.func = f;
    }
    public FunctionsType getType() {return type;}
    public MathFunction getFunc(){return func;}
    public double apply(double x)
    {
        return switch (type) {
            case SIN -> Math.sin(func.apply(x));
            case COS -> Math.cos(func.apply(x));
            case TG -> Math.sin(func.apply(x)) / Math.cos(func.apply(x));
            case CTG -> Math.cos(func.apply(x)) / Math.sin(func.apply(x));
            default -> 0;
        };
    }
}

