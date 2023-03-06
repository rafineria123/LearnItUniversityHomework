package multithreading_exercises.multidimensional_array_exercise;

import java.time.Clock;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

public class FutureSolution {
    private static final int ROWS = 4;
    private static final int COLUMNS = 100;
    private static Clock clock = Clock.systemDefaultZone();
    private static int [][] generatedArray = generateArray(ROWS, COLUMNS);
    private static int countDownLatchResult = 0;


    public static void main(String[] args) throws Exception {

        System.out.println(findMaxInMultiDimensionArray(generatedArray));

        System.out.println(findMaxInMultiDimensionArrayMultiThreadFuture());

        System.out.println(findMaxInMultiDimensionArrayMultiThreadCountDownLatch());



    }

    private static int[][] generateArray(int rows, int columns){
        var generatedArray = new int[rows][columns];

        Random random = new Random();
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                generatedArray[i][j]  = random.nextInt(100000);
            }
        }
        return generatedArray;
    }

    private static int findMaxInOneDimensionArray(int [] array){
        int max = 0;
        for(int i = 0;i<array.length;i++){
            if(Integer.compare(array[i], max)>0){
                max = array[i];
            }
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return max;
    }

    private static int findMaxInMultiDimensionArray(int [][]array){
        long start = clock.millis();
        int max = 0;
        for(int[] singleArray:array){
            int result = findMaxInOneDimensionArray(singleArray);
            if(result > max){
                max = result;
            }
        }
        long stop = clock.millis();
        System.out.println(stop - start);
        return max;
    }

    private static int findMaxInMultiDimensionArrayMultiThreadFuture() throws Exception {
        long start = clock.millis();
        ExecutorService executorService = Executors.newFixedThreadPool(ROWS);
        var futureList = Arrays.asList(new Future[4]);
        for(int i = 0;i<4;i++){
            futureList.set(i, executorService.submit(new MultiThreadingFuture(i)));
        }
        int max = 0;
        for(Future<Integer> future : futureList){
            if(future.get() > max){
                max = future.get();
            }
        }
        long stop = clock.millis();
        System.out.println(stop - start);
        executorService.shutdown();
        return max;
    }

    private static int findMaxInMultiDimensionArrayMultiThreadCountDownLatch(){
        long start = clock.millis();
        CountDownLatch countDownLatch = new CountDownLatch(ROWS);
        ExecutorService executorService = Executors.newFixedThreadPool(ROWS);
        var latchArray = new Runnable[ROWS];
        for(int i = 0 ;i<ROWS;i++){
            latchArray[i] = new MultiThreadingCountDownLatch(i, countDownLatch);
        }
        for(Runnable task: latchArray){
            executorService.submit(task);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        long stop = clock.millis();
        System.out.println(stop - start);

        return countDownLatchResult;
    }

    private static class MultiThreadingFuture implements Callable<Integer>{

        private int id;

        public MultiThreadingFuture(int id) {
            this.id = id;
        }

        @Override
        public Integer call() throws Exception {
            return FutureSolution.findMaxInOneDimensionArray(FutureSolution.generatedArray[id]);
        }
    }

    private static class MultiThreadingCountDownLatch implements Runnable{

        private int id;
        private CountDownLatch countDownLatch;

        public MultiThreadingCountDownLatch(int id, CountDownLatch countDownLatch) {
            this.id = id;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            int result = FutureSolution.findMaxInOneDimensionArray(generatedArray[id]);
            if(result > countDownLatchResult) countDownLatchResult = result;
            countDownLatch.countDown();

        }
    }




}
