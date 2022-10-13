package com.design.patterns.creation.builder.html;

import java.util.Scanner;

public class HtmlBuilder implements DocumentBuilder {
    private final HeadingBuilder headingBuilder = new HeadingBuilder();
    private final HrBuilder hrBuilder = new HrBuilder();
    private final ParagraphBuilder paragraphBuilder = new ParagraphBuilder();
    private final QuoteBuilder quoteBuilder = new QuoteBuilder();       // quote - 引用

    @Override
    public String build(String markdown) {
        StringBuilder buffer = new StringBuilder();
        Scanner scanner = new Scanner(markdown);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.startsWith("#")){
                buffer.append(headingBuilder.build(line)).append("\n");
            } else if(line.startsWith(">")) {
                buffer.append(quoteBuilder.build(line)).append("\n");
            } else if(line.startsWith("---")) {
                buffer.append(hrBuilder.build(line)).append("\n");
            } else {
                buffer.append(paragraphBuilder.build(line)).append("\n");
            }
        }

        return buffer.toString();
    }
}
