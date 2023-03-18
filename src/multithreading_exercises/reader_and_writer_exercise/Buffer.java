package multithreading_exercises.reader_and_writer_exercise;

import java.util.concurrent.CountDownLatch;

public class Buffer extends Thread{


    private StringBuffer content;
    private int maxSize;
    private int amountOfReaders;
    private Object readerMonitor;
    private Object writerMonitor;
    private CountDownLatch countDownLatch;

    public Buffer(int maxSize, int amountOfReaders) {
        content = new StringBuffer();
        this.maxSize = maxSize;
        this.countDownLatch = new CountDownLatch(amountOfReaders);
        this.amountOfReaders = amountOfReaders;
        readerMonitor = new Object();
        writerMonitor = new Object();
    }


    @Override
    public void run() {
        synchronized (writerMonitor){
            writerMonitor.notify();
        }
        while(true){
            try {
                countDownLatch.await();
                synchronized (writerMonitor){
                    content = new StringBuffer();
                    writerMonitor.notify();
                }

                countDownLatch = new CountDownLatch(this.amountOfReaders);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

    public Object getReaderMonitor() {
        return readerMonitor;
    }

    public Object getWriterMonitor() {
        return writerMonitor;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }
}
