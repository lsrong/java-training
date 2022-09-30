package com.design.patterns.creation.factory.good;

import com.design.patterns.creation.factory.service.AbstractFactory;
import com.design.patterns.creation.factory.service.HtmlDocument;
import com.design.patterns.creation.factory.service.WordDocument;

/**
 * @description:  Good工厂
 * @author: lsrong
 * @date: 2022/9/30 16:04
 **/
public class GoodFactory implements AbstractFactory {

    @Override
    public HtmlDocument makeHtml(String md) {
        // 创建Good的HTML文档
        return new GoodHtmlDocument(md);
    }

    @Override
    public WordDocument makeWord(String md) {
        // 创建Good的WORD文档
        return new GoodWordDocument(md);
    }
}
