/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.dao;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.vminger.prophet.issue.entity.IssueEntity;

@Repository
public class IssueDaoImpl implements IssueDao {
  
  @Autowired
  private MongoTemplate template;
  
  @Override
  public void insert(IssueEntity issue) {
    // TODO Auto-generated method stub
    template.insert(issue);
  }

  @Override
  public void insertAll(List<IssueEntity> issues) {
    // TODO Auto-generated method stub
    template.insertAll(issues);
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