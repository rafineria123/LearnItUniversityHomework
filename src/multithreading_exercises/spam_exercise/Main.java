package multithreading_exercises.spam_exercise;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Spam spamObject = new Spam(new String[]{"a", "b", "c"}, new long[]{1000, 2000, 5000});
        InputListener inputListener = new InputListener(spamObject);
        inputListener.startSpamming();

        TimeUnit.SECONDS.sleep(5);
        ByteArrayInputStream bais = new ByteArrayInputStream(new byte[] { '\n' });
        System.setIn(bais);
    }

    private static class Spam extends Thread{
        private String[] msgArray;
        private long[] msArray;

        public Spam(String[] msgArray, long[] msArray) {
            if(msgArray.length!=msArray.length){
                throw new IllegalArgumentException();
            }
            this.msgArray = msgArray;
            this.msArray = msArray;
            this.setDaemon(true);
        }

        @Override
        public void run() {
            for(int i = 0;i<this.msgArray.length;i++){
                try {
                    sleep(msArray[i]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(msgArray[i]);
            }
        }

    }

    private static class InputListener extends Thread{
        private Thread threadToInterrupt;

        public InputListener(Thread threadToInterrupt) {
            this.threadToInterrupt = threadToInterrupt;
            super.setDaemon(true);
        }

        @Override
        public void run() {
            this.threadToInterrupt.run();
            while(true){
                try {
                    if(System.in.available() > 0 && System.in.read() == '\n'){
                        this.threadToInterrupt.interrupt();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void startSpamming(){
            start();
        }
    }
}


