package functions;

public class DifferentiateFunction implements MathFunction {
    MathFunction func;
    MathFunction resFunc;
    DifferentiateFunction(MathFunction f){
        this.func =f;
        this.resFunc = differentiate(func);
    }
    public MathFunction getFunc(){return func;}
    public MathFunction getResult(){return resFunc;}
    public MathFunction differentiate(MathFunction mf)
    {
        MathFunction result = new ZeroFunction();
        MathFunction innerF;
        MathFunction f1,f2;
        double n = 1;
        switch(mf.typeOfFunc())
        {
            case CONST:
                result =  new ZeroFunction();
                break;
            case X:
                result = new ConstantFunction(1);
                break;
            case POWER:
                innerF = ((SqrFunction)mf).getFunc();
                n = (((SqrFunction)mf).getN()).getX();
                if (n == 2)
                    result = new MultiplyFunction(new ConstantFunction(2),new IdentityFunction());
                else
                    result =new MultiplyFunction( new MultiplyFunction(((SqrFunction)mf).getN(),new SqrFunction(innerF,n-1)) , differentiate(innerF));
                break;
            case COMP:
                result = new MultiplyFunction(new DifferentiateFunction(((CompositeFunction)mf).getOuter()),new DifferentiateFunction(((CompositeFunction)mf).getInner()));
                break;
            case SIN:
                innerF = ((TrigonometricFunction)mf).getFunc();
                result =  new MultiplyFunction(new TrigonometricFunction(FunctionsType.COS, innerF),differentiate(innerF));
                break;
            case COS:
                innerF = ((TrigonometricFunction)mf).getFunc();
                result = new MultiplyFunction(new MultiplyFunction(new ConstantFunction(-1),new TrigonometricFunction(FunctionsType.SIN, innerF)), differentiate(innerF));
                break;
            case ASIN:
                innerF = ((InverseTrigonometricFunction)mf).getFunc();
                result = new MultiplyFunction(new MultiplyFunction( new ConstantFunction(1),new SqrFunction(new SummationFunction(new ConstantFunction(1), new MultiplyFunction(new ConstantFunction(-1),new SqrFunction(innerF,2))), -0.5)) , differentiate(innerF));
                break;
            case ACOS:
                innerF = ((InverseTrigonometricFunction)mf).getFunc();
                result = new MultiplyFunction(new ConstantFunction(-1), new MultiplyFunction(new MultiplyFunction( new ConstantFunction(1),new SqrFunction(new SummationFunction(new ConstantFunction(1), new MultiplyFunction(new ConstantFunction(-1),new SqrFunction(innerF,2))), -0.5)) , differentiate(innerF)));
                break;
            case MULT:

                f1 = ((MultiplyFunction)mf).getFunc1();
                f2 = ((MultiplyFunction)mf).getFunc2();
                if (f1 instanceof ZeroFunction || f2 instanceof ZeroFunction)
                    result = new ZeroFunction();
                else if(f1 instanceof ConstantFunction && !(f2 instanceof ConstantFunction))
                    result = new MultiplyFunction(f1,differentiate(f2));
                else if (f2 instanceof ConstantFunction && !(f1 instanceof ConstantFunction))
                    result = new MultiplyFunction(differentiate(f1),f2);
                else if (f2 instanceof  ConstantFunction && f1 instanceof ConstantFunction)
                    result = new ZeroFunction();
                else
                    result = new SummationFunction(new MultiplyFunction(differentiate(f1),f2),new MultiplyFunction(f1,differentiate(f2)));
                break;
            case SUM:
                f1 = differentiate(((SummationFunction)mf).getFunc1());
                f2 = differentiate(((SummationFunction)mf).getFunc2());
                if (f1 instanceof ZeroFunction && !(f2 instanceof  ZeroFunction))
                    result = f2;
                else if (f2 instanceof ZeroFunction && !(f1 instanceof  ZeroFunction))
                    result = f1;
                else if (f1 instanceof ZeroFunction && f2 instanceof  ZeroFunction)
                    result = f2;
                else result = new SummationFunction(f1,f2);
        }
        return result;
    }
    public double apply(double x)
    {
        return resFunc.apply(x);
    }
}
