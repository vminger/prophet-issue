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
  private MongoTemplate template;
  
  @Autowired
  private IssueConverter converter;
  
  @Override
  public void insert(IssueEntity issue) {
    // TODO Auto-generated method stub
    IssueEntityMongo mongo = converter.createMongoFromIssueEntity(issue);
    template.insert(mongo);
  }

  @Override
  public void insertAll(List<IssueEntity> issues) {
    // TODO Auto-generated method stub
    List<IssueEntityMongo> mongos = new ArrayList<IssueEntityMongo>();
    for (IssueEntity issue : issues) {
      IssueEntityMongo mongo = converter.createMongoFromIssueEntity(issue);
      mongos.add(mongo);
    }
    template.insertAll(mongos);
  }

  @Override
  public IssueEntity findById(String id) {
    // TODO Auto-generated method stub
    return template.findById(id, IssueEntity.class);
  }

  @Override
  public List<IssueEntity> findByUserId(String userId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<IssueEntity> listAllIssues() {
    return template.findAll(IssueEntity.class);
  }
  
  @Override
  public void delete(IssueEntity issue) {
    // TODO Auto-generated method stub
    template.remove(issue);
  }

  @Override
  public void deleteById(String id) {
    // TODO Auto-generated method stub
  }

}