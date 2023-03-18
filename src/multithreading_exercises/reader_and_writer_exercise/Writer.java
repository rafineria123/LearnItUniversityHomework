package multithreading_exercises.reader_and_writer_exercise;

import java.util.Random;

public class Writer extends Thread{

    private Buffer buffer;

    public Writer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(true){
            try {
                synchronized (buffer.getWriterMonitor()) {
                    buffer.getWriterMonitor().wait();
                }
                write();
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
        synchronized (buffer.getReaderMonitor()) {
        buffer.getReaderMonitor().notifyAll();
        }

    }
}
