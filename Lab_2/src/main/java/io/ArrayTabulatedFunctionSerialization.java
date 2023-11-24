package io;

import functions.ArrayTabulatedFunction;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import operations.TabulatedDifferentialOperator;

import java.io.*;

public class ArrayTabulatedFunctionSerialization {
    public static void main(String[] args) {
        try{
            try(BufferedOutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream("output/serialized_array_functions.bin"))){
                double[] xValue = {1, 2, 3, 4, 5};
                double[] yValue = {5, 10, 15, 20, 25};

                TabulatedFunction func = new ArrayTabulatedFunction(xValue,yValue);
                TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
                TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(factory);

                TabulatedFunction derivativeFirst = operator.derive(func);
                TabulatedFunction derivativeSecond = operator.derive(derivativeFirst);

                FunctionsIO.serialize(fileOutputStream, func);
                FunctionsIO.serialize(fileOutputStream, derivativeFirst);
                FunctionsIO.serialize(fileOutputStream, derivativeSecond);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream("output/serialized_array_functions.bin"))) {
            System.out.println(FunctionsIO.deserialize(input));
            System.out.println(FunctionsIO.deserialize(input));
            System.out.println(FunctionsIO.deserialize(input));

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
