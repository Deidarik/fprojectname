package functions;

public class IntegralFunction implements MathFunction{

    private MathFunction result;
    private MathFunction func;
    private MathFunction d = new IdentityFunction();

    IntegralFunction(MathFunction func)
    {
        this.d = new IdentityFunction();
        this.func = func;
        this.result = integrate(func);
    }
    IntegralFunction(MathFunction func, MathFunction d)
    {
        this.func = func;
        this.d = d;
        this.result = integrate(func);

    }
    public MathFunction getResult(){return result;}
    private boolean isD(MathFunction f)
    {
        boolean b = false;
        MathFunction f1,f2,f3,f4;
        if(f instanceof MultiplyFunction)
        {
            f1 = ((MultiplyFunction)f).getFunc1();
            f2 = ((MultiplyFunction)f).getFunc2();
            if (f2 instanceof MultiplyFunction)
            {
                f4 = ((MultiplyFunction)f2).getFunc1();
                f3 = ((MultiplyFunction)f2).getFunc2();
                if (f4 instanceof ConstantFunction && f1 instanceof ConstantFunction && f3 instanceof IdentityFunction) {
                    f1 = new ConstantFunction(((ConstantFunction) f1).getX() * ((ConstantFunction) f4).getX());
                    f2 = f3;
                }
                else if (f3 instanceof ConstantFunction && f1 instanceof ConstantFunction && f4 instanceof IdentityFunction ) {
                    f1 = new ConstantFunction(((ConstantFunction) f1).getX() + ((ConstantFunction) f3).getX());
                    f2 = f4;
                }
                b = (f1 instanceof ConstantFunction && f2 instanceof IdentityFunction) || (f2 instanceof ConstantFunction && f1 instanceof IdentityFunction);

            }
        }
        return b;
    }
    private MathFunction checkFunc(MathFunction mf,MathFunction origin){
        MathFunction f1,f2;
        if(isD(mf))
            return origin;
        if(origin instanceof MultiplyFunction)
        {
            f1 = ((MultiplyFunction)origin).getFunc1();
            f1 = checkFunc((new DifferentiateFunction(f1)).getResult(),f1);
            if (isD((new DifferentiateFunction(f1)).getResult()))
                return f1;
            f2 = ((MultiplyFunction)origin).getFunc2();
            f2 = checkFunc((new DifferentiateFunction(f2)).getResult(),f2);
            if (isD((new DifferentiateFunction(f2)).getResult()))
                return f2;
        }
        if (origin instanceof SqrFunction)
        {
            f1 = ((SqrFunction)origin).getFunc();
            return checkFunc(new DifferentiateFunction(f1).getResult(),f1);
        }

        if(origin instanceof SummationFunction) {
            f1 = ((SummationFunction) origin).getFunc1();
            f1 = checkFunc(new DifferentiateFunction(f1).getResult(), f1);
            if (isD((new DifferentiateFunction(f1)).getResult()))
                return f1;
            f2 = ((SummationFunction) origin).getFunc2();
            f2 = checkFunc(new DifferentiateFunction(f2).getResult(), f2);
            if (isD((new DifferentiateFunction(f2)).getResult()))
                return f1;
        }
        if(origin instanceof IdentityFunction)
            return origin;
        return new ZeroFunction();
    }
    private MathFunction partialIntegral(InverseTrigonometricFunction function)
    {

        MathFunction u,du,v,preV,temp,temp1, resFunc;
        double n = 1;
        du = (new DifferentiateFunction(function)).getResult();
        v = (new IntegralFunction(new ConstantFunction(1))).getResult();
        temp = checkFunc(du,du);
        if (temp instanceof ZeroFunction)
            return temp;
        d = temp;
        temp = (new DifferentiateFunction(temp)).getResult();

        preV = v;
        v = new MultiplyFunction((new IntegralFunction(du,d)).getResult(),new SqrFunction(new ConstantFunction(-2),-1));
        temp = new DefinedIntegral(new SummationFunction(new MultiplyFunction(function,preV),new MultiplyFunction(new ConstantFunction(-1),v)));
        return temp;
    }


    private MathFunction integrate(MathFunction mf)
    {
        MathFunction resFunc = new ZeroFunction();
        MathFunction f1,f2;
        double n;
        switch (mf.typeOfFunc())
        {
            case SUM:
                f1 = ((SummationFunction)mf).getFunc1();
                f2 = ((SummationFunction)mf).getFunc2();
                resFunc = new SummationFunction((new IntegralFunction(f1)).getResult(),(new IntegralFunction(f2)).getResult());
                break;
            case MULT:
                f1 = ((MultiplyFunction)mf).getFunc1();
                f2 = ((MultiplyFunction)mf).getFunc2();
                if (f1 instanceof ConstantFunction)
                    resFunc = new MultiplyFunction(f1,(new IntegralFunction(f2,d)).getResult());
                else if (f2 instanceof ConstantFunction)
                    resFunc = new MultiplyFunction(f2,(new IntegralFunction(f1,d)).getResult());

                break;
            case CONST:
                resFunc = new MultiplyFunction(func,d);
                break;
            case X:
                resFunc = new MultiplyFunction(new SqrFunction(d,2),new SqrFunction(new ConstantFunction(2),-1));
                break;
            case POWER:
                n = ((SqrFunction)mf).getN().getX();
                resFunc = new MultiplyFunction(new SqrFunction(d,n+1),new SqrFunction(new ConstantFunction(n+1),-1));
                break;
            case ASIN:
            case ACOS:
                resFunc = partialIntegral((InverseTrigonometricFunction) func);
                break;
//            case COS:
//                f1 = ((TrigonometricFunction)mf).getFunc();
//                if(f1 instanceof IdentityFunction)
//                    resFunc = new TrigonometricFunction(FunctionsType.SIN,)

        };
        return resFunc;
    }
    public double apply(double x)
    {
        return result.apply(x);
    }
}
