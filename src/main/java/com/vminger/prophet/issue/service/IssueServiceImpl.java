/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vminger.prophet.issue.converter.IssueConverter;
import com.vminger.prophet.issue.repo.IssueDao;
import com.vminger.prophet.issue.repo.IssueEntity;

@Service("issueServiceImpl")
public class IssueServiceImpl implements IssueService {
  
  private static final Logger logger = LogManager.getLogger(IssueServiceImpl.class);
  
  @Autowired
  private IssueDao repo;

  @Autowired
  IssueConverter converter;
  
  /**
   * Add an issue by json instance.
   * @author VMINGER
   * @param issueInstance issue json instance
   * @return result
   */
  @Override
  public String createIssue(String issueInstance) {
    
    String result;
    
    logger.debug("Start to create issue");
    
    IssueEntity issueEntity =
        converter.createIssueEntityFromJson(issueInstance);
    
    try {
      
      repo.create(issueEntity);
      
      IssueEntity repoIssue = repo.findByIssueId(issueEntity.getContextId());
      result = converter.createJsonFromIssueEntity(repoIssue);
      
      logger.info("Create an issue successfully");
      
    } catch (Exception ex) {
      
      result = "Create an issue failed.\n";
      result += ex.getMessage();
      
      logger.error(result);
      
    }
    
    logger.debug("End to create issue");
    
    return result;
  
  }

  /**
   * Show an issue by id.
   * @author VMINGER
   * @param id issue id
   * @return issue json instance
   */
  @Override
  public String showIssue(String id) {
    
    logger.debug("Start to show an issue with id = " + id);
    
    String jsonInstance;
    
    try {
      IssueEntity issueEntity = repo.findByIssueId(id);
      jsonInstance = converter.createJsonFromIssueEntity(issueEntity);
      logger.debug("Success to show an issue with id = " + id);
    } catch (Exception ex) {
      jsonInstance = ex.getMessage();
      logger.error("Failed to show an issue with id = " + id);
      logger.error(ex.getStackTrace().toString());
    }
    
    logger.debug("End to show an issue with id = " + id);
    
    return jsonInstance;
  }

  @Override
  public String updateIssue(String issueInstance) {
    IssueEntity issueEntity =
        converter.createIssueEntityFromJson(issueInstance);
    
    String result;
    try {
      result = repo.update(issueEntity);
    } catch (Exception ex) {
      result = ex.getStackTrace().toString();
    }
    
    return result;
  }

  /**
   * Delete an issue by id.
   * @author VMINGER
   * @param id issue id
   * @return result
   */
  @Override
  public String deleteIssue(String id) {
    
    logger.debug("Start to delete an issue with id = " + id);
    
    String result;
    
    try {
      result = repo.deleteByIssueId(id);
      logger.debug("Success to delete an issue with id = " + id);
    } catch (Exception ex) {
      result = ex.getMessage();
      logger.error("Failed to delete an issue with id = " + id);
      logger.error(ex.getStackTrace().toString());
    }
    
    logger.debug("End to delete an issue with id = " + id);
    
    return result;
  }

  /**
   * List issues.
   * @author VMINGER
   * @return result
   */
  @Override
  public String listIssues() {
    List<IssueEntity> issueEntities;
    String result;
    
    try {
      issueEntities = repo.listIssues();
      result = converter.createJsonFromIssueEntity(issueEntities);
    } catch (Exception ex) {
      result = ex.getStackTrace().toString();
    }
    
    return result;
  }
  
  /**
   * List issues by user id.
   * @param userId user id
   * @return result
   */
  @Override
  public String listIssuesByUserId(String userId) {
    List<IssueEntity> issueEntities;
    String result;
    
    try {
      issueEntities = repo.findByUserId(userId);
      result = converter.createJsonFromIssueEntity(issueEntities);
    } catch (Exception ex) {
      result = ex.getStackTrace().toString();
    }
    
    return result;
  }

}