/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.repo.drivers.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

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
  public void insert(IssueEntity issue) {
    // TODO Auto-generated method stub
    IssueEntityMongo mongo = converter.createMongoFromIssueEntity(issue);
    repo.insert(mongo);
  }

  @Override
  public void insertAll(List<IssueEntity> issues) {
    // TODO Auto-generated method stub
    List<IssueEntityMongo> mongos = new ArrayList<IssueEntityMongo>();
    for (IssueEntity issue : issues) {
      IssueEntityMongo mongo = converter.createMongoFromIssueEntity(issue);
      mongos.add(mongo);
    }
    repo.insertAll(mongos);
  }

  @Override
  public IssueEntity findById(String id) {
    // TODO Auto-generated method stub
    IssueEntityMongo mongo = repo.findById(id, IssueEntityMongo.class);
    IssueEntity issue = converter.createIssueEntityFromMongo(mongo);
    return issue;
  }

  @Override
  public List<IssueEntity> findByUserId(String userId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<IssueEntity> listAllIssues() {
    List<IssueEntityMongo> mongos = repo.findAll(IssueEntityMongo.class);
    List<IssueEntity> issues = new ArrayList<IssueEntity>();
    for (IssueEntityMongo mongo : mongos) {
      IssueEntity issue = converter.createIssueEntityFromMongo(mongo);
      issues.add(issue);
    }
    return issues;
  }
  
  @Override
  public void delete(IssueEntity issue) {
    IssueEntityMongo mongo = converter.createMongoFromIssueEntity(issue);
    repo.remove(mongo);
  }

  @Override
  public void deleteById(String id) {
    // TODO Auto-generated method stub
  }

}