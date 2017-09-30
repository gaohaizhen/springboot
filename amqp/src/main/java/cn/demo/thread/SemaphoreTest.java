package cn.demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semphore = new Semaphore(3);
        for (int i = 0; i < 11; i++) {
            executorService.submit(new Runnable() {

                @Override
                public void run() {
                    try {
                        semphore.acquire();
                        System.out.println("线程" + Thread.currentThread().getName() + "获取到了信号量, 还剩余"
                                + semphore.availablePermits() + "个信号量");
                        Thread.sleep((long) (Math.random() * 10000));
                        semphore.release();
                        System.out.println("线程" + Thread.currentThread().getName() + "执行完了，要释放信号量了");
                        semphore.release();
                        System.out.println("线程" + Thread.currentThread().getName() + "已经释放信号量, 还剩余"
                                + semphore.availablePermits() + "个信号量");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            });
        }



        executorService.shutdown();

    }

}
