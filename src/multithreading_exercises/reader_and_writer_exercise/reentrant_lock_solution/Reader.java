package multithreading_exercises.reader_and_writer_exercise.reentrant_lock_solution;

public class Reader extends Thread{

    private Buffer buffer;

    public Reader(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {

        while(true){
            try {
                buffer.getLock().lock();
                buffer.getConditionFull().await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            read();
            buffer.getCountDownLatch().countDown();
            buffer.getLock().unlock();
        }
    }

    public void read(){
        System.out.print("Reader "+Thread.currentThread().getName() + " : "+buffer.read());
        buffer.getCountDownLatch().countDown();
    }
}
