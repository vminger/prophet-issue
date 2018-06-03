/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.repo.drivers.elasticsearch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.vminger.prophet.issue.converter.IssueConverter;
import com.vminger.prophet.issue.repo.IssueRepo;
import com.vminger.prophet.issue.repo.IssueEntity;

@Repository("issueDaoImplElastic")
public class IssueRepoElastic implements IssueRepo {

  private static final Logger logger = LogManager.getLogger(IssueRepoElastic.class);
  
  @Autowired
  IssueElasticRepository repo;
  
  @Autowired
  IssueConverter converter;
  
  @Override
  public void create(IssueEntity issue) throws Exception {
    
    logger.debug("Start to create an issue in elasticsearch");
    
    IssueEntityElastic elastic = converter.createElasticFromIssueEntity(issue);
    
    repo.save(elastic);
    
    logger.debug("End to create an issue in elasticsearch");
    
  }

  @Override
  public void create(List<IssueEntity> issues) throws Exception {
    
    logger.debug("Start to create issues in elasticsearch");
    
    List<IssueEntityElastic> elastics = new ArrayList<IssueEntityElastic>();
    for (IssueEntity issue : issues) {
      IssueEntityElastic elastic =
          converter.createElasticFromIssueEntity(issue);
      elastics.add(elastic);
    }
    
    repo.saveAll(elastics);
    
    logger.debug("End to create issues in elasticsearch");
    
  }

  @Override
  public IssueEntity findByIssueId(String id) throws Exception {
    
    logger.debug("Start to find an issue in elasticsearch, id = " + id);
    
    IssueEntityElastic elastic = repo.findById(id).get();
    
    IssueEntity issue = converter.createIssueEntityFromElastic(elastic);
    
    logger.debug("End to find an issue in elasticsearch, id = " + id);
    
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
  public String update(IssueEntity issue) throws Exception {
    
    logger.debug("Start to create an issue in elasticsearch");
    
    IssueEntityElastic elastic = converter.createElasticFromIssueEntity(issue);
    
    IssueEntityElastic result = repo.save(elastic);
    
    logger.debug("End to create an issue in elasticsearch");
    
    return result.toString();
    
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
    
    logger.debug("Start to delete an issue in elasticsearch, id = " + id);
    
    String result = "Delete an issue = " + id + " successfully";
    
    repo.deleteById(id);
    
    logger.debug("End to delete an issue in elasticsearch, id = " + id);
    
    return result;
  }

}
