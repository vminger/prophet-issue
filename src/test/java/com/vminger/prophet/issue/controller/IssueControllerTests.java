/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.controller;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

import com.vminger.prophet.issue.BaseIssueTests;
import com.vminger.prophet.issue.service.IssueService;

@RunWith(SpringRunner.class)
@SpringBootTest
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
	
	@Test
	public void testAddIssue() throws Exception {
		String issues = "mock test issues";
		when(service.addIssue(issues)).thenReturn(issues);
		
		mockMvc.perform(post("/v1.0/issues")
						.contentType(MediaType.APPLICATION_JSON)
						.content(issues))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();
		
		verify(service, timeout(1)).addIssue(issues);
	}
	
	@After
	public void tearDown() {
		
	}
}