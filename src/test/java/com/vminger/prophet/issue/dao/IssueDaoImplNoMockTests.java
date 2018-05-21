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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProphetIssueApplication.class)
public class IssueDaoImplNoMockTests extends AbstractJUnit4SpringContextTests {

  @Autowired
  IssueDaoImpl issueDao;
  
  @Before
  public void setUp() {
    //issueDao = new IssueDaoImpl();
  }
  
  @Test
  public void testInsert() throws Exception {
    IssueEntity issueEntity = new IssueEntity();
    issueDao.insert(issueEntity);
  }
  
  @After
  public void tearDown() {
  }
  
}
