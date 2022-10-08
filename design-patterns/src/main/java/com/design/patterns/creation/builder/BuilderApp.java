package com.design.patterns.creation.builder;

import com.design.patterns.creation.builder.user.User;
import com.design.patterns.creation.builder.user.UserLombok;

public class BuilderApp {
    public static void main(String[] args){
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
}
