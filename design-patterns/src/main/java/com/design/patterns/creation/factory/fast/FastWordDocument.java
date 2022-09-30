package com.design.patterns.creation.factory.fast;


import com.design.patterns.creation.factory.service.WordDocument;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * @description: Fast的WordDocument实现类
 * @author: lsrong
 * @date: 2022/9/30 16:20
 **/
public class FastWordDocument implements WordDocument {
    private final String md;

    public FastWordDocument(String md){
        this.md = md;
    }

    /**
     * @description: Fast的转换成WORD文档
     * @param: [path]
     * @return: void
     * @author: lsrong
     * @date: 2022/9/30 16:08
     **/
    @Override
    public void save(Path path) throws IOException {
        StringBuilder doc = new StringBuilder("{\\rtf1\\ansi\n{\\fonttbl\\f0\\fswiss\\fcharset0 Helvetica-Bold;\\f1\\fswiss\\fcharset0 Helvetica;}\n");
        Scanner scanner = new Scanner(md);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.startsWith("#")){
                doc.append(String.format("\\f0\\b\\fs24 \\cf0%s\\par\n", line.substring(1)));
            }else{
                doc.append(String.format("\\f1\\b0%s\\par\n", line));
            }
        }
        doc.append("}");
        scanner.close();
//        System.out.println(doc);
        Files.write(path, doc.toString().getBytes(StandardCharsets.UTF_8));
    }
}
