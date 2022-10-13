package com.design.patterns.creation.prototype;


public class Main {
    public static void main(String[] args){
        Student std1 = new Student();
        std1.setId(123);
        std1.setName("Bob");
        std1.setScore(88);
        // 复制新对象:
        Student std2 = std1.clone();
        System.out.println(std1);
        System.out.println(std2);
        System.out.println(std1 == std2); // false
    }
}
