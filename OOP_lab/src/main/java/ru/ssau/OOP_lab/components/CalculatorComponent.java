package ru.ssau.OOP_lab.components;

import ru.ssau.OOP_lab.functions.ArrayTabulatedFunction;
import ru.ssau.OOP_lab.functions.TabulatedFunction;
import ru.ssau.OOP_lab.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.OOP_lab.functions.factory.TabulatedFunctionFactory;
import ru.ssau.OOP_lab.io.TabulatedFunctionSerialization;
import ru.ssau.OOP_lab.operations.TabulatedFunctionOperationService;

public class CalculatorComponent {


    private TabulatedFunction oper1 = new ArrayTabulatedFunction(new double[]{0,0,0,0},new double[]{0,0,0,0});
    private TabulatedFunction oper2 = new ArrayTabulatedFunction(new double[]{0,0,0,0},new double[]{0,0,0,0});

    private TabulatedFunctionOperationService operationService = new TabulatedFunctionOperationService(new ArrayTabulatedFunctionFactory());

    private TabulatedFunction result = new ArrayTabulatedFunction(new double[]{0,0,0,0},new double[]{0,0,0,0});
    private String operation;

    public String getTypeOfFunction() {
        return typeOfFunction;
    }

    public void setTypeOfFunction(String typeOfFunction) {
        this.typeOfFunction = typeOfFunction;
    }

    private String typeOfFunction;


    public void setOperationService(TabulatedFunctionFactory factory) {
        this.operationService = new TabulatedFunctionOperationService(factory);
    }

    public void doOperation(){
        switch (operation) {
            case "add" -> result = operationService.add(oper1, oper2);
            case "subtract" -> result = operationService.subtract(oper1, oper2);
            case "multiply" -> result = operationService.multiplication(oper1, oper2);
            case "divide" -> result = operationService.division(oper1, oper2);
        }
    }
    public TabulatedFunction getOper1() {
        return oper1;
    }

    public void setOper1(TabulatedFunction oper1) {
        this.oper1 = oper1;
    }

    public TabulatedFunction getOper2() {
        return oper2;
    }

    public void setOper2(TabulatedFunction oper2) {
        this.oper2 = oper2;
    }

    public TabulatedFunctionOperationService getOperationService() {
        return operationService;
    }

    public void setOperationService(TabulatedFunctionOperationService operationService) {
        this.operationService = operationService;
    }

    public TabulatedFunction getResult() {
        return result;
    }

    public void setResult(TabulatedFunction result) {
        this.result = result;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
