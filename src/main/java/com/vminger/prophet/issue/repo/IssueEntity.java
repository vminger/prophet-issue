/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.repo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class IssueEntity implements Serializable, Comparable<IssueEntity> {

  private static final long serialVersionUID = -748388722236389944L;
  
  private String contextId;
  private String context;
  private String k12n;
  private String subject;
  private float dod;
  private String type;
  private Map<String, String> qasQuestion;
  private Map<String, Map<String, String>> qasOptions;
  private String createdAt;
  private String updatedAt;
  private String userId;
  private String fromUrl;
  private List<String> tags;

  public IssueEntity() {
    tags = new LinkedList<String>();
  }

  /**
   * Issue Entity.
   * @param contextId uuid4 for issue entity
   * @param context context for issue entity
   * @param k12n 010-1年级上学期, 011-1年级下学期
   * @param subject 000-语文, 001-数学
   * @param type 000-单选, 001-多选
   * @param qasQuestion question
   * @param qasOptions options and answers
   * @param createdAt created time
   * @param updatedAt updated time
   * @param userId uuid4 for user
   * @param fromUrl 爬虫爬取的原始url，user_id和url二选一
   * @param dod 0-100，n表示有n%的人不会做，数值越大，难度越大
   */
  public IssueEntity(String contextId, String context, String k12n,
      String subject, float dod, String type, Map<String, String> qasQuestion,
      Map<String, Map<String, String>> qasOptions, String createdAt,
      String updatedAt, String userId, String fromUrl) {
    super();
    this.contextId = contextId;
    this.context = context;
    this.k12n = k12n;
    this.subject = subject;
    this.dod = dod;
    this.type = type;
    this.qasQuestion = qasQuestion;
    this.qasOptions = qasOptions;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.userId = userId;
    this.fromUrl = fromUrl;
  }

  public String getContextId() {
    return contextId;
  }

  public void setContextId(String contextId) {
    this.contextId = contextId;
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }
  
  public String getK12n() {
    return k12n;
  }

  public void setK12n(String k12n) {
    this.k12n = k12n;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }
  
  public float getDod() {
    return dod;
  }

  public void setDod(float dod) {
    this.dod = dod;
  }
  
  public String getType() {
    return type;
  }
  
  public void setType(String type) {
    this.type = type;
  }

  public Map<String, String> getQasQuestion() {
    return qasQuestion;
  }
  
  public void setQasQuestion(Map<String, String> qasQuestion) {
    this.qasQuestion = qasQuestion;
  }
  
  public Map<String, Map<String, String>> getQasOptions() {
    return qasOptions;
  }
  
  public void setQasOptions(Map<String, Map<String, String>> qasOptions) {
    this.qasOptions = qasOptions;
  }

  public String getCreatedAt() {
    return createdAt;
  }
  
  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }
  
  public String getUpdatedAt() {
    return updatedAt;
  }
  
  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }
  
  public String getUserId() {
    return userId;
  }
  
  public void setUserId(String userId) {
    this.userId = userId;
  }
  
  public String getFromUrl() {
    return fromUrl;
  }
  
  public void setFromUrl(String fromUrl) {
    this.fromUrl = fromUrl;
  }
 
  public void addTag(String tag) {
    tags.add(tag);
  }
  
  public void delTag(String tag) {
    tags.remove(tag);
  }
  
  @Override
  public String toString() {
    
    String jsonQas = "[\n";
    for (String keyQuestionId : qasQuestion.keySet()) {
      jsonQas += "{\n";
      jsonQas += "question_id:" + keyQuestionId + ",\n";
      jsonQas += "question:" + qasQuestion.get(keyQuestionId) + "\n";
      jsonQas += "options:[" + "\n";
      Map<String, String> mapOptions = qasOptions.get(keyQuestionId);
      for (String keyOption : mapOptions.keySet()) {
        String valAnswer = mapOptions.get(keyOption);
        jsonQas += "{\n";
        jsonQas += "option:" + keyOption + ",\n";
        jsonQas += "answer:" + valAnswer + "\n";
        jsonQas += "},\n";
      }
      jsonQas += "]},\n";
    }
    jsonQas += "]";
    
    String jsonTags = "";
    if (tags != null) {
      jsonTags = tags.toString();
    }

    String result = "\n"
        + "{\n"
        + "\"context_id\":" + contextId + ",\n"
        + "\"context\":" + context + ",\n"
        + "\"k12n\":" + k12n + ",\n"
        + "\"subject\":" + subject + ",\n"
        + "\"dod\":" + dod + ",\n"
        + "\"type\":" + type + ",\n"
        + "\"qas\":" + jsonQas + ",\n"
        + "\"created_at\":" + createdAt + ",\n"
        + "\"updated_at\":" + updatedAt + ",\n"
        + "\"user_id\":" + userId + ",\n"
        + "\"from_url\":" + fromUrl + ",\n"
        + "\"tags\":[" + jsonTags + "]\n"
        + "}";
    return result;
  }

  @Override
  public int compareTo(IssueEntity issueEntity) {
    return contextId.compareTo(issueEntity.getContextId());
  }
}