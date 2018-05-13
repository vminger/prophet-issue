/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vminger.prophet.issue.dao.IssueDaoImpl;
import com.vminger.prophet.issue.entity.IssueEntity;
import com.vminger.prophet.issue.factory.IssueFactory;

public class IssueServiceImpl implements IssueService {

  @Autowired
  private IssueFactory issueFactory;
  
  @Autowired
  private IssueDaoImpl issueDaoImpl;

  /**
   * Add issue.
   * @author VMINGER
   * @param issues issue json instance.
   */
  public String addIssues(String issues) {   
    List<IssueEntity> issueEntities = issueFactory.factoryIssues(issues);
    Iterator<IssueEntity> iterator = issueEntities.iterator();
    while (iterator.hasNext()) {
      issueDaoImpl.insert(iterator.next());
    }
    return issues;
  }

}