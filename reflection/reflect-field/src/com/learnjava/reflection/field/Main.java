package com.learnjava.reflection.field;

import java.lang.reflect.Field;

/**
 * Reflection Field
 *
 * @author lsrong
 */
public class Main {

  public static void main(String[] args) {
    try {
      String name = "Xiao Ming";
      int age = 20;
      Person p = new Person();
      // 利用反射给name和age字段赋值:
      // 字段
      Field n = p.getClass().getDeclaredField("name");
      n.setAccessible(true);
      n.set(p, name);

      Field a = p.getClass().getDeclaredField("age");
      a.setAccessible(true);
      a.setInt(p, age);

      System.out.println(p.getName()); // "Xiao Ming"
      System.out.println(p.getAge()); // 20

    } catch (NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }
}
