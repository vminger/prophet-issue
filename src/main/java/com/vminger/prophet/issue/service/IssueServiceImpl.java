/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.vminger.prophet.issue.dao.IssueDaoImpl;
import com.vminger.prophet.issue.entity.IssueEntity;

public class IssueServiceImpl implements IssueService {

  @Autowired
  private IssueDaoImpl issueDaoImpl;

  /**
   * Add issue.
   * @author VMINGER
   * @param issues issue json instance.
   */
  public String addIssues(String issues) {
    IssueEntity issue = new IssueEntity();
    issueDaoImpl.insert(issue);
    return issues;
  }

}