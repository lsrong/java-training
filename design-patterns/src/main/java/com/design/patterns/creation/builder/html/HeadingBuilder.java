package com.design.patterns.creation.builder.html;

public class HeadingBuilder implements DocumentBuilder {
    @Override
    public String build(String heading){
        int n = 0;
        while (heading.charAt(0) == '#') {
            n++;
            heading = heading.substring(1);
        }

        return String.format("<h%d>%s</h%d>", n, heading.trim(), n);
    }
}
