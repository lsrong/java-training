package com.learnjava.exceptioncustom;

public class UserNotFoundException extends BaseException {
  public UserNotFoundException() {
    super();
  }

  public UserNotFoundException(String message) {
    super(message);
  }

  public UserNotFoundException(Throwable throwable) {
    super(throwable);
  }

  public UserNotFoundException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
