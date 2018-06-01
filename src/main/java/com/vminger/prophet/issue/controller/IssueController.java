/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.controller;

import java.io.IOException;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
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

  @ExceptionHandler({ IssueBadJsonException.class })
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ResponseBody
  public String handleIssueBadJsonException(Exception ex) {
    String view = viewer.errorView(ex.getMessage());
    return view;
  }
  
  @ExceptionHandler({ IssueProcessingException.class })
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ResponseBody
  public String handleIssueProcessingException(Exception ex) {
    String view = viewer.errorView(ex.getMessage());
    return view;
  }
  
  @ExceptionHandler({ IssueIOException.class })
  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public String handleIssueIOException(Exception ex) {
    String view = viewer.errorView("Bad json instance");
    return view;
  }
  
  /**
   * Add an issue.
   * @param issueInstance issue json instance
   * @return no return value
   * @throws IssueBadJsonException 4xx bad code for
   *         json instance does not match json schema.
   * @throws IssueProcessingException 4xx bad code for
   *         json schema or json instance error.
   * @throws IssueIOException 5xx bad code for json schema file error.
   */
  @RequestMapping(method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public String createIssue(@RequestBody String issueInstance)
      throws IssueBadJsonException, 
             IssueProcessingException,
             IssueIOException {
    logger.info("Start to create an issue from json instance");
    logger.debug(issueInstance);
    
    try {
      
      ProcessingReport report = IssueJsonSchemaValidator.validateByPath(
          IssueConstant.SCHEMA_ISSUE_IN_TEXT_PATH, issueInstance);
      
      if (!report.isSuccess()) {
        
        logger.error(report);
        
        String message = "";
        Iterator<ProcessingMessage> iterator = report.iterator();
        while (iterator.hasNext()) {
          message += iterator.next().getMessage() + "\n";
        }
        
        throw new IssueBadJsonException(message);
      }
      
      logger.debug("Validate json instance sucessfully");
      
    } catch (ProcessingException ex) {
      
      logger.error(ex.getStackTrace());
      throw new IssueProcessingException();
      
    } catch (IOException ex) {
      
      logger.error(ex.getStackTrace());
      throw new IssueIOException();
      
    }

    String result = service.createIssue(issueInstance);
    
    String view = viewer.createIssueView(result);
    
    logger.info("End to create an issue from json instance");
    logger.debug(view);
    
    return view;
  }
  
  /**
   * Show an issue by id.
   * @param id issue id
   * @return view for show and issue
   */
  @RequestMapping(value = "/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public String showIssue(@PathVariable String id) {
    
    logger.debug("Start to show and issue with id = " + id);
    
    String result = service.showIssue(id);
    
    String view = viewer.showIssueView(result);
    
    logger.debug("End to show and issue with id = " + id);
    
    return view;
  
  }
  
  /**
   * Update an issue.
   * @param id issue id
   * @return view for show and issue
   */
  @RequestMapping(value = "/{id}",
      method = RequestMethod.PUT,
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public String updateIssue(@PathVariable String id,
      @RequestBody String issueInstance)
          throws IssueBadJsonException,
          IssueProcessingException,
          IssueIOException {
    
    logger.debug("Start to update an issue with id = " + id);
    logger.debug(issueInstance);
    
    try {
      
      ProcessingReport report = IssueJsonSchemaValidator.validateByPath(
          IssueConstant.SCHEMA_ISSUE_IN_TEXT_PATH, issueInstance);
      
      if (!report.isSuccess()) {
        
        logger.error(report);
        
        String message = "";
        Iterator<ProcessingMessage> iterator = report.iterator();
        while (iterator.hasNext()) {
          message += iterator.next().getMessage() + "\n";
        }
        
        throw new IssueBadJsonException(message);
      }
      
      logger.debug("Validate json instance sucessfully");
      
    } catch (ProcessingException ex) {
      
      logger.error(ex.getStackTrace());
      throw new IssueProcessingException();
      
    } catch (IOException ex) {
      
      logger.error(ex.getStackTrace());
      throw new IssueIOException();
      
    }
    
    String result = service.updateIssue(id, issueInstance);
    
    String view = viewer.updateIssueView(result);
    
    logger.debug("End to update an issue with id = " + id);
    
    return view;
  
  }
  
  /**
   * Delete an issue by id.
   * @param id issue id
   * @return view for delete and issue
   */
  @RequestMapping(value = "/{id}",
      method = RequestMethod.DELETE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public String deleteIssue(@PathVariable String id) {
    
    logger.debug("Start to update an issue with id = " + id);
    
    String result = service.deleteIssue(id);
    
    String view = viewer.deleteIssueView(result);
    
    logger.debug("End to update an issue with id = " + id);
    
    return view;
  
  }
  
  /**
   * List issues.
   * @return view for list issues
   */
  @RequestMapping(method = RequestMethod.GET,
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