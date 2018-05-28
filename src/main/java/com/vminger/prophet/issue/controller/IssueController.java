/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
  private IssueViewer viewer;

  /**
   * Add an issue.
   * @param issueInstance issue json instance
   * @return no return value
   * @throws IssueBadJsonException 4xx bad code
   * @throws IssueProcessingException 4xx bad code
   * @throws IssueIOException 5xx bad code
   */
  @RequestMapping(method = RequestMethod.POST,
      consumes = "application/json;charset=UTF-8",
      produces = "application/json;charset=UTF-8")
  @ResponseBody
  public String createIssue(@RequestBody String issueInstance)
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

    String result = service.createIssue(issueInstance);

    logger.info("End to create an issue from json instance");
    
    String view = viewer.createIssueView(result);
    
    return view;

  }
  
  /**
   * Show an issue by id.
   * @param id issue id
   * @return view for show and issue
   */
  @RequestMapping(value = "/{id}",
      method = RequestMethod.GET,
      produces = "application/json;charset=UTF-8")
  @ResponseBody
  public String showIssue(@PathVariable String id) {
    String result = service.showIssue(id);
    String view = viewer.showIssueView(result);
    return view;
  }
  
  /**
   * Update an issue.
   * @param id issue id
   * @return view for show and issue
   */
  @RequestMapping(value = "/{id}",
      method = RequestMethod.POST,
      consumes = "application/json;charset=UTF-8",
      produces = "application/json;charset=UTF-8")
  @ResponseBody
  public String updateIssue(@PathVariable String id, @RequestBody String issueInstance) {
    
    String result = service.updateIssue(issueInstance);
    
    String view = viewer.updateIssueView(result);
    
    return view;
  }
  
  /**
   * Delete an issue by id.
   * @param id issue id
   * @return view for delete and issue
   */
  @RequestMapping(value = "/{id}",
      method = RequestMethod.DELETE,
      produces = "application/json;charset=UTF-8")
  @ResponseBody
  public String deleteIssue(@PathVariable String id) {
    
    String result = service.deleteIssue(id);
    
    String view = viewer.deleteIssueView(result);
    
    return view;
  }
  
  /**
   * List issues.
   * @return view for list issues
   */
  @RequestMapping(method = RequestMethod.POST,
      produces = "application/json;charset=UTF-8")
  @ResponseBody
  public String listIssues() {
    
    String result = service.listIssues();
    
    String view = viewer.listIssuesView(result);
    
    return view;
  }

  /**
   * List issues by user id.
   * @param id user id
   * @return view for list issues by user id
   */
  @RequestMapping(
      value = "/user/{id}",
      method = RequestMethod.GET)
  @ResponseBody
  public String listIssuesByUserId(@PathVariable String id) {
    
    String result = service.listIssuesByUserId(id);
    
    String view = viewer.listIssuesByUserIdView(result);
    
    return view;
    
  }
}