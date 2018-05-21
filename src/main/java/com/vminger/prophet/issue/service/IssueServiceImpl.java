/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vminger.prophet.issue.dao.IssueDao;
import com.vminger.prophet.issue.entity.IssueEntity;
import com.vminger.prophet.issue.factory.IssueFactory;

@Service("issueServiceImpl")
public class IssueServiceImpl implements IssueService {
  
  @Autowired
  private IssueDao issueDao;

  @Autowired
  IssueFactory issueFactory;
  
  /**
   * Add issue.
   * @author VMINGER
   * @param issues issue json instance.
   */
  public String addIssues(String issues) {
    IssueEntity issueEntity;
    issueEntity = issueFactory.factoryIssue(issues);
    issueDao.insert(issueEntity);
    
    return issues;
  }
  
  /**
   * List all issues.
   */
  public String listAllIssues() {
    List<IssueEntity> issueEntities = issueDao.listAllIssues();
    String result = issueFactory.issuesToString(issueEntities);
    return result;
  }

}