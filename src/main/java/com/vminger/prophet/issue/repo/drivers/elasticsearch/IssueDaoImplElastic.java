/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.repo.drivers.elasticsearch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.vminger.prophet.issue.repo.IssueDao;
import com.vminger.prophet.issue.repo.IssueEntity;

public class IssueDaoImplElastic implements IssueDao {

  @Autowired
  ElasticsearchRepository<IssueEntityElastic, Long> repo;
  
  @Override
  public void insert(IssueEntity issue) {
    // TODO Auto-generated method stub
  }

  @Override
  public void insertAll(List<IssueEntity> issues) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public IssueEntity findById(String id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<IssueEntity> findByUserId(String userId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<IssueEntity> listAllIssues() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(IssueEntity issue) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void deleteById(String id) {
    // TODO Auto-generated method stub
    
  }

}
