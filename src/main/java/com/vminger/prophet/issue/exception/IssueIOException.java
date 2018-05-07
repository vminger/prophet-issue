package com.vminger.prophet.issue.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * IssueProcessingException
 * @author vminger
 *
 */
@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR, reason="Bad json schema")
public class IssueIOException extends IOException {
	private static final long serialVersionUID = 5000000000000000003L;
}
