package multithreading_exercises.thread_name_exercise;

import java.time.Clock;

public class Main {
    public static void main(String[] args) {
         Thread threadA = new Thread(new impRunnable());
         threadA.run();
         ExtThread threadB = new ExtThread();
         threadB.run();
         Thread threadC = new Thread(() -> {
             Clock clock = Clock.systemDefaultZone();
             long currentTime = clock.millis();
             while(currentTime + 1000> clock.millis()){
                 System.out.println(Thread.currentThread().getName()+"C");
             }
         });
         threadC.run();
         Thread threadD = new Thread(Main::printThreadName);
         threadD.run();
    }

    public static void printThreadName(){
        Clock clock = Clock.systemDefaultZone();
        long currentTime = clock.millis();
        while(currentTime + 1000> clock.millis()) {
            System.out.println(Thread.currentThread().getName() + "C");
        }
    }
}
