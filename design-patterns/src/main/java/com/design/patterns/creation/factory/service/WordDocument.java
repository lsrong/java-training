package com.design.patterns.creation.factory.service;

import java.io.IOException;
import java.nio.file.Path;

/**
 * @description: WORD文档接口
 * @author: lsrong
 * @date: 2022/9/30 16:03
 **/
public interface WordDocument {
    /**
     * @description: 保存WORD文档
     * @param: [path]
     * @return: void
     * @author: lsrong
     * @date: 2022/9/30 16:07
     **/
    void save(Path path) throws IOException;
}
