package com.design.patterns.creation.builder.user;

import lombok.Builder;

/**
 * @description: 使用 Lombok 的构建者模式
 * @author: lsrong
 * @date: 2022/10/8 11:02
 **/
@Builder
public class UserLombok {
    private String name;
    private String password;
    private int age;

    public String toString() {
        return String.format("UserLombokBuilder:name=%s, password=%s, age=%d", name, password, age);
    }
}
