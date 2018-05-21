/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.factory;

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

@Component
public class IssueFactory {

  public IssueFactory() {  
  }
  
  /**
   * Build issue entity from issue json instance.
   * @param issueInstance issue json instance
   * @return issueEntities
   */
  public IssueEntity factoryIssue(String issueInstance) {

    IssueEntity issueEntity = new IssueEntity();
    
    JSONObject jsonObject = new JSONObject(issueInstance);
    
    JSONObject jsonIssueInText = jsonObject.getJSONObject("issues_in_text");
    
    String contextId = UUID.randomUUID().toString();
    issueEntity.setContextId(contextId);
    
    String context = jsonIssueInText.getString("context");
    issueEntity.setContext(context);
    
    String k12n = jsonIssueInText.getString("k12n");
    issueEntity.setK12n(k12n);
    
    String subject = jsonIssueInText.getString("subject");
    issueEntity.setSubject(subject);
    
    float dod = jsonIssueInText.getFloat("dod");
    issueEntity.setDod(dod);
    
    String type = jsonIssueInText.getString("type");
    issueEntity.setType(type);
    
    Map<String, String> qasQuestion = new HashMap<String, String>();
    
    Map<String, Map<String, String>> qasOptions =
        new HashMap<String, Map<String, String>>();
    
    JSONArray jsonQas = jsonIssueInText.getJSONArray("qas");
    for (int i = 0; i < jsonQas.length(); i++) {
      JSONObject jsonQa = jsonQas.getJSONObject(i);
      
      String questionId = UUID.randomUUID().toString();
      questionId.replace(".", "^_$");
      
      String question = jsonQa.getString("question");
      qasQuestion.put(questionId, question);
      
      Map<String, String> options = new HashMap<String, String>();
      JSONArray jsonOptions = jsonQa.getJSONArray("options");
      for (int j = 0; j < jsonOptions.length(); j++) {
        JSONObject jsonOption = jsonOptions.getJSONObject(j);
        String option = jsonOption.getString("option");
        option = option.replace(".", "^_$");
        String answer = jsonOption.getString("answer");
        options.put(option, answer);
      }
      qasOptions.put(questionId, options);
    }
    issueEntity.setQasQuestion(qasQuestion);
    issueEntity.setQasOptions(qasOptions);
    
    String issueCreatedAt = UniDatetime.getDatetime();
    issueEntity.setCreatedAt(issueCreatedAt);
    
    String issueUpdatedAt = UniDatetime.getDatetime();
    issueEntity.setUpdatedAt(issueUpdatedAt);
    
    List<String> tags = TagFactory.getTags(issueEntity);
    for (int j = 0; j < tags.size(); j++) {
      issueEntity.addTag(tags.get(j));
    }
    
    return issueEntity;
  }
  
  /**
   * Covert issue entity to string.
   * @param issueEntities issue entities
   * @return issue entities json
   */
  public String issuesToString(List<IssueEntity> issueEntities) {
    String jsons = "";
    
    for (int i = 0; i < issueEntities.size(); i++) {
      IssueEntity issueEntity = issueEntities.get(i);
      String json = issueEntity.toString();
      jsons += json;
    }
    
    return jsons;
  }
}