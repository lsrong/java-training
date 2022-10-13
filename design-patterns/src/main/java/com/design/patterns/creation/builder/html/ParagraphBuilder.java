package com.design.patterns.creation.builder.html;

public class ParagraphBuilder implements DocumentBuilder {
    @Override
    public String build(String line){
        return "<p>" + line + "</p>";
    }
}



