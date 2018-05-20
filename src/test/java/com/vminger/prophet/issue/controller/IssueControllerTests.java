/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.vminger.prophet.issue.main.BaseIssueTests;
import com.vminger.prophet.issue.service.IssueService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IssueControllerTests.class)
public class IssueControllerTests extends BaseIssueTests {

  private MockMvc mockMvc;

  @Mock
  private IssueService service;

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

    when(service.addIssues(issues)).thenReturn(issues);

    mockMvc.perform(post("/v1.0/issues")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(issues))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
      .andDo(print())
      .andReturn();

    verify(service, times(1)).addIssues(issues);
  }

	public void testAddIssueWithBadJsonInstance1() throws Exception {
		String issues = "{\"test\":\"test1\"}";
		
		when(service.addIssues(issues)).thenReturn(issues);
		
		mockMvc.perform(post("/v1.0/issues")
						.accept(MediaType.APPLICATION_JSON_UTF8)
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(issues))
				.andExpect(status().is4xxClientError())
				.andDo(print())
				.andReturn();
		
		verify(service, times(1)).addIssues(issues);
	}
	
	public void testAddIssueWithBadJsonInstance2() throws Exception {
		String issues = "test";
		
		when(service.addIssues(issues)).thenReturn(issues);
		
		mockMvc.perform(post("/v1.0/issues")
						.accept(MediaType.APPLICATION_JSON_UTF8)
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(issues))
				.andExpect(status().is4xxClientError())
				.andDo(print())
				.andReturn();
		
		verify(service, times(1)).addIssues(issues);
	}
	
	public void testAddIssueWithBadRequestHeader1() throws Exception {
		String issues = 
		"{ " +
		"	\"issues_in_text\": { " +
		"    	\"ctxs\": [ " +
		"          { " +
		"          	\"ctx_id\": \"7278aacd-1cd3-4af7-83c5-c734bc91e494\", " +
		"            \"ctx\": \"This is a mono issue test.\" " +
		"          } " +
		"         ], " +
		"         \"issues\": [ " +
		"          { " +
		"            \"ctx_id\": \"7278aacd-1cd3-4af7-83c5-c734bc91e494\", " +
		"            \"issue_id\": \"23698db7-8d30-409c-9626-a4c106c63ffd\", " +
		"            \"issue_topic\": \"which one is right?\", " +
		"            \"issue_k12n\": \"020\", " +
		"            \"issue_subject\": \"math\", " +
		"            \"issue_dod\": 60, " +
		"            \"issue_type\": \"mono\", " +
		"            \"issue_qa\": [ " +
		"              { " +
		"                \"option\": \"A. 1+1=1\", " +
		"                \"is_answer\": false " +
		"              }, { " +
		"                \"option\": \"B. 1+1=2\", " +
		"                \"is_answer\": true " +
		"              }, { " +
		"                \"option\": \"C. 2+2=2\", " +
		"                \"is_answer\": true " +
		"              }, { " +
		"                \"option\": \"D. 2+2=2\", " +
		"                \"is_answer\": true " +
		"              } " +
		"            ] " +
		"          } " +
		"         ] " +
		"    } " +
		"}";
		
		when(service.addIssues(issues)).thenReturn(issues);
		
		mockMvc.perform(post("/v1.0/issues")
						.accept(MediaType.APPLICATION_JSON_UTF8)
						.contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.content(issues))
				.andExpect(status().is4xxClientError())
				.andDo(print())
				.andReturn();
		
		verify(service, times(1)).addIssues(issues);
	}
	
	@After
	public void tearDown() {
		
	}
}