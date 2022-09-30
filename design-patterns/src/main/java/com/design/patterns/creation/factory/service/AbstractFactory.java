package com.design.patterns.creation.factory.service;

import com.design.patterns.creation.factory.fast.FastFactory;
import com.design.patterns.creation.factory.good.GoodFactory;

/**
 * @description: 抽象工厂
 * @author: lsrong
 * @date: 2022/9/30 16:10
 **/
public interface AbstractFactory {
    /**
     * @description: 生产HTML文档
     * @param: [md]
     * @return: com.design.patterns.creation.factory.service.HtmlDocument
     * @author: lsrong
     * @date: 2022/9/30 16:04
     **/
    HtmlDocument makeHtml(String md);
    
    /**
     * @description: 生产WORD文档
     * @param: [md]
     * @return: com.design.patterns.creation.factory.service.WordDocument
     * @author: lsrong
     * @date: 2022/9/30 16:04
     **/
    WordDocument makeWord(String md);

    /**
     * @description: 创建实际工厂
     * @param: [name]
     * @return: com.design.patterns.creation.factory.service.AbstractFactory
     * @author: lsrong
     * @date: 2022/9/30 16:38
     **/
    static AbstractFactory createFactory(String name){
        if(name.equalsIgnoreCase("fast")){
            return new FastFactory();
        } else if (name.equalsIgnoreCase("good")) {
            return new GoodFactory();
        }else {
            throw new IllegalArgumentException("Unsupported factory!");
        }
    }
}
