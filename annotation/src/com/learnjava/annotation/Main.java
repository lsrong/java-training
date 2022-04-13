package com.learnjava.annotation;

import java.lang.reflect.Field;

/**
 * Annotation practice
 *
 * @author lsrong
 */
public class Main {

  public static void main(String[] args) throws Exception {
    Person p1 = new Person("Bob", "Beijing", 20);
    Person p2 = new Person("", "Shanghai", 20);
    Person p3 = new Person("Alice", "Shanghai", 199);
    for (Person p : new Person[] {p1, p2, p3}) {
      try {
        check(p);
        System.out.println("Person " + p + " checked ok.");
      } catch (IllegalArgumentException e) {
        System.out.println("Person " + p + " checked failed: " + e);
      }
    }
  }

  // 使用@Range注解来检查Java Bean的字段。如果字段类型是String，就检查String的长度，如果字段是int，就检查int的范围。
  static void check(Person person) throws IllegalArgumentException, ReflectiveOperationException {

    Field[] fields = person.getClass().getFields();
    for (Field field : fields) {
      Range range = field.getAnnotation(Range.class);
      if (range == null) {
        continue;
      }

      Object value = field.get(person);
      // 字符串检测长度
      if (value instanceof String) {
        String s = (String) value;
        if (s.length() < range.min() || s.length() > range.max()) {
          throw new IllegalArgumentException("Invalid field: " + field.getName());
        }
      }

      // 数值检测大小
      if (value instanceof Integer) {
        int i = (int) value;
        if (i < range.min() || i > range.max()) {
          throw new IllegalArgumentException("Invalid field: " + field.getName());
        }
      }
    }
  }
}
