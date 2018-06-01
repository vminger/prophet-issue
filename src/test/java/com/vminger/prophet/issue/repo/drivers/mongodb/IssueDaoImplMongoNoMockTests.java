/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.repo.drivers.mongodb;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import com.vminger.prophet.issue.ProphetIssueApplication;
import com.vminger.prophet.issue.converter.IssueConverter;
import com.vminger.prophet.issue.repo.IssueEntity;
import com.vminger.prophet.issue.repo.drivers.mongodb.IssueDaoImplMongo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProphetIssueApplication.class)
public class IssueDaoImplMongoNoMockTests extends AbstractJUnit4SpringContextTests {

  @Autowired
  IssueDaoImplMongo repo;
  
  @Autowired
  IssueConverter converter;
  
  @Before
  public void before() {
  }
  
  @Test
  public void testCreateIssueEntityFromEmpty() throws Exception {
    
    IssueEntity issueEntity = new IssueEntity();
    
    repo.create(issueEntity);
    
  }
  
  @Test
  public void testCreateIssueEntityFromJsonInstance() throws Exception {
    
    String issueInstance = ""
        + "{\n"
        + "  \"issue_in_text\": {\n"
        + "    \"context\": \"vimger test context\",\n"
        + "    \"k12n\": \"000\",\n"
        + "    \"subject\": \"000\",\n"
        + "    \"dod\": 60,\n"
        + "    \"type\": \"000\",\n"
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
        + "            \"option\": \"C. 2+2=3\",\n"
        + "            \"answer\": \"1\"\n"
        + "          },\n"
        + "          {\n"
        + "            \"option\": \"D. 2+2=4\",\n"
        + "            \"answer\": \"0\"\n"
        + "          }\n"
        + "        ]\n"
        + "      }\n"
        + "    ]\n"
        + "  }\n"
        + "}";
    
    IssueEntity issueEntity =
        converter.createIssueEntityFromJson(issueInstance);
    
    repo.create(issueEntity);
    
  }
  
  @Test
  public void testFindByIssueId() throws Exception {
    
    String id = "84993e0c-5c95-4de3-a197-75c342a1cf44";
    
    IssueEntity issueEntity = repo.findByIssueId(id);
    
    String jsonIssue = converter.createJsonFromIssueEntity(issueEntity);
    
    assertEquals(id, issueEntity.getContextId());
  
  }
  
  @Test
  public void testUpdate() throws Exception {
    String id = "c08efceb-92f3-49d3-82f1-fa1a6c9468ca";
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
    
    issueEntity.setContextId(id);
    
    String actual = repo.update(issueEntity);
    String expected = "";
    
    assertEquals(expected, actual);
    
  }
  
  @Test
  public void testDeleteByIssueId() throws Exception {
    
    String contextId = "84993e0c-5c95-4de3-a197-75c342a1cf44";
    
    String actual = repo.deleteByIssueId(contextId);
    
    assertEquals("AcknowledgedDeleteResult{deletedCount=1}", actual);
    
  }
  
  @After
  public void after() {
  }
  
}
