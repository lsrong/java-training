package com.design.patterns.creation.factory.fast;

import com.design.patterns.creation.factory.service.AbstractFactory;
import com.design.patterns.creation.factory.service.HtmlDocument;
import com.design.patterns.creation.factory.service.WordDocument;

/**
 * @description:  Fast工厂
 * @author: lsrong
 * @date: 2022/9/30 16:04
 **/
public class FastFactory implements AbstractFactory {
    @Override
    public HtmlDocument makeHtml(String md) {
        // 创建Fast的HTML文档
        return new FastHtmlDocument(md);
    }

    @Override
    public WordDocument makeWord(String md) {
        // 创建Fast的WORD文档
        return new FastWordDocument(md);
    }
}
