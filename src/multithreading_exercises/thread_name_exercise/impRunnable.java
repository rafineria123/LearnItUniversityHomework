package multithreading_exercises.thread_name_exercise;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;

public class impRunnable implements Runnable{
    @Override
    public void run() {
        Clock clock = Clock.systemDefaultZone();
        long currentTime = clock.millis();
        while(currentTime + 1000> clock.millis()){
            System.out.println(Thread.currentThread().getName()+"A");
        }

    }
}
