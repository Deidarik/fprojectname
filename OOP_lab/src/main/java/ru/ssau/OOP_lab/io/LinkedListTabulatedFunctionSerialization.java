package ru.ssau.OOP_lab.io;

import ru.ssau.OOP_lab.functions.LinkedListTabulatedFunction;
import ru.ssau.OOP_lab.functions.TabulatedFunction;
import ru.ssau.OOP_lab.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.OOP_lab.functions.factory.TabulatedFunctionFactory;
import ru.ssau.OOP_lab.operations.TabulatedDifferentialOperator;

import java.io.*;

public class LinkedListTabulatedFunctionSerialization {
    public static void serialize(String dir, TabulatedFunction func) {
        try{
            try(BufferedOutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream(dir))){
                FunctionsIO.serialize(fileOutputStream, func);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static TabulatedFunction deserialize(String dir) {
        TabulatedFunction func = null;
        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(dir))) {
            func = FunctionsIO.deserialize(input);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return func;
    }
}
