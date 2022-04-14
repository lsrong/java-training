package com.learnjava.collection.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Collection List
 *
 * @author lsrong
 */
public class Main {
  public static void main(String[] args) {
    final int start = 10;
    final int end = 20;

    List<Integer> list = createList(start, end);
    // 洗牌算法suffle可以随机交换List中的元素位置:
    Collections.shuffle(list);
    // 随机删除List中的一个元素:
    int removed = list.remove((int) (Math.random() * list.size()));

    // 尝试找出缺失的数字
    int found = findMissingNumber(start, end, list);

    System.out.println(list.toString());
    System.out.println("missing number: " + found);
    System.out.println(removed == found ? "测试成功" : "测试失败"); // com.learnjava.collection.list
  }

  /** TODO: 给定一组连续的整数，例如：10，11，12，……，20，但其中缺失一个数字，试找出缺失的数字 */
  static int findMissingNumber(int start, int end, List<Integer> list) {
    List<Integer> orgList = createList(start, end);
    /*
     * for (Iterator<Integer> it = list.iterator(); it.hasNext(); ) { Integer i = it.next();
     * orgList.remove(i); }
     */

    for (Integer i : list) {
      orgList.remove(i);
    }

    return orgList.get(0);
  }

  // 构造从start到end的序列的集合
  static List<Integer> createList(int start, int end) {
    List<Integer> list = new ArrayList<>();
    for (int i = start; i <= end; i++) {
      list.add(i);
    }

    return list;
  }
}
