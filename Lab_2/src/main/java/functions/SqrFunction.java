package functions;

import static java.lang.Math.pow;

public class SqrFunction implements MathFunction {
    private double n;
    MathFunction func;
    SqrFunction(){
        this.n = 2d;
    }
    SqrFunction(MathFunction f,double n){
        this.n = n;
        this.func = f;
    }
    public ConstantFunction getN(){return (new ConstantFunction(n));}
    public MathFunction getFunc(){return func;}
    public double apply(double x)
    {

        return pow(func.apply(x), n);
    }   //Возводим в степень
}
