package com.design.patterns.structure.adapter;

import java.util.concurrent.Callable;

/**
 * @description: 将 Callable 接口 适配为 Runnable 接口
 * @author: lsrong
 * @date: 2022/10/13 18:07
 **/
public class RunnableAdapter implements Runnable{

    // 被转换接口
    private final Callable<?> callable;

    public RunnableAdapter(Callable<?> callable){
        this.callable = callable;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        // 调用被转换接口
        try {
            callable.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
