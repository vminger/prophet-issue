/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.service;

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
import org.springframework.test.context.junit4.SpringRunner;

import com.vminger.prophet.issue.ProphetIssueApplication;
import com.vminger.prophet.issue.converter.IssueConverter;
import com.vminger.prophet.issue.repo.IssueDao;
import com.vminger.prophet.issue.repo.drivers.mongodb.entity.IssueEntityMongo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProphetIssueApplication.class)
public class IssueServiceImplTests {

  @Mock
  IssueDao issueDao;

  @Mock
  IssueConverter issueFactory;
  
  @InjectMocks
  IssueServiceImpl issueServiceImpl;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);
  }

  @After
  public void after() {
  }
  
  @Test
  public void testAddIssue() throws Exception {
    String issues = ""
        + "{\n"
        + "  \"issues_in_text\": {\n"
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
    
    IssueEntityMongo issueEntity = new IssueEntityMongo();
    when(issueFactory.createIssueEntityFromJson(issues)).thenReturn(issueEntity);
    
    String actual = issueServiceImpl.addIssues(issues);
    
    verify(issueFactory, times(1)).createIssueEntityFromJson(issues);
    
    assertEquals(issues, actual);
  }
  
  @Test
  public void testListAllIssues() throws Exception {
    List<IssueEntityMongo> issueEntities = new LinkedList<IssueEntityMongo>();
    IssueEntityMongo issueEntity = new IssueEntityMongo();
    issueEntity.setContextId("f597e7aa-bae8-407b-a77c-9dd0a09d7a72");
    issueEntity.setContext("test context");
    issueEntities.add(issueEntity);
    
    String expected = ""
        + "{"
        + "\"context_id\":" + issueEntity.getContextId()
        + "\"context\":" + issueEntity.getContext()
        + "}";
    
    when(issueDao.listAllIssues()).thenReturn(issueEntities);
    when(issueFactory.createJsonFromIssueEntity(issueEntities)).thenReturn(expected);
    String actual = issueServiceImpl.listAllIssues();
    
    assertEquals(expected, actual);
  }

}