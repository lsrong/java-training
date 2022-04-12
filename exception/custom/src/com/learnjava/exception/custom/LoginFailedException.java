package com.learnjava.exception.custom;

public class LoginFailedException extends BaseException {
  public LoginFailedException() {
    super();
  }

  public LoginFailedException(String message) {
    super(message);
  }

  public LoginFailedException(Throwable throwable) {
    super(throwable);
  }

  public LoginFailedException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
