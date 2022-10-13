package com.design.patterns.structure.adapter;

import java.util.concurrent.Callable;

public class Task implements Callable<Long> {
    private final long num;

    public Task(long num) {
        this.num = num;
    }

    @Override
    public Long call() {
        long r = 0;
        for(long n=1; n < this.num; n++) {
            r = r + n;
        }
        System.out.println("Result: " + r);

        return r;
    }
}
