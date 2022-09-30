package com.design.patterns.creation.factory.good;

import com.design.patterns.creation.factory.service.HtmlDocument;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * @description: Good的HtmlDocument实现类
 * @author: lsrong
 * @date: 2022/9/30 16:20
 **/
public class GoodHtmlDocument implements HtmlDocument {
    private final String md;

    public GoodHtmlDocument(String md){
        this.md = md;
    }

    /**
     * @description: Good的装换HTML方法
     * @param: [path]
     * @return: void
     * @author: lsrong
     * @date: 2022/9/30 16:07
     **/
    @Override
    public String toHtml() {
        StringBuilder html = new StringBuilder("<!DOCTYPE html>\n<html lang=\"en\">\n<body>\n");
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
        html.append("</body>\n</html>");
        scanner.close();

        return html.toString();
    }

    @Override
    public void save(Path path) throws IOException {
        Files.write(path, toHtml().getBytes(StandardCharsets.UTF_8));
    }
}
