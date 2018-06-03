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

import org.bson.BsonValue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.vminger.prophet.issue.ProphetIssueApplication;
import com.vminger.prophet.issue.converter.IssueConverter;
import com.vminger.prophet.issue.repo.IssueEntity;
import com.vminger.prophet.issue.repo.drivers.mongodb.IssueRepoMongo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProphetIssueApplication.class)
public class IssueRepoMongoTests {

  @Mock
  MongoTemplate template;
  
  @Mock
  IssueConverter converter;
  
  @InjectMocks
  IssueRepoMongo repo;
  
  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);
  }
  
  @After
  public void after() {
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
  public void testFindByIssueId() throws Exception {
    
    String contextId = "b15de281-5868-4992-b918-63d582e69ecb";
    IssueEntityMongo issueEntityMongo = new IssueEntityMongo();
    IssueEntity issueEntity = new IssueEntity();
    
    when(template.findById(contextId, IssueEntityMongo.class))
      .thenReturn(issueEntityMongo);
    when(converter.createIssueEntityFromMongo(issueEntityMongo))
      .thenReturn(issueEntity);
    
    IssueEntity retEntity = repo.findByIssueId(contextId);
    
    verify(template, times(1)).findById(contextId, IssueEntityMongo.class);
    verify(converter, times(1)).createIssueEntityFromMongo(issueEntityMongo);
    
    assertEquals(issueEntity, retEntity);
    
  }
  
  @Test
  public void testFindByUserId() throws Exception {
    String userId = "85fd2f86-be41-4bd7-b34b-7139e90e1ad1";
    
    List<IssueEntity> issueEntities = repo.findByUserId(userId);
    
    assertEquals(issueEntities, null);
  }
  
  @Test
  public void testUpdate() throws Exception {
    
    String id = "76d42c7b-e5db-4fb8-b1f9-30216023ea9f";
    String patchIssueInstance = ""
        + "{\n"
        + "  \"issue_in_text\": {\n"
        + "    \"context\": \"vimger test context new\",\n"
        + "    \"k12n\": \"111\",\n"
        + "    \"subject\": \"111\",\n"
        + "    \"dod\": 100,\n"
        + "    \"type\": \"111\",\n"
        + "    \"qas\": [\n"
        + "      {\n"
        + "        \"question\": \"which one is right?\",\n"
        + "        \"options\": [\n"
        + "          {\n"
        + "            \"option\": \"A. 1+1=1\",\n"
        + "            \"answer\": \"1\"\n"
        + "          },\n"
        + "          {\n"
        + "            \"option\": \"B. 1+1=2\",\n"
        + "            \"answer\": \"0\"\n"
        + "          },\n"
        + "          {\n"
        + "            \"option\": \"C. 2+2=2\",\n"
        + "            \"answer\": \"1\"\n"
        + "          },\n"
        + "          {\n"
        + "            \"option\": \"D. 2+2=2\",\n"
        + "            \"answer\": \"0\"\n"
        + "          }\n"
        + "        ]\n"
        + "      }\n"
        + "    ]\n"
        + "  }\n"
        + "}";
    
    IssueConverter issueConverter = new IssueConverter();
    
    IssueEntity issueEntity =
        issueConverter.createIssueEntityFromJson(patchIssueInstance);
    
    IssueEntityMongo mongo =
        issueConverter.createMongoFromIssueEntity(issueEntity);
    
    UpdateResult result = new UpdateResult() {
      
      @Override
      public boolean wasAcknowledged() {
        // TODO Auto-generated method stub
        return false;
      }
      
      @Override
      public boolean isModifiedCountAvailable() {
        // TODO Auto-generated method stub
        return false;
      }
      
      @Override
      public BsonValue getUpsertedId() {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public long getModifiedCount() {
        // TODO Auto-generated method stub
        return 0;
      }
      
      @Override
      public long getMatchedCount() {
        // TODO Auto-generated method stub
        return 0;
      }
    };
        
    Query query = new Query();
    query.addCriteria(Criteria.where("_id").is(id));
    Update update = Update.update("issue_entity", mongo);
    
    when(template.updateFirst(query, update, IssueEntityMongo.class))
      .thenReturn(result);
    
    when(converter.createMongoFromIssueEntity(issueEntity)).thenReturn(mongo);
    
    String actual = repo.update(issueEntity);
    
    String expected = "";
    
    assertEquals(expected, actual);
    
  }
  
  @Test
  public void testDelete() throws Exception {
    IssueEntity issueEntity = new IssueEntity();
    repo.delete(issueEntity);
    verify(template, times(1)).remove(issueEntity);
  }
  
  @Test
  public void testDeleteByIssueId() throws Exception {
    
    String contextId = "84993e0c-5c95-4de3-a197-75c342a1cf44";
    DeleteResult result = new DeleteResult() {
      
      @Override
      public boolean wasAcknowledged() {
        // TODO Auto-generated method stub
        return false;
      }
      
      @Override
      public long getDeletedCount() {
        // TODO Auto-generated method stub
        return 0;
      }
    };
    
    Query query = new Query();
    query.addCriteria(Criteria.where("context_id").is(contextId));
    
    when(template.remove(query, IssueEntityMongo.class)).thenReturn(result);
    
    String actual = repo.deleteByIssueId(contextId);
    
    verify(template, times(1)).remove(query, IssueEntityMongo.class);
    
    assertEquals(result.toString(), actual);
    
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
  
}
