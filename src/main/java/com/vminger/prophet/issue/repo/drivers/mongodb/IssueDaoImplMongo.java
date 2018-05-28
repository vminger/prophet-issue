/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.repo.drivers.mongodb;

import java.util.ArrayList;
import java.util.List;

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
  
  @Autowired
  private MongoTemplate repo;
  
  @Autowired
  private IssueConverter converter;
  
  @Override
  public void create(IssueEntity issue) throws Exception {
    
    IssueEntityMongo mongo = converter.createMongoFromIssueEntity(issue);
    
    repo.insert(mongo);
    
  }

  @Override
  public void create(List<IssueEntity> issues) throws Exception {
    
    List<IssueEntityMongo> mongos = new ArrayList<IssueEntityMongo>();
    for (IssueEntity issue : issues) {
      IssueEntityMongo mongo = converter.createMongoFromIssueEntity(issue);
      mongos.add(mongo);
    }
    
    repo.insert(mongos, IssueEntity.class);
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
    
    String contextId = issueEntity.getContextId();
    Query query = new Query();
    query.addCriteria(Criteria.where("context_id").is(contextId));
    
    Update update = Update.update("issue_entity", issueEntity);
    UpdateResult result = repo.upsert(query, update, IssueEntity.class);
    
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
    
    Query query = new Query();
    query.addCriteria(Criteria.where("context_id").is(id));
    DeleteResult result = repo.remove(query, IssueEntity.class);
    
    return result.toString();
  }

}