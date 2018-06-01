/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.repo.drivers.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.vminger.prophet.issue.converter.IssueConverter;
import com.vminger.prophet.issue.repo.IssueDao;
import com.vminger.prophet.issue.repo.IssueEntity;

@Repository
public class IssueDaoImplMongo implements IssueDao {
  
  private static final Logger logger = LogManager.getLogger(IssueDaoImplMongo.class);
  
  @Autowired
  private MongoTemplate repo;
  
  @Autowired
  private IssueConverter converter;
  
  @Override
  public void create(IssueEntity issue) throws Exception {
    
    logger.debug("Start to create an issue in mongodb");
    
    IssueEntityMongo mongo = converter.createMongoFromIssueEntity(issue);
    
    repo.insert(mongo);
    
    logger.debug("End to create issues in mongodb");
  
  }

  @Override
  public void create(List<IssueEntity> issues) throws Exception {
    
    logger.debug("Start to create issues in mongodb");
    
    List<IssueEntityMongo> mongos = new ArrayList<IssueEntityMongo>();
    
    for (IssueEntity issue : issues) {
      
      IssueEntityMongo mongo = converter.createMongoFromIssueEntity(issue);
      
      mongos.add(mongo);
      
    }
    
    repo.insert(mongos, IssueEntityMongo.class);
    
    logger.debug("End to create issues in mongodb");
    
  }

  @Override
  public IssueEntity findByIssueId(String id) {
    
    IssueEntityMongo mongo = repo.findById(id, IssueEntityMongo.class);
    IssueEntity issue = converter.createIssueEntityFromMongo(mongo);
    
    return issue;
  }

  @Override
  public List<IssueEntity> findByUserId(String userId) {
    
    Query query = new Query();
    query.addCriteria(Criteria.where("user_id").is(userId));
    List<IssueEntity> issueEntities = repo.find(query, IssueEntity.class);
    
    return issueEntities;
  }

  @Override
  public List<IssueEntity> listIssues() {
    
    List<IssueEntityMongo> mongos = repo.findAll(IssueEntityMongo.class);
    List<IssueEntity> issues = new ArrayList<IssueEntity>();
    for (IssueEntityMongo mongo : mongos) {
      IssueEntity issue = converter.createIssueEntityFromMongo(mongo);
      issues.add(issue);
    }
    
    return issues;
  }

  @Override
  public String update(IssueEntity issueEntity) {
    
    IssueEntityMongo mongo =
        converter.createMongoFromIssueEntity(issueEntity);
    
    String contextId = mongo.getContextId();
    Query query = new Query();
    query.addCriteria(Criteria.where("_id").is(contextId));
    
    Update update = new Update();
    
    if (issueEntity.getContext() != null) {
      update.set("context", issueEntity.getContext());
    }

    if (issueEntity.getK12n() != null) {
      update.set("k12n", issueEntity.getK12n());
    }
    
    if (issueEntity.getSubject() != null) {
      update.set("subject", issueEntity.getSubject());
    }
    
    if (issueEntity.getDod() != 0) {
      update.set("dod", issueEntity.getDod());
    }
    
    if (issueEntity.getType() != null) {
      update.set("type", issueEntity.getType());
    }

    if (issueEntity.getQasQuestion() != null) {
      update.set("qas_questions", issueEntity.getQasQuestion());
    }
    
    if (issueEntity.getQasOptions() != null) {
      update.set("qas_options", issueEntity.getQasOptions());
    }
    
    if (issueEntity.getUserId() != null) {
      update.set("userid", issueEntity.getUserId());
    }
    
    if (issueEntity.getFromUrl() != null) {
      update.set("fromurl", issueEntity.getFromUrl());
    }
    
    if (issueEntity.getTags() != null) {
      update.set("tags", issueEntity.getTags());
    }
    
    UpdateResult result = repo.upsert(query, update, IssueEntityMongo.class);
    
    return result.toString();
  }
  
  @Override
  public String delete(IssueEntity issueEntity) {

    IssueEntityMongo mongo =
        converter.createMongoFromIssueEntity(issueEntity);
    DeleteResult result = repo.remove(mongo);
    
    return result.toString();
  }

  @Override
  public String deleteByIssueId(String id) {
    
    logger.debug("Start to delete an issue by id = " + id);
    
    Query query = new Query();
    query.addCriteria(Criteria.where("_id").is(id));
    DeleteResult result = repo.remove(query, IssueEntityMongo.class);
    logger.debug("Result for delete an issue by id = " + id);
    logger.debug(result.toString());
    
    logger.debug("End to delete an issue by id = " + id);
    
    return result.toString();
  }

}