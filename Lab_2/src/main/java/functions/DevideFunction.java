package functions;

public class DevideFunction implements MathFunction{
    private MathFunction func1;
    private MathFunction func2;
    DevideFunction(MathFunction f1,MathFunction f2)
    {
        this.func1 = f1;
        this.func2 = f2;
    }
    public MathFunction getFunc1(){return func1;}
    public MathFunction getFunc2(){return func2;}
    public double apply(double x)
    {
        return (func1.apply(x) / func2.apply(x));
    }
}
