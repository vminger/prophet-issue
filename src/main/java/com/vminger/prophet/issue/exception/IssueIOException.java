/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * IssueProcessingException.
 * @author vminger
 *
 */
public class IssueIOException extends IOException {

  private static final long serialVersionUID = -5989067792843400914L;
  
  public IssueIOException() {
  }
  
  public IssueIOException(String message, Throwable cause) {
    super(message, cause);      
  }

  public IssueIOException(String message) {
    super(message);     
  }

  public IssueIOException(Throwable cause) {
    super(cause);
  }

}
