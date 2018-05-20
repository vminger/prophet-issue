/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vminger.prophet.issue.dao.IssueDaoImpl;
import com.vminger.prophet.issue.entity.IssueEntity;
import com.vminger.prophet.issue.factory.IssueFactory;

@Service("issueServiceImpl")
public class IssueServiceImpl implements IssueService {
  
  @Autowired
  private IssueDaoImpl issueDaoImpl;

  /**
   * Add issue.
   * @author VMINGER
   * @param issues issue json instance.
   */
  public String addIssues(String issues) {
    IssueFactory issueFactory = new IssueFactory();
    IssueEntity issueEntity;
    
    issueEntity = issueFactory.factoryIssue(issues);
    issueDaoImpl.insert(issueEntity);
    
    return issues;
  }

}