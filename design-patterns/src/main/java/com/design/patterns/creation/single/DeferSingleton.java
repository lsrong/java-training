package com.design.patterns.creation.single;

/**
 * @description: DeferSingleton,延迟加载单例模式
 * @author: lsrong
 * @date: 2022/9/30 17:25
 **/
public class DeferSingleton {
    // 私有构造
    private DeferSingleton(){}

    // 私有唯一实例
    private static volatile DeferSingleton instance;

    // 延迟加载，多线程下会出现并发的问题，因此需要加锁，一旦加锁就会严重影响性能，所以一般不会用这种方式。
    public static DeferSingleton getInstance(){
        if(instance == null){
            synchronized (DeferSingleton.class){
                if(instance == null) {
                    instance = new DeferSingleton();
                }
            }
        }

        return instance;
    }
}
