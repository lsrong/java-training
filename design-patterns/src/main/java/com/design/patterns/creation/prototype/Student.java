package com.design.patterns.creation.prototype;

/**
 * @description: 实现原型模式，Cloneable
 * @author: lsrong
 * @date: 2022/10/13 11:00
 **/
public class Student implements Cloneable{
    private int id;
    private String name;
    private int score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return String.format("{Student: id=%s, name=%s, score=%s}@%s", this.id, this.name, this.score,
                Integer.toHexString(hashCode()));
    }

    @Override
    public Student clone() {
        Student std = new Student();
        std.id = id;
        std.name = name;
        std.score = score;

        return std;
    }
}
