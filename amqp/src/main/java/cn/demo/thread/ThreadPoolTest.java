package cn.demo.thread;

import java.util.concurrent.*;

public class ThreadPoolTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //ExecutorService threadPool = Executors.newFixedThreadPool(3);
        //ExecutorService threadPool = Executors.newCachedThreadPool();

        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 10; i++) {
            final int task = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 1; j <= 10; j++) {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + " is looping of " + j + " for  task of " + task);
                    }
                }
            });
        }
        System.out.println("all of 10 tasks have committed! ");
        //threadPool.shutdownNow();


        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName()+"sleep start");
                    Thread.sleep(5000);
                    System.out.println(Thread.currentThread().getName()+"sleep over");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },3,2,TimeUnit.SECONDS);



    }

}
