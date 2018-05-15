/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.vminger.prophet.issue.entity.IssueEntity;
import com.vminger.prophet.issue.recomend.TagFactory;
import com.vminger.prophet.issue.util.UniDatetime;

@Component("issueFactory")
public class IssueFactory {

  public IssueFactory() {  
  }
  
  /**
   * Build issue entities from issue json instances.
   * @param issueInstances issue json instances
   * @return issueEntities
   */
  public List<IssueEntity> factoryIssues(String issueInstances) {
        
    JSONObject jsonObject = new JSONObject(issueInstances);
    
    JSONObject issueInText = (JSONObject)jsonObject.get("issue-in-text");
    
    JSONArray ctxs = new JSONArray(issueInText.get("ctxs"));
    Map<String, String> ctxsMap = new HashMap<String, String>();
    for (int i = 0; i < ctxs.length(); i++) {
      JSONObject ctx = (JSONObject)ctxs.get(i);
      String ctxIdKey = ctx.get("ctx_id").toString();
      String ctxVal = ctx.get("ctx").toString();
      ctxsMap.put(ctxIdKey, ctxVal);
    }
    
    JSONArray jsonIssues = new JSONArray(issueInText.get("issues"));
    List<IssueEntity> issueEntities = new ArrayList<IssueEntity>();
    for (int i = 0; i < jsonIssues.length(); i++) {
      JSONObject jsonIssue = (JSONObject)jsonIssues.get(i);
      
      String issueId = UUID.randomUUID().toString();
      
      String ctxId = jsonIssue.get("ctx_id").toString();
      String issueCtx = null;
      if (ctxId != null) {
        issueCtx = ctxsMap.get(ctxId);
      }
      
      String issueQuestion = jsonIssue.get("question").toString();
      
      Map<String, String> issueOptions = new HashMap<String, String>();
      JSONArray jsonOptions = new JSONArray(jsonIssue.get("options"));
      for (int j = 0; j < jsonOptions.length(); j++) {
        JSONObject jsonOption = new JSONObject(jsonOptions.get(j));
        String issueOption = jsonOption.get("option").toString();
        String issueIsAnswer = jsonOption.get("is_answer").toString();
        issueOptions.put(issueOption, issueIsAnswer);
      }
      
      String issueK12n = jsonIssue.get("k12n").toString();
      String issueSubject = jsonIssue.get("subject").toString();
      String issueType = jsonIssue.get("type").toString();
      float issueDod = jsonIssue.getFloat("dod");
      String issueCreatedAt = UniDatetime.getDatetime();
      String issueUpdatedAt = UniDatetime.getDatetime();
      
      IssueEntity issueEntity = new IssueEntity();
      issueEntity.setId(issueId);
      issueEntity.setContext(issueCtx);
      issueEntity.setQuestion(issueQuestion);
      issueEntity.setOptions(issueOptions);
      issueEntity.setK12n(issueK12n);
      issueEntity.setSubject(issueSubject);
      issueEntity.setType(issueType);
      issueEntity.setDod(issueDod);
      issueEntity.setCreatedAt(issueCreatedAt);
      issueEntity.setUpdatedAt(issueUpdatedAt);
      List<String> tags = TagFactory.getTags(issueEntity);
      for (int j = 0; j < tags.size(); j++) {
        issueEntity.addTag(tags.get(j));
      }
      issueEntities.add(issueEntity);
    }
    
    return issueEntities;
  }
  
  public IssueEntity factoryIssue(String issue) {
    IssueEntity issueEntity = null;
    return issueEntity;
  }
}