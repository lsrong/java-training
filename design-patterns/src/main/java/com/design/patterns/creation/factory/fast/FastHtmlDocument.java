package com.design.patterns.creation.factory.fast;


import com.design.patterns.creation.factory.service.HtmlDocument;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * @description: Fast的HtmlDocument实现类
 * @author: lsrong
 * @date: 2022/9/30 16:20
 **/
public class FastHtmlDocument implements HtmlDocument {
    private final String md;

    public FastHtmlDocument(String md){
        this.md = md;
    }

    /**
     * @description: Fast的转换成HTML文档
     * @param: [path]
     * @return: void
     * @author: lsrong
     * @date: 2022/9/30 16:08
     **/
    @Override
    public String toHtml() {
        StringBuilder html = new StringBuilder();
        Scanner scanner = new Scanner(md);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.startsWith("#")){
                html.append("<h1>");
                html.append(line.substring(1));
                html.append("</h1>");
            }else{
                html.append("<p>");
                html.append(line);
                html.append("</p>");
            }
            html.append("\n");
        }
        scanner.close();

        return html.toString();
    }

    @Override
    public void save(Path path) throws IOException {
        Files.write(path, toHtml().getBytes(StandardCharsets.UTF_8));
    }
}
