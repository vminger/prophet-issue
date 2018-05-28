/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.repo.drivers.elasticsearch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
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
  public void create(IssueEntity issue) throws Exception {
    IssueEntityElastic elastic = converter.createElasticFromIssueEntity(issue);
    repo.save(elastic);
  }

  @Override
  public void create(List<IssueEntity> issues) throws Exception {
    List<IssueEntityElastic> elastics = new ArrayList<IssueEntityElastic>();
    for (IssueEntity issue : issues) {
      IssueEntityElastic elastic =
          converter.createElasticFromIssueEntity(issue);
      elastics.add(elastic);
    }
    repo.saveAll(elastics);
  }

  @Override
  public IssueEntity findByIssueId(String id) throws Exception {
    IssueEntityElastic elastic = repo.findById(id).get();
    IssueEntity issue = converter.createIssueEntityFromElastic(elastic);
    
    return issue;
  }

  @Override
  public List<IssueEntity> findByUserId(String userId) throws Exception {
    QueryBuilder builder = QueryBuilders.matchPhraseQuery("user_id", userId);
    
    Iterator<IssueEntityElastic> elastics = repo.search(builder).iterator();
    List<IssueEntity> issueEntities = new ArrayList<IssueEntity>();
    while (elastics.hasNext()) {
      IssueEntityElastic elastic = elastics.next();
      IssueEntity issueEntity
          = converter.createIssueEntityFromElastic(elastic);
      issueEntities.add(issueEntity);
    }
    
    return issueEntities;
  }

  @Override
  public List<IssueEntity> listIssues() throws Exception {
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
  public String update(IssueEntity issueEntity) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public String delete(IssueEntity issue) throws Exception {
    // FIXME: result
    String result = "Delete issue successfully";
    
    IssueEntityElastic elastic
        = converter.createElasticFromIssueEntity(issue);
    repo.delete(elastic);
    
    return result;
  }

  @Override
  public String deleteByIssueId(String id) throws Exception {
    // FIXME: result
    String result = "Delete issue successfully";
    
    repo.deleteById(id);
    
    return result;
  }

}
