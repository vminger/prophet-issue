/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.vminger.prophet.issue.BaseIssueTests;
import com.vminger.prophet.issue.ProphetIssueApplication;
import com.vminger.prophet.issue.service.IssueService;
import com.vminger.prophet.issue.viewer.IssueViewer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProphetIssueApplication.class)
public class IssueControllerTests extends BaseIssueTests {

  private MockMvc mockMvc;

  @Mock
  private IssueService service;
  
  @Mock
  private IssueViewer issueViewer;

  @InjectMocks
  private IssueController controller;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  /**
   * Add issue with correct json instance.
   * @throws Exception exception
   */
  @Test
  public void testAddIssue() throws Exception {
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

    when(service.createIssue(issueInstance)).thenReturn(issueInstance);

    mockMvc.perform(post("/v1.0/issues")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(issueInstance))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
      .andDo(print())
      .andReturn();

    verify(service, times(1)).createIssue(issueInstance);
  }

  /**
   * Add issue with bad json instance.
   * @throws Exception exception
   */
  @Test
  public void testAddIssueWithBadJsonInstance1() throws Exception {
    String issueInstance = "{\"test\":\"test1\"}";

    when(service.createIssue(issueInstance)).thenReturn(issueInstance);

    mockMvc.perform(post("/v1.0/issues")
          .accept(MediaType.APPLICATION_JSON_UTF8)
          .contentType(MediaType.APPLICATION_JSON_UTF8)
          .content(issueInstance))
      .andExpect(status().is4xxClientError())
      .andDo(print())
      .andReturn();

    verify(service, times(0)).createIssue(issueInstance);
  }

  /**
   * Add issue with bad json instance.
   * @throws Exception exception
   */
  @Test
  public void testAddIssueWithBadJsonInstance2() throws Exception {
    String issueInstance = "test";

    when(service.createIssue(issueInstance)).thenReturn(issueInstance);

    mockMvc.perform(post("/v1.0/issues")
          .accept(MediaType.APPLICATION_JSON_UTF8)
          .contentType(MediaType.APPLICATION_JSON_UTF8)
          .content(issueInstance))
      .andExpect(status().is5xxServerError())
      .andDo(print())
      .andReturn();

    verify(service, times(0)).createIssue(issueInstance);
  }

  /**
   * Add issue with bad request header.
   * @throws Exception exception
   */
  @Test
  public void testAddIssueWithBadRequestHeader1() throws Exception {
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

    when(service.createIssue(issueInstance)).thenReturn(issueInstance);

    mockMvc.perform(post("/v1.0/issues")
          .accept(MediaType.APPLICATION_JSON_UTF8)
          .contentType(MediaType.APPLICATION_FORM_URLENCODED)
          .content(issueInstance))
      .andExpect(status().is4xxClientError())
      .andDo(print())
      .andReturn();

    verify(service, times(0)).createIssue(issueInstance);
  }
  
  @Test
  public void testListAllIssues() throws Exception {
    String result = "test";
    String expected = ""
        + "{"
        + "  \"issues_in_text\": {"
        + result
        + "}";
    
    when(service.listIssues()).thenReturn(result);
    when(issueViewer.listIssuesView(result)).thenReturn(expected);
    
    MvcResult mvcResult = mockMvc.perform(get("/v1.0/issues"))
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();
    
    verify(service, times(1)).listIssues();
    verify(issueViewer,times(1)).listIssuesView(result);
    
    assertEquals(expected, mvcResult.getResponse().getContentAsString());
  }

  @After
  public void tearDown() {
  }
}