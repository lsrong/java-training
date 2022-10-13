package com.design.patterns.creation.builder;

import com.design.patterns.creation.builder.html.DocumentBuilder;
import com.design.patterns.creation.builder.html.HtmlBuilder;
import com.design.patterns.creation.builder.url.UrlBuilder;
import com.design.patterns.creation.builder.user.User;
import com.design.patterns.creation.builder.user.UserLombok;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args){
        demoUserBuilder();

        demoHtmlBuilder();
    }

    private static void demoUserBuilder(){
        // 自定义的Builder示例
        User user = User.builder()
                .name("builder-custom")
                .password("pwd123")
                .age(28)
                .build();
        System.out.println(user);

        // 使用lombok扩展包的builder示例
        UserLombok userLombok = UserLombok.builder()
                .name("builder-lombok")
                .password("pwd123")
                .age(18)
                .build();
        System.out.println(userLombok);
    }

    private static void demoHtmlBuilder(){
        // md 转为 html 格式
        String markdown = String.join("\n", // Markdown document
                "## Builder Pattern", // heading
                "> Seperate the construction of a complex object from its representation",
                "Use builder pattern when the construction process is complex.", "----", "Here is an example.");
        HtmlBuilder htmlBuilder = new HtmlBuilder();
        String html = htmlBuilder.build(markdown);
        System.out.println(html);

        // url builder
        UrlBuilder urlBuilder = new UrlBuilder();
        Map<String, String> q = new HashMap<>();
        q.put("a", "demo-test");
        q.put("b", "a&fds");
        String url = urlBuilder.scheme("https")
                .host("www.baidu.com")
                .port(443)
                .path("s")
                .query(q)
                .build();
        System.out.println(url);
    }
}
