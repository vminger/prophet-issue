/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.repo.drivers.mongodb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.vminger.prophet.issue.repo.IssueDao;
import com.vminger.prophet.issue.repo.drivers.mongodb.entity.IssueEntityMongo;

@Repository
public class IssueDaoImplMongo implements IssueDao {
  
  @Autowired
  private MongoTemplate template;
  
  @Override
  public void insert(IssueEntityMongo issue) {
    // TODO Auto-generated method stub
    template.insert(issue);
  }

  @Override
  public void insertAll(List<IssueEntityMongo> issues) {
    // TODO Auto-generated method stub
    template.insertAll(issues);
  }

  @Override
  public IssueEntityMongo findById(String id) {
    // TODO Auto-generated method stub
    return template.findById(id, IssueEntityMongo.class);
  }

  @Override
  public List<IssueEntityMongo> findByUserId(String userId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<IssueEntityMongo> listAllIssues() {
    return template.findAll(IssueEntityMongo.class);
  }
  
  @Override
  public void delete(IssueEntityMongo issue) {
    // TODO Auto-generated method stub
    template.remove(issue);
  }

  @Override
  public void deleteById(String id) {
    // TODO Auto-generated method stub
  }

}