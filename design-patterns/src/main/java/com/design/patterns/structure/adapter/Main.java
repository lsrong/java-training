package com.design.patterns.structure.adapter;

import java.util.concurrent.Callable;

public class Main {
    public static void main(String[] args) {
        // Thread 接收 Runnable接口，不接受Callable 接口，会出现报错。
//        Callable<Long> callable = new Task(1000L);
//        Thread thread = new Thread(callable);
//        thread.start();
        Callable<Long> callable = new Task(1234000L);
        Thread thread = new Thread(new RunnableAdapter(callable));
        thread.start();
    }
}
