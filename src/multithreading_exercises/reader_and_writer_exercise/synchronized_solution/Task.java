package multithreading_exercises.reader_and_writer_exercise.synchronized_solution;

public class Task {
    public static void main(String[] args) {
        Buffer buffer  = new Buffer(5,3);
        Reader reader1 = new Reader(buffer);
        Reader reader2 = new Reader(buffer);
        Reader reader3 = new Reader(buffer);
        Writer writer = new Writer(buffer);
        writer.start();
        reader1.start();
        reader2.start();
        reader3.start();
        buffer.start();

    }
}
