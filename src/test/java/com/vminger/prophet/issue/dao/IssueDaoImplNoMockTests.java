/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.dao;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.vminger.prophet.issue.ProphetIssueApplication;
import com.vminger.prophet.issue.converter.IssueConverter;
import com.vminger.prophet.issue.repo.drivers.mongodb.dao.IssueDaoImplMongo;
import com.vminger.prophet.issue.repo.drivers.mongodb.entity.IssueEntityMongo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProphetIssueApplication.class)
public class IssueDaoImplNoMockTests extends AbstractJUnit4SpringContextTests {

  @Autowired
  IssueDaoImplMongo issueDao;
  
  @Autowired
  IssueConverter issueFactory;
  
  @Before
  public void before() {
  }
  
  @Test
  public void testInsertEmptyIssue() throws Exception {
    IssueEntityMongo issueEntity = new IssueEntityMongo();
    issueDao.insert(issueEntity);
  }
  
  @Test
  public void testInsertJsonInstanceIssue() throws Exception {
    String issueInstance = ""
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
    
    IssueEntityMongo issueEntity = issueFactory.createIssueEntityFromJson(issueInstance);
    
    issueDao.insert(issueEntity);
  }
  
  @Test
  public void testListAllIssues() throws Exception {
    
    List<IssueEntityMongo> actual = issueDao.listAllIssues();
    
  }
  
  @After
  public void after() {
  }
  
}
