/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.repo.drivers.elasticsearch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.vminger.prophet.issue.converter.IssueConverter;
import com.vminger.prophet.issue.repo.IssueDao;
import com.vminger.prophet.issue.repo.IssueEntity;

public class IssueDaoImplElastic implements IssueDao {

  @Autowired
  ElasticsearchRepository<IssueEntityElastic, String> repo;
  
  @Autowired
  IssueConverter converter;
  
  @Override
  public void insert(IssueEntity issue) {
    IssueEntityElastic elastic = converter.createElasticFromIssueEntity(issue);
    repo.save(elastic);
  }

  @Override
  public void insertAll(List<IssueEntity> issues) {
    List<IssueEntityElastic> elastics = new ArrayList<IssueEntityElastic>();
    for (IssueEntity issue : issues) {
      IssueEntityElastic elastic =
          converter.createElasticFromIssueEntity(issue);
      elastics.add(elastic);
    }
    repo.saveAll(elastics);
  }

  @Override
  public IssueEntity findById(String id) {
    IssueEntityElastic elastic = repo.findById(id).get();
    IssueEntity issue = converter.createIssueEntityFromElastic(elastic);
    return issue;
  }

  @Override
  public List<IssueEntity> findByUserId(String userId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<IssueEntity> listAllIssues() {
    List<IssueEntity> issues = new ArrayList<IssueEntity>();
    Iterator<IssueEntityElastic> elastics = repo.findAll().iterator();
    while (elastics.hasNext()) {
      IssueEntityElastic elastic = elastics.next();
      IssueEntity issue = converter.createIssueEntityFromElastic(elastic);
      issues.add(issue);
    }
    return issues;
  }

  @Override
  public void delete(IssueEntity issue) {
    IssueEntityElastic elastic = converter.createElasticFromIssueEntity(issue);
    repo.delete(elastic);
  }

  @Override
  public void deleteById(String id) {
    repo.deleteById(id);
  }

}
