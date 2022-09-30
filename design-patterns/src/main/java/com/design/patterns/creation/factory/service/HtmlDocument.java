package com.design.patterns.creation.factory.service;

import java.io.IOException;
import java.nio.file.Path;

/**
 * @description: HTML文档接口
 * @author: lsrong
 * @date: 2022/9/30 16:03
 **/
public interface HtmlDocument {
    /**
     * @description: MD转成HTML格式
     * @param: []
     * @return: java.lang.String
     * @author: lsrong
     * @date: 2022/9/30 16:06
     **/
    String toHtml();

    /**
     * @description: 保存HTML文档
     * @param: [path]
     * @return: void
     * @author: lsrong
     * @date: 2022/9/30 16:07
     **/
    void save(Path path) throws IOException;
}
