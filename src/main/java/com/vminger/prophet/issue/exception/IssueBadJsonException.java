/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.exception;

/**
 * IssueBadJsonException.
 * @author vminger
 *
 */
public class IssueBadJsonException extends Exception {
  
  private static final long serialVersionUID = 8054782616122083853L;
  
  public IssueBadJsonException() {
  }
  
  public IssueBadJsonException(String message, Throwable cause) {
    super(message, cause);      
  }

  public IssueBadJsonException(String message) {
    super(message);     
  }

  public IssueBadJsonException(Throwable cause) {
    super(cause);
  }

}
