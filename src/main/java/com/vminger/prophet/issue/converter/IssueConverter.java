/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.vminger.prophet.issue.recomend.TagFactory;
import com.vminger.prophet.issue.repo.IssueEntity;
import com.vminger.prophet.issue.repo.drivers.elasticsearch.IssueEntityElastic;
import com.vminger.prophet.issue.repo.drivers.mongodb.IssueEntityMongo;
import com.vminger.prophet.issue.util.UniDatetime;

/**
 * Issue Converter to convert issue entity to json instance and
 * convert json instance to issue entity.
 * @author vminger
 *
 */
@Component
public class IssueConverter {

  /**
   * Convert json instance to issue entity.
   * @param jsonInstance json instance
   * @return issue entity
   */
  public IssueEntity createIssueEntityFromJson(final String jsonInstance) {

    IssueEntity issueEntity = new IssueEntity();

    JSONObject jsonObject = new JSONObject(jsonInstance);

    JSONObject jsonIssueInText = jsonObject.getJSONObject("issue_in_text");

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
   * Covert issue entity to json instance.
   * @param issueEntity issue entity
   * @return json instance
   */
  public String createJsonFromIssueEntity(final IssueEntity issueEntity) {
    return issueEntity.toString();
  }

  /**
   * Covert issue entity to json instance.
   * @param issueEntities issue entities
   * @return json instance
   */
  public String createJsonFromIssueEntity(final List<IssueEntity> issueEntities) {
    String jsons = "[";

    for (int i = 0; i < issueEntities.size(); i++) {
      IssueEntity issueEntity = issueEntities.get(i);
      String json = issueEntity.toString();
      jsons += json;
      jsons += ",";
    }

    jsons += "]";

    return jsons;
  }
  
  /**
   * Convert IssueEntityMongo to IssueEntity.
   * @param mongo IssueEntityMongo
   * @return IssueEntity
   */
  public IssueEntity createIssueEntityFromMongo(IssueEntityMongo mongo) {
    IssueEntity issue = new IssueEntity();
    issue.setContextId(mongo.getContextId());
    issue.setContext(mongo.getContext());
    issue.setK12n(mongo.getK12n());
    issue.setSubject(mongo.getSubject());
    issue.setType(mongo.getType());
    issue.setDod(mongo.getDod());
    issue.setQasQuestion(mongo.getQasQuestion());
    issue.setQasOptions(mongo.getQasOptions());
    issue.setCreatedAt(mongo.getCreatedAt());
    issue.setUpdatedAt(mongo.getUpdatedAt());
    issue.setFromUrl(mongo.getFromUrl());
    issue.setUserId(mongo.getUserId());
    issue.setTags(mongo.getTags());
    
    return issue;
  }
  
  /**
   * Convert IssueEntity to IssueEntityMongo.
   * @param issue IssueEntity
   * @return IssueEntityMongo
   */
  public IssueEntityMongo createMongoFromIssueEntity(IssueEntity issue) {
    IssueEntityMongo mongo = new IssueEntityMongo();
    mongo.setContextId(issue.getContextId());
    mongo.setContext(issue.getContext());
    mongo.setK12n(issue.getK12n());
    mongo.setSubject(issue.getSubject());
    mongo.setType(issue.getType());
    mongo.setDod(issue.getDod());
    mongo.setQasQuestion(issue.getQasQuestion());
    mongo.setQasOptions(issue.getQasOptions());
    mongo.setCreatedAt(issue.getCreatedAt());
    mongo.setUpdatedAt(issue.getUpdatedAt());
    mongo.setFromUrl(issue.getFromUrl());
    mongo.setUserId(issue.getUserId());
    mongo.setTags(issue.getTags());
    
    return mongo;
  }
  
  
  /**
   * Convert IssueEntityElastic to IssueEntity.
   * @param elastic IssueEntityElastic
   * @return IssueEntity
   */
  public IssueEntity createIssueEntityFromElastic(IssueEntityElastic elastic) {
    IssueEntity issue = new IssueEntity();
    issue.setContextId(elastic.getContextId());
    issue.setContext(elastic.getContext());
    issue.setK12n(elastic.getK12n());
    issue.setSubject(elastic.getSubject());
    issue.setType(elastic.getType());
    issue.setDod(elastic.getDod());
    issue.setQasQuestion(elastic.getQasQuestion());
    issue.setQasOptions(elastic.getQasOptions());
    issue.setCreatedAt(elastic.getCreatedAt());
    issue.setUpdatedAt(elastic.getUpdatedAt());
    issue.setFromUrl(elastic.getFromUrl());
    issue.setUserId(elastic.getUserId());
    issue.setTags(elastic.getTags());
    
    return issue;
  }
  
  /**
   * Convert IssueEntity to IssueEntityElastic.
   * @param issue IssueEntity
   * @return IssueEntityElastic
   */
  public IssueEntityElastic createElasticFromIssueEntity(IssueEntity issue) {
    IssueEntityElastic elastic = new IssueEntityElastic();    
    elastic.setContextId(issue.getContextId());
    elastic.setContext(issue.getContext());
    elastic.setK12n(issue.getK12n());
    elastic.setSubject(issue.getSubject());
    elastic.setType(issue.getType());
    elastic.setDod(issue.getDod());
    elastic.setQasQuestion(issue.getQasQuestion());
    elastic.setQasOptions(issue.getQasOptions());
    elastic.setCreatedAt(issue.getCreatedAt());
    elastic.setUpdatedAt(issue.getUpdatedAt());
    elastic.setFromUrl(issue.getFromUrl());
    elastic.setUserId(issue.getUserId());
    elastic.setTags(issue.getTags());
    
    return elastic;
  }
  
}
