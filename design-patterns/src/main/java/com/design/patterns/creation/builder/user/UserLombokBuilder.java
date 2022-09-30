package com.design.patterns.creation.builder.user;

import lombok.Builder;

@Builder
public class UserLombokBuilder {
    private String name;

    private String password;

    private String nickname;

    private int age;

    public String toString() {
        return String.format("UserLombokBuilder:name=%s, password=%s, nickname=%s, age=%d", name, password, nickname, age);
    }
}
