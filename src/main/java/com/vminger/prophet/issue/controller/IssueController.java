/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.vminger.prophet.issue.viewer.IssueViewer;

@RestController
@RequestMapping("/v1.0/issues")
public class IssueController {

  private static final Logger logger = LogManager.getLogger(IssueController.class);

  @Autowired
  private IssueService service;
  
  @Autowired
  private IssueViewer issueViewer;

  /**
   * Controller for add issues.
   * @param issues issue json instance
   * @return no return value
   * @throws IssueBadJsonException 4xx bad code
   * @throws IssueProcessingException 4xx bad code
   * @throws IssueIOException 5xx bad code
   */
  @RequestMapping(method = RequestMethod.POST,
      consumes = "application/json;charset=UTF-8",
      produces = "application/json;charset=UTF-8")
  @ResponseBody
  public String createIssues(@RequestBody String issueInstance)
      throws IssueBadJsonException, 
      IssueProcessingException,
      IssueIOException {

    logger.info("Start to create an issue from json instance");
    logger.info(issueInstance);
    
    try {
      ProcessingReport report = IssueJsonSchemaValidator.validateByPath(
          IssueConstant.SCHEMA_ISSUE_IN_TEXT_PATH, issueInstance);
      if (!report.isSuccess()) {
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

    String result = service.addIssues(issueInstance);

    logger.info("End to create an issue from json instance");
    
    return result;

  }
  
  /**
   * List all issues controller.
   * @return list all issues viewer
   */
  @RequestMapping(method = RequestMethod.GET,
      produces = "application/json;charset=UTF-8")
  @ResponseBody
  public String listAllIssues() {
    
    String result = service.listAllIssues();
    
    String viewer = issueViewer.listAllIssuesViewer(result);
    
    return viewer;
  }
}