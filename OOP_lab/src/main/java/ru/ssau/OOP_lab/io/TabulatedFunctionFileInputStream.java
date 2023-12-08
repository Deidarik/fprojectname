package ru.ssau.OOP_lab.io;

import ru.ssau.OOP_lab.functions.TabulatedFunction;
import ru.ssau.OOP_lab.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.OOP_lab.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.OOP_lab.functions.factory.TabulatedFunctionFactory;
import ru.ssau.OOP_lab.operations.TabulatedDifferentialOperator;

import java.io.*;

public class TabulatedFunctionFileInputStream {
    public static void main(String[] args) {
        try{
            try(BufferedInputStream input =new BufferedInputStream( new FileInputStream("input/binary_function.bin"))){
                TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
                TabulatedFunction function = FunctionsIO.readTabulatedFunction(input, factory);
                System.out.println(function);
            }

        }catch(IOException e){
            e.printStackTrace();
        }
        try{
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите размер и значения функции: ");
            TabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
            TabulatedDifferentialOperator oper = new TabulatedDifferentialOperator(factory);
            TabulatedFunction function = FunctionsIO.readTabulatedFunction(inputReader, factory);
            System.out.println(oper.derive(function));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
