package concurrent;

import functions.TabulatedFunction;

public class WriteTask implements Runnable{
    private TabulatedFunction func;
    private double value;
    public WriteTask(TabulatedFunction f, double v){
        this.func = f;
        this.value = v;
    }
    public void run(){
        for( int i = 0; i < func.getCount(); i++){
            func.setY(i,value);
            System.out.println("Writing for index "+i+" complete");
        }
    }
}
