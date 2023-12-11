package ru.ssau.OOP_lab.io;

import ru.ssau.OOP_lab.functions.TabulatedFunction;

import java.io.*;

public class TabulatedFunctionSerialization {
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
        TabulatedFunction function = null;
        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(dir))) {
            function = FunctionsIO.deserialize(input);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return function;
    }

}
