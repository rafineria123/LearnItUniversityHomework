package multithreading_exercises.reader_and_writer_exercise.synchronized_solution;

public class Reader extends Thread{

    private Buffer buffer;

    public Reader(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            synchronized(buffer.getReaderMonitor()) {
                buffer.getReaderMonitor().wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(true){
            read();
            try {
                synchronized(buffer.getReaderMonitor()) {
                    buffer.getReaderMonitor().wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void read(){
        System.out.print("Reader "+Thread.currentThread().getName() + " : "+buffer.read());
        buffer.getCountDownLatch().countDown();
    }
}
