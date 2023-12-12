package ru.ssau.OOP_lab.components;

import ru.ssau.OOP_lab.functions.ArrayTabulatedFunction;
import ru.ssau.OOP_lab.functions.TabulatedFunction;
import ru.ssau.OOP_lab.functions.factory.TabulatedFunctionFactory;
import ru.ssau.OOP_lab.operations.TabulatedDifferentialOperator;

public class DifferentialComponent {

    private TabulatedFunction oper = new ArrayTabulatedFunction(new double[]{0,0,0,0},new double[]{0,0,0,0});
    private TabulatedFunction result = new ArrayTabulatedFunction(new double[]{0,0,0,0},new double[]{0,0,0,0});
    private TabulatedDifferentialOperator differentialOperator;
    private String typeOfFunction;

    public String getTypeOfFunction() {
        return typeOfFunction;
    }

    public void setTypeOfFunction(String typeOfFunction) {
        this.typeOfFunction = typeOfFunction;
    }

    public void doDefferentiate(){
        this.result = differentialOperator.derive(oper);
    }



    public TabulatedFunction getOper() {
        return oper;
    }

    public void setOper(TabulatedFunction oper) {
        this.oper = oper;
    }

    public TabulatedFunction getResult() {
        return result;
    }

    public void setResult(TabulatedFunction result) {
        this.result = result;
    }

    public TabulatedDifferentialOperator getDifferentialOperator() {
        return differentialOperator;
    }

    public void setDifferentialOperator(TabulatedFunctionFactory factory) {
        this.differentialOperator = new TabulatedDifferentialOperator(factory);
    }


}
