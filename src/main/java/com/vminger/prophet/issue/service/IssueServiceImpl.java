/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
   * Add an issue.
   * @author VMINGER
   * @param issues issue json instance.
   */
  @Override
  public String createIssue(String issueInstance) {
    IssueEntity issueEntity;
    issueEntity = converter.createIssueEntityFromJson(issueInstance);
    dao.insert(issueEntity);
    return issueInstance;
  }

  @Override
  public String showIssue(String id) {
    IssueEntity issue = dao.findById(id);
    String json = converter.createJsonFromIssueEntity(issue);
    return json;
  }

  @Override
  public String updateIssue(String id, String issueInstance) {
    List<IssueEntity> issueEntities = dao.listAllIssues();
    String result = converter.createJsonFromIssueEntity(issueEntities);
    return result;
  }

  @Override
  public String deleteIssue(String id) {
    dao.deleteById(id);
  }

  @Override
  public String listIssues() {
    dao.listAllIssues();
  }

}