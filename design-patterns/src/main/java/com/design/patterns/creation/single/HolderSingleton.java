package com.design.patterns.creation.single;

/**
 * @description: 嵌套类的单例模式
 * @author: lsrong
 * @date: 2022/9/30 17:40
 **/
public class HolderSingleton {
    private HolderSingleton(){}

    // 嵌套类可以访问外部类的静态属性和静态方法 的特性
    private static class Holder{
        private static HolderSingleton instance = new HolderSingleton();
    }

    public static HolderSingleton getInstance(){
        return Holder.instance;
    }
}

