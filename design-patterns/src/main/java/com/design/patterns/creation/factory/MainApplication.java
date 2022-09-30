package com.design.patterns.creation.factory;

import com.design.patterns.creation.factory.fast.FastFactory;
import com.design.patterns.creation.factory.fast.FastHtmlDocument;
import com.design.patterns.creation.factory.good.GoodFactory;
import com.design.patterns.creation.factory.service.AbstractFactory;
import com.design.patterns.creation.factory.service.HtmlDocument;
import com.design.patterns.creation.factory.service.WordDocument;

import java.io.IOException;
import java.nio.file.Paths;

public class MainApplication {
    public static void main(String[] args) throws IOException {
        String md = "#Hello\nHello, world!";

        // Fast factory
//        AbstractFactory fastFactory = new FastFactory();
        AbstractFactory fastFactory = AbstractFactory.createFactory("fast");
        // Fast to html
        HtmlDocument fastHtml = fastFactory.makeHtml(md);
        fastHtml.save(Paths.get(".", "fast.html"));
        // Fast to word
        WordDocument fastWord = fastFactory.makeWord(md);
        fastWord.save(Paths.get(".", "fast.doc"));

        // Good Factory
//        AbstractFactory goodFactory = new GoodFactory();
        AbstractFactory goodFactory = AbstractFactory.createFactory("fast");
        // Good to html
        HtmlDocument goodHtml = goodFactory.makeHtml(md);
        goodHtml.save(Paths.get(".", "good.html"));
        // Good to word
        WordDocument goodWord = goodFactory.makeWord(md);
        goodWord.save(Paths.get(".", "good.doc"));
    }
}
