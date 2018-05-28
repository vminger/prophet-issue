/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.service;

public interface IssueService {

  public String createIssue(String issueInstance);
  
  public String showIssue(String id);
  
  public String updateIssue(String issueInstance);
  
  public String deleteIssue(String id);
  
  public String listIssues();
  
  public String listIssuesByUserId(String id);

}