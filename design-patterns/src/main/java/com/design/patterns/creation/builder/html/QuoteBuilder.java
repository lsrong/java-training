package com.design.patterns.creation.builder.html;


public class QuoteBuilder implements DocumentBuilder {
    @Override
    public String build(String line){
        return "<blockquote>" + line.substring(1).trim() + "</blockquote>";
    }
}
