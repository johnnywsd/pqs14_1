package com.shoudaw.pqs.sp14_sp1.addressbook;

public class InvalidPhone extends Exception {

  private static final long serialVersionUID = -6366881562625371124L;

  public InvalidPhone() {
  }

  public InvalidPhone(String message) {
    super(message);
  }

  public InvalidPhone(Throwable cause) {
    super(cause);
  }

  public InvalidPhone(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidPhone(String message, Throwable cause,
      boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
