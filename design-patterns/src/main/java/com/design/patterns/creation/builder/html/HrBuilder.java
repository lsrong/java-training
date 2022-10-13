package com.design.patterns.creation.builder.html;

public class HrBuilder implements DocumentBuilder {
    @Override
    public String build(String line) {
        return "<hr/>";
    }
}
