package com.design.patterns.creation.builder.user;

/**
 * @description: 简单实现Builder生产器模式示例
 * @author: lsrong
 * @date: 2022/10/8 11:04
 **/
public class User {
    // 用户名私有属性
    private String name;
    private String password;
    private Integer age;

    private User(String name, String password, Integer age){
        this.name = name;
        this.password = password;
        this.age = age;
    }

    public static UserBuilder builder(){
        return new UserBuilder();
    }

    public static class UserBuilder{
        private String name;
        private String password;
        private Integer age;

        private UserBuilder(){
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder password(String pwd) {
            this.password = pwd;
            return this;
        }

        public UserBuilder age(Integer age) {
            this.age = age;
            return this;
        }

        public User build(){
            if(name == null || password == null) {
                throw new RuntimeException("用户名和密码必须");
            }

            if(age <= 0 || age >= 150) {
                throw new RuntimeException("年龄不合法");
            }

            return new User(name, password, age);
        }
    }
}
