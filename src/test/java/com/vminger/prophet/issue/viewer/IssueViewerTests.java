/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.viewer;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vminger.prophet.issue.ProphetIssueApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProphetIssueApplication.class)
public class IssueViewerTests {

  @Autowired
  IssueViewer viewer;
  
  @Before
  public void before() throws Exception {
  }
  
  @After
  public void after() throws Exception {
  }
  
  @Test
  public void testCreateIssuesViewer() throws Exception {
    String result = "test";
    String expected = ""
        + "{"
        + "  \"issues_in_text\": {"
        + result
        + "}";
    String actual = viewer.createIssuesViewer(result);
    
    assertEquals(expected, actual);
  }
  
  @Test
  public void testListAllIssuesViewer() throws Exception {
    String result = "test";
    String expected = ""
        + "{"
        + "  \"issues_in_text\": {"
        + result
        + "}";
    String actual = viewer.listAllIssuesViewer(result);
    
    assertEquals(expected, actual);
  }
}