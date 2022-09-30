package com.design.patterns.creation.builder;

import com.design.patterns.creation.builder.user.UserLombokBuilder;

public class BuilderApp {
    public static void main(String[] args){
        UserLombokBuilder user1 = UserLombokBuilder.builder()
                .name("builder")
                .nickname("lombok builder")
                .password("pwd123")
                .age(18)
                .build();
        System.out.println(user1);
    }
}
