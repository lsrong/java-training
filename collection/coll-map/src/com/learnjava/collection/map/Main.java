package com.learnjava.collection.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HashMap
 *
 * @author lsrong
 */
public class Main {
  public static void main(String[] args) {
    List<Student> list = new ArrayList<>();
    list.add(new Student("Bob", 78));
    list.add(new Student("Alice", 85));
    list.add(new Student("Brush", 66));
    list.add(new Student("Newton", 99));

    Students holder = new Students(list);
    System.out.println(holder.getScore("Bob") == 78 ? "测试成功!" : "测试失败!");
    System.out.println(holder.getScore("Alice") == 85 ? "测试成功!" : "测试失败!");
    System.out.println(holder.getScore("Tom") == -1 ? "测试成功!" : "测试失败!");
  }
}

class Students {
  List<Student> list;

  // 利用Map充当缓存，以提高查找效率
  Map<String, Integer> cache;

  Students(List<Student> list) {
    this.list = list;
    cache = new HashMap<>();
  }

  int getScore(String name) {
    // 先在Map中查找:
    Integer score = this.cache.get(name);
    if (score == null) {
      // 利用Map充当缓存，以提高查找效率
      score = findInList(name);
      this.cache.put(name, score);
    }
    return score == null ? -1 : score;
  }

  Integer findInList(String name) {
    for (Student ss : this.list) {
      if (ss.name.equals(name)) {
        return ss.score;
      }
    }
    return null;
  }
}

class Student {
  String name;
  int score;

  Student(String name, int score) {
    this.name = name;
    this.score = score;
  }
}
