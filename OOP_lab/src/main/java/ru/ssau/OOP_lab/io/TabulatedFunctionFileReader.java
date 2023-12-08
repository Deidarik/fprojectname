package ru.ssau.OOP_lab.io;

import ru.ssau.OOP_lab.functions.TabulatedFunction;
import ru.ssau.OOP_lab.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.OOP_lab.functions.factory.LinkedListTabulatedFunctionFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TabulatedFunctionFileReader {
    public static void main(String[] args) {
        try {
            try (BufferedReader readerArray = new BufferedReader(new FileReader("input/function.txt"));
                 BufferedReader readerLinked = new BufferedReader(new FileReader("input/function.txt"))) {

                TabulatedFunction arrayTabulatedFunction = FunctionsIO.readTabulatedFunction(readerArray, new ArrayTabulatedFunctionFactory());
                TabulatedFunction linkedListTabulatedFunction = FunctionsIO.readTabulatedFunction(readerLinked,new LinkedListTabulatedFunctionFactory());

                System.out.println("Array:");
                System.out.println(arrayTabulatedFunction);
                System.out.println("Linked List:");
                System.out.println(linkedListTabulatedFunction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
