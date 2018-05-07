/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.vminger.prophet.issue.constant.IssueConstant;
import com.vminger.prophet.issue.exception.IssueBadJsonException;
import com.vminger.prophet.issue.exception.IssueIOException;
import com.vminger.prophet.issue.exception.IssueProcessingException;
import com.vminger.prophet.issue.service.IssueService;
import com.vminger.prophet.issue.validation.IssueJsonSchemaValidator;

@RestController
@RequestMapping("/v1.0/issues")
public class IssueController {
	
	private static final Logger logger = LogManager.getLogger(IssueController.class);
	
	private IssueService service = null;
	
	@RequestMapping(method = RequestMethod.POST,
					consumes="application/json;charset=UTF-8",
					produces="application/json;charset=UTF-8")
	@ResponseBody
	public String addIssue(@RequestBody String issues)
			throws IssueBadJsonException,
				IssueProcessingException,
				IssueIOException {
		
		try {
			logger.warn(issues);
			ProcessingReport report = IssueJsonSchemaValidator.validateByPath(
					IssueConstant.SCHEMA_ISSUE_IN_TEXT_PATH,
					issues);
			if(!report.isSuccess()) {
				logger.warn(report);
				throw new IssueBadJsonException();
			}
		} catch (ProcessingException ex) {
			logger.warn(ex.getStackTrace());
			throw new IssueProcessingException();
		} catch (IOException ex) {
			logger.warn(ex.getStackTrace());
			throw new IssueIOException();
		}
		
		logger.info(issues);
		
		service.addIssue(issues);
		
		return issues;
		
	}
}
