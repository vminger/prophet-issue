/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.exception;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;

/**
 * IssueProcessingException.
 * @author vminger
 *
 */
public class IssueProcessingException extends ProcessingException {
  
  private static final long serialVersionUID = -1954867748012686779L;

  public IssueProcessingException() {
  }
  
  public IssueProcessingException(String message, Throwable cause) {
    super(message, cause);      
  }

  public IssueProcessingException(String message) {
    super(message);     
  }
  
}
