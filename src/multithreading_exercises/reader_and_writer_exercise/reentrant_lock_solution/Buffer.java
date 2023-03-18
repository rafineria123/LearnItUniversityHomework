package multithreading_exercises.reader_and_writer_exercise.reentrant_lock_solution;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer extends Thread{


    private StringBuffer content;
    private int maxSize;
    private int amountOfReaders;
    private CountDownLatch countDownLatch;
    private Lock lock = new ReentrantLock();
    private Condition conditionFull;
    private Condition conditionEmpty;

    public Buffer(int maxSize, int amountOfReaders) {
        content = new StringBuffer();
        conditionFull = lock.newCondition();
        conditionEmpty = lock.newCondition();
        this.maxSize = maxSize;
        this.countDownLatch = new CountDownLatch(amountOfReaders);
        this.amountOfReaders = amountOfReaders;
    }


    @Override
    public void run() {

        while(true){
            try {
                lock.lock();
                countDownLatch.await();
                countDownLatch = new CountDownLatch(this.amountOfReaders);

                conditionEmpty.signalAll();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }
    }

    public void write(String message){
        if((content.length()+message.length())>maxSize){
            throw new IndexOutOfBoundsException("Buffer can store only up to " + maxSize + " characters");
        }else {
            content.append(message);
        }
    }




    public String read(){
        return content.toString();
    }

    public int getMaxSize() {
        return maxSize;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public Lock getLock() {
        return lock;
    }

    public Condition getConditionFull() {
        return conditionFull;
    }

    public Condition getConditionEmpty() {
        return conditionEmpty;
    }
}
