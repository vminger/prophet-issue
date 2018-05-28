/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.ldap.DataLdapTest;
import org.springframework.stereotype.Service;

import com.vminger.prophet.issue.converter.IssueConverter;
import com.vminger.prophet.issue.repo.IssueDao;
import com.vminger.prophet.issue.repo.IssueEntity;

@Service("issueServiceImpl")
public class IssueServiceImpl implements IssueService {
  
  @Autowired
  private IssueDao dao;

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
    String result = "Create an issue successfully";
    
    IssueEntity issueEntity =
        converter.createIssueEntityFromJson(issueInstance);
    
    try {
      dao.create(issueEntity);     
    } catch (Exception ex) {
      result = "Create an issue failed.\n";
      result += ex.getStackTrace().toString();
    }
    
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
    IssueEntity issueEntity;
    String jsonInstance;
    
    try {
      issueEntity = dao.findByIssueId(id);
      jsonInstance = converter.createJsonFromIssueEntity(issueEntity);
    } catch (Exception ex) {
      jsonInstance = ex.getStackTrace().toString();
    }

    return jsonInstance;
  }

  @Override
  public String updateIssue(String issueInstance) {
    IssueEntity issueEntity =
        converter.createIssueEntityFromJson(issueInstance);
    
    String result;
    try {
      result = dao.update(issueEntity);
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
    String result;
    
    try {
      result = dao.deleteByIssueId(id);;
    } catch (Exception ex) {
      result = ex.getStackTrace().toString();
    }
    
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
      issueEntities = dao.listIssues();
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
      issueEntities = dao.findByUserId(userId);
      result = converter.createJsonFromIssueEntity(issueEntities);
    } catch (Exception ex) {
      result = ex.getStackTrace().toString();
    }
    
    return result;
  }

}