/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.dao;

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
import com.vminger.prophet.issue.entity.IssueEntity;
import com.vminger.prophet.issue.factory.IssueFactory;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProphetIssueApplication.class)
public class IssueDaoImplNoMockTests extends AbstractJUnit4SpringContextTests {

  @Autowired
  IssueDaoImpl issueDao;
  
  @Autowired
  IssueFactory issueFactory;
  
  @Before
  public void setUp() {
    //issueDao = new IssueDaoImpl();
  }
  
  @Test
  public void testInsertEmptyIssue() throws Exception {
    IssueEntity issueEntity = new IssueEntity();
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
    
    IssueEntity issueEntity = issueFactory.factoryIssue(issueInstance);
    
    issueDao.insert(issueEntity);
  }
  
  @After
  public void tearDown() {
  }
  
}
