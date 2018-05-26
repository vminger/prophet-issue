/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.repo.drivers.elasticsearch.dao;

import java.util.List;

import com.vminger.prophet.issue.repo.IssueDao;
import com.vminger.prophet.issue.repo.drivers.mongodb.entity.IssueEntityMongo;

public class IssueDaoImplElastic implements IssueDao {

  @Override
  public void insert(IssueEntityMongo issue) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void insertAll(List<IssueEntityMongo> issues) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public IssueEntityMongo findById(String id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<IssueEntityMongo> findByUserId(String userId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<IssueEntityMongo> listAllIssues() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(IssueEntityMongo issue) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void deleteById(String id) {
    // TODO Auto-generated method stub
    
  }

}
