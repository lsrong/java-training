package com.design.patterns.creation.single;


/**
 * @description: 枚举类的单例模式
 * @author: lsrong
 * @date: 2022/9/30 17:48
 **/
public enum EnumSingleton {
    INSTANCE;   // 唯一枚举
    private String name;
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

// 使用
// String name = World.INSTANCE.getName();
