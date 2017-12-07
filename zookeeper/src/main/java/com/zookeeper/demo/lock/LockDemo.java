package com.zookeeper.demo.lock;

public class LockDemo {
    @SuppressWarnings("unused")
    public static void main(String[] args) throws Exception {
        System.out.println("123");
        try {
            int t = 1 / 0;
            System.out.println("try");
        } finally {
            System.out.println("22");
        }
    }

}
