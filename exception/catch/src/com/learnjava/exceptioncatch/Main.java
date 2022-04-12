package com.learnjava.exceptioncatch;

/**
 * Exception Catch.
 *
 * @author lsrong
 */
public class Main {

  public static void main(String[] args) {
    String a = "12";
    String b = "x9";
    // 捕获异常并处理.
    try {
      int c = stringToInt(a);
      int d = stringToInt(b);
      System.out.println(c * d);
    } catch (NumberFormatException e) {
      System.out.println(e.getMessage());
      // trace
      //      for (StackTraceElement st : e.getStackTrace()) {
      //        System.out.println(st.toString());
      //      }

      e.printStackTrace();
    } catch (Exception e) {
      System.out.println("Unknown error");
    } finally {
      System.out.println("do finally job!");
    }
  }

  static int stringToInt(String s) {
    return Integer.parseInt(s);
  }
}
