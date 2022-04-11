package com.learnjava.expectionthrow;

/**
 * Exception throw.
 *
 * @author lsrong
 */
public class Main {

  public static void main(String[] args) {
    try {
      System.out.println(tax(2000, 0.1));
    } catch (IllegalArgException e) {
      System.out.println(e.getStringTrace());
    }

    try {
      System.out.println(tax(-200, 0.1));
    } catch (IllegalArgException e) {
      System.out.println(e.getStringTrace());
    }

    try {

      System.out.println(tax(2000, -0.1));
    } catch (IllegalArgException e) {
      System.out.println(e.getStringTrace());
    }
  }

  static double tax(int salary, double rate) {
    // 如果传入的参数为负，则抛出自定义的 IllegalArgException
    if (salary < 0 || rate < 0) {
      throw new IllegalArgException("tax 参数不能为负数!");
    }

    return salary * rate;
  }
}

class IllegalArgException extends RuntimeException {
  public IllegalArgException() {
    super();
  }

  public IllegalArgException(String message) {
    super(message);
  }

  public IllegalArgException(String message, Throwable throwable) {
    super(message, throwable);
  }

  public IllegalArgException(Throwable throwable) {
    super(throwable);
  }

  public String getStringTrace() {
    StringBuilder trace = new StringBuilder();
    for (StackTraceElement st : this.getStackTrace()) {
      trace.append(st.toString());
      trace.append("\n");
    }
    return trace.toString();
  }
}
