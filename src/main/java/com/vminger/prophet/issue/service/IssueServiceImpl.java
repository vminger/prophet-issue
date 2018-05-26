/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vminger.prophet.issue.converter.IssueConverter;
import com.vminger.prophet.issue.repo.IssueDao;
import com.vminger.prophet.issue.repo.drivers.mongodb.entity.IssueEntityMongo;

@Service("issueServiceImpl")
public class IssueServiceImpl implements IssueService {
  
  @Autowired
  private IssueDao issueDao;

  @Autowired
  IssueConverter issueFactory;
  
  /**
   * Add issue.
   * @author VMINGER
   * @param issues issue json instance.
   */
  public String addIssues(String issues) {
    IssueEntityMongo issueEntity;
    issueEntity = issueFactory.createIssueEntityFromJson(issues);
    issueDao.insert(issueEntity);
    
    return issues;
  }
  
  /**
   * List all issues.
   */
  public String listAllIssues() {
    List<IssueEntityMongo> issueEntities = issueDao.listAllIssues();
    String result = issueFactory.createJsonFromIssueEntity(issueEntities);
    return result;
  }

}