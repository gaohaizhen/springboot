package cn.demo.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock每次只能由一个线程获取到锁；同一个线程可以多次获取锁，但是也必须释放多次
 */
public class ReentrantLockDemo {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();

        for (int i = 0; i < 6; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    try {
                        System.out.println(Thread.currentThread().getName() + "取得了锁");
                        lock.lock();
                        lock.tryLock();
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.unlock();
                    lock.unlock();
                    lock.unlock();

                    System.out.println(Thread.currentThread().getName() + "释放了锁");
                }
            });

            thread.start();
        }
    }
}
