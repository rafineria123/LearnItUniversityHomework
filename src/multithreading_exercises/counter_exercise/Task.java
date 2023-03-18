package multithreading_exercises.counter_exercise;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable{
    private int counterOne = 0;
    private int counterTwo = 0;


    public static void main(String[] args) throws InterruptedException {
        var thread1 = new Thread(new Task());
        var thread2 = new Thread(new Task());
        var thread3 = new Thread(new Task());
        thread1.start();
        thread2.start();
        thread3.start();

        TimeUnit.SECONDS.sleep(3);
        thread1.interrupt();
        thread2.interrupt();
        thread3.interrupt();

    }

    @Override
    public void run() {
        try {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (this) {
                System.out.println("c1: " + this.counterOne + "c2: " + this.counterTwo);

                counterOne++;
                TimeUnit.MILLISECONDS.sleep(10);
                counterTwo++;
                TimeUnit.MILLISECONDS.sleep(10);
            }
        }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
