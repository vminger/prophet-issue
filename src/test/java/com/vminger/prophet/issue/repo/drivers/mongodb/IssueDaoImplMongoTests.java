/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.repo.drivers.mongodb;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.vminger.prophet.issue.ProphetIssueApplication;
import com.vminger.prophet.issue.converter.IssueConverter;
import com.vminger.prophet.issue.repo.IssueEntity;
import com.vminger.prophet.issue.repo.drivers.mongodb.IssueDaoImplMongo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProphetIssueApplication.class)
public class IssueDaoImplMongoTests {

  @Mock
  MongoTemplate template;
  
  @Mock
  IssueConverter converter;
  
  @InjectMocks
  IssueDaoImplMongo repo;
  
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }
  
  @Test
  public void testCreate1() throws Exception {
    
    IssueEntity issueEntity = new IssueEntity();
    IssueEntityMongo issueEntityMongo = new IssueEntityMongo();
    
    when(converter.createMongoFromIssueEntity(issueEntity))
      .thenReturn(issueEntityMongo);
    
    repo.create(issueEntity);
    
    verify(converter, times(1)).createMongoFromIssueEntity(issueEntity);
    verify(template, times(1)).insert(issueEntityMongo);
    
  }
  
  @Test
  public void testCreate2() throws Exception {
    List<IssueEntity> issueEntities = new LinkedList<IssueEntity>();
    IssueEntity issueEntity = new IssueEntity();
    issueEntities.add(issueEntity);
    repo.create(issueEntities);
    verify(template, times(1)).insertAll(issueEntities);
  }
  
  @Test
  public void testDelete() throws Exception {
    IssueEntity issueEntity = new IssueEntity();
    repo.delete(issueEntity);
    verify(template, times(1)).remove(issueEntity);
  }
  
  @Test
  public void testDeleteById() throws Exception {
    String contextId = "b15de281-5868-4992-b918-63d582e69ecb";
    repo.deleteByIssueId(contextId);
  }
  
  @Test
  public void testFindById() throws Exception {
    String contextId = "b15de281-5868-4992-b918-63d582e69ecb";
    IssueEntity issueEntity = new IssueEntity();
    when(template.findById(contextId, IssueEntity.class)).thenReturn(issueEntity);
    
    IssueEntity retEntity = repo.findByIssueId(contextId);
    assertEquals(retEntity, issueEntity);
  }
  
  @Test
  public void testFindByUserId() throws Exception {
    String userId = "85fd2f86-be41-4bd7-b34b-7139e90e1ad1";
    
    List<IssueEntity> issueEntities = repo.findByUserId(userId);
    
    assertEquals(issueEntities, null);
  }
  
  @Test
  public void testListAllIssues() throws Exception {
    List<IssueEntity> expected = new LinkedList<IssueEntity>();
    IssueEntity issueEntity = new IssueEntity();
    issueEntity.setContextId("f597e7aa-bae8-407b-a77c-9dd0a09d7a72");
    issueEntity.setContext("test context");
    expected.add(issueEntity);
    
    when(template.findAll(IssueEntity.class)).thenReturn(expected);
    
    List<IssueEntity> actual = repo.listIssues();
    
    assertEquals(expected, actual);
  }
  
  @After
  public void tearDown() {
  }
  
}
