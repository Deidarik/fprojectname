package ru.ssau.OOP_lab.concurrent;

import ru.ssau.OOP_lab.functions.TabulatedFunction;

public class ReadTask implements Runnable{
    private final TabulatedFunction func;
    public ReadTask(TabulatedFunction func){
        this.func = func;
    }
    @Override
    public void run(){
        for(int i = 0; i < func.getCount(); ++i){
            synchronized (func) {
                System.out.println("After read: i =" + i + " x = " + func.getX(i) + " y = " + func.getY(i));
            }
        }
    }
}
