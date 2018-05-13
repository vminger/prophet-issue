/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.factory;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.vminger.prophet.issue.entity.IssueEntity;

@Component("issueFactory")
public class IssueFactory {

  public IssueFactory() {  
  }
  
  public List<IssueEntity> factoryIssues(String issueInstances) {
    List<IssueEntity> issueEntities = new ArrayList<IssueEntity>();
    
    JSONObject jsonObject = new JSONObject(issueInstances);
    
    JSONObject issueInText = (JSONObject)jsonObject.get("issue-in-text");
    
    JSONArray ctxs = new JSONArray(issueInText.get("ctxs"));
    
    JSONArray issues = new JSONArray(issueInText.get("issues"));
    
    return issueEntities;
  }
  
  public IssueEntity factoryIssue(String issue) {
    IssueEntity issueEntity = null;
    return issueEntity;
  }
}
