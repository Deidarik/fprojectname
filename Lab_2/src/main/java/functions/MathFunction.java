package functions;

public interface MathFunction {
       double apply(double x); //aaaaa
       default public FunctionsType typeOfFunc()
       {
              MathFunction func = this;
              FunctionsType f = FunctionsType.ERROR;
              if (func instanceof TrigonometricFunction )
                     f = ((TrigonometricFunction) func).getType();
              else if (func instanceof IdentityFunction)
                     f = FunctionsType.X;
              else if (func instanceof InverseTrigonometricFunction)
                     f = ((InverseTrigonometricFunction)func).getType();
              else if(func instanceof ConstantFunction)
                     f = FunctionsType.CONST;
              else if(func instanceof CompositeFunction)
                     f = FunctionsType.COMP;
              else if(func instanceof SqrFunction)
                     f = FunctionsType.POWER;
              else if(func instanceof  MultiplyFunction)
                     f = FunctionsType.MULT;
              else if(func instanceof SummationFunction)
                     f = FunctionsType.SUM;


              return f;
       }
        default CompositeFunction andThen(MathFunction afterFunction)
       {
       CompositeFunction cf = new CompositeFunction(this, afterFunction);
           return cf;
       }
}
