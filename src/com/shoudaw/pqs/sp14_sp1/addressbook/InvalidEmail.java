package com.shoudaw.pqs.sp14_sp1.addressbook;

public class InvalidEmail extends Exception {

  private static final long serialVersionUID = -4102293234737658184L;

  public InvalidEmail() {
  }

  public InvalidEmail(String message) {
    super(message);
  }

  public InvalidEmail(Throwable cause) {
    super(cause);
  }

  public InvalidEmail(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidEmail(String message, Throwable cause,
      boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
