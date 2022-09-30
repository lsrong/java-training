package com.design.patterns.creation.single;

/**
 * @description: Singleton,最简单的实现
 * @author: lsrong
 * @date: 2022/9/30 17:25
 **/
public class Singleton {
    // 私有构造
    private Singleton(){}

    // 私有唯一实例
    private static final Singleton instance = new Singleton();

    // 公开实例获得方法
    public static Singleton getInstance(){
        return instance;
    }
}
