package com.learnjava.reflection.method;

import java.lang.reflect.Method;

/**
 * Reflection Method.
 *
 * @author lsrong
 */
public class Main {

  public static void main(String[] args) throws Exception {
    String name = "Xiao Ming";
    int age = 20;
    Person p = new Person();

    // 利用反射调用setName和setAge方法:
    Method sn = p.getClass().getDeclaredMethod("setName", String.class);
    sn.invoke(p, name);

    Method sa = p.getClass().getDeclaredMethod("setAge", int.class);
    sa.invoke(p, age);

    System.out.println(p.getName()); // "Xiao Ming"
    System.out.println(p.getAge()); // 20
  }
}
