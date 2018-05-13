/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Configuration;

@Configuration
public class IssueEntity implements Serializable {

  private static final long serialVersionUID = -748388722236389944L;
  
  private String id;
  private String context;
  private String question;
  private Map<String, Boolean> options = new HashMap<String, Boolean>();
  private String k12n;
  private String subject;
  private String type;
  private float dod;
  private List<String> relatedIssues = new LinkedList<String>();
  private String createdAt;
  private String updatedAt;
  private String userId;
  private String fromUrl;
  private List<String> tags = new LinkedList<String>();

  public IssueEntity() {
  }

  /**
   * Issue Entity.
   * @param id uuid4 for issue entity
   * @param context context for issue entity
   * @param question question for issue entity
   * @param options options and answers for issue entity
   * @param k12n 010-1年级上学期,011-1年级下学期
   * @param subject chn-语文,math-数学
   * @param type mono-单选,multi-多选
   * @param createdAt created time
   * @param updatedAt updated time
   * @param userId uuid4 for user
   * @param fromUrl 爬虫爬取的原始url，user_id和url二选一
   * @param dod 0-100，n表示有n%的人不会做，数值越大，难度越大
   * @param relatedIssues related issues for cloze test
   * @param tags auto generated tags for issue entity
   */
  public IssueEntity(String id, String context, String question, Map<String, Boolean> options,
      String k12n, String subject, String type, float dod, List<String> relatedIssues,
      String createdAt, String updatedAt, String userId, String fromUrl, List<String> tags) {
    super();
    this.id = id;
    this.context = context;
    this.question = question;
    this.options = options;
    this.k12n = k12n;
    this.subject = subject;
    this.type = type;
    this.dod = dod;
    this.relatedIssues = relatedIssues;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.userId = userId;
    this.fromUrl = fromUrl;
    this.tags = tags;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  public String getQuestion() {
    return question;
  }
  
  public void setQuestion(String question) {
    this.question = question;
  }
  
  public Map<String, Boolean> getOptions() {
    return options;
  }
  
  public void setQas(Map<String, Boolean> options) {
    this.options = options;
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
  
  public String getType() {
    return type;
  }
  
  public void setType(String type) {
    this.type = type;
  }
  
  public float getDod() {
    return dod;
  }

  public void setDod(float dod) {
    this.dod = dod;
  }

  public List<String> getRelatedIssues() {
    return relatedIssues;
  }
  
  public void setRelatedIssues(List<String> relatedIssues) {
    this.relatedIssues = relatedIssues;
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
  
  public List<String> getTags() {
    return tags;
  }
  
  public void setTags(List<String> tags) {
    this.tags = tags;
  }
 
}