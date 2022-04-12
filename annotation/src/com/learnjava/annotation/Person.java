package com.learnjava.annotation;

public class Person {

  @com.learnjava.annotation.Range(min = 1, max = 20)
  public String name;

  @com.learnjava.annotation.Range(max = 10)
  public String city;

  @com.learnjava.annotation.Range(min = 1, max = 100)
  public int age;

  public Person(String name, String city, int age) {
    this.name = name;
    this.city = city;
    this.age = age;
  }

  @Override
  public String toString() {
    return String.format("{Person: name=%s, city=%s, age=%d}", name, city, age);
  }
}
