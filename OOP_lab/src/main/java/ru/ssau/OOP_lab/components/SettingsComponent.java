package ru.ssau.OOP_lab.components;

import ru.ssau.OOP_lab.functions.LinkedListTabulatedFunction;
import ru.ssau.OOP_lab.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.OOP_lab.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.OOP_lab.functions.factory.TabulatedFunctionFactory;

import java.io.Serial;
import java.io.Serializable;

public class SettingsComponent implements Serializable,Components {
    private String stringFactory;
    private TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
    @Serial
    private static final long serialVersionUID = 949758346190653819L;
    public TabulatedFunctionFactory getFactory() {
        return factory;
    }
    public String setLinkedListFactory(){
        return "Linked list";
    }
    public String setArrayFactory(){
        return "Array";
    }
    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }
    public void makeFactory(){
        switch (stringFactory) {
            case "Linked list" -> factory = new LinkedListTabulatedFunctionFactory();
            case "Array" -> factory = new ArrayTabulatedFunctionFactory();
        }
    }

    public String getStringFactory() {
        return stringFactory;
    }

    public void setStringFactory(String stringFactory) {
        this.stringFactory = stringFactory;
    }
    //надо ебануть сериализацию
}
