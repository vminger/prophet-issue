/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.service;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vminger.prophet.issue.dao.IssueDaoImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IssueServiceImplTests.class)
public class IssueServiceImplTests {

  @Mock
  IssueDaoImpl issueDaoImpl;

  @InjectMocks
  IssueServiceImpl issueServiceImpl;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testAddIssue() throws Exception {
    String issues = "test";
    
    String ret = issueServiceImpl.addIssues(issues);
    
    assertEquals(issues, ret);
  }

  @After
  public void tearDown() {
  
  }
}