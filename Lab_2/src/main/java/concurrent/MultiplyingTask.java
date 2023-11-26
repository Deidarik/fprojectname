package concurrent;
import functions.TabulatedFunction;

import java.util.concurrent.CountDownLatch;

public class MultiplyingTask implements Runnable{

    private final TabulatedFunction tabulatedFunction;
    private final CountDownLatch latch;

    public MultiplyingTask(TabulatedFunction tabulatedFunction, CountDownLatch latch) {
        this.tabulatedFunction = tabulatedFunction;
        this.latch = latch;
    }

    @Override
    public void run() {
        for (int i = 0; i < tabulatedFunction.getCount(); i++) {
            synchronized (tabulatedFunction) {
                tabulatedFunction.setY(i, tabulatedFunction.getY(i) * 2);
            }
        }

        String threadName = Thread.currentThread().getName();
        System.out.println("Thread " + threadName + " has completed the task.");

        latch.countDown();
    }
}
