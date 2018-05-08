/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * IssueProcessingException
 * @author vminger
 *
 */
@ResponseStatus(code=HttpStatus.BAD_REQUEST, reason="Bad json instance")
public class IssueBadJsonException extends Exception {
	private static final long serialVersionUID = 5000000000000000002L;
}
