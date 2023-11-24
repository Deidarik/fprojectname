package concurrent;

import functions.TabulatedFunction;

public class ReadTask implements Runnable{
    private TabulatedFunction func;
    public ReadTask(TabulatedFunction func){
        this.func = func;
    }
    public void run(){
        for(int i = 0; i < func.getCount(); i++){
            System.out.println("After read: i =" + i + "x = " + func.getX(i) +" y = " +func.getY(i));
        }
    }
}
