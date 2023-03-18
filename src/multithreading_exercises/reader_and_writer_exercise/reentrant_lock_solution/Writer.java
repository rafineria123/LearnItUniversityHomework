package multithreading_exercises.reader_and_writer_exercise.reentrant_lock_solution;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Writer extends Thread{

    private Buffer buffer;

    public Writer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(true){
            buffer.getLock().lock();
            write();

            buffer.getConditionFull().signalAll();
            buffer.getLock().unlock();

            try {
                buffer.getLock().lock();
                buffer.getConditionEmpty().await();
                buffer.getLock().unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        }
    }

    private void write(){
        Random random = new Random();
        char c;
        System.out.println("");
        System.out.print("Writer writes: ");
        for(int i = 0;i<buffer.getMaxSize();i++){
            c = (char)(random.nextInt(26) + 'a');
            buffer.write(String.valueOf(c));
            System.out.print(c);
        }

    }
}
