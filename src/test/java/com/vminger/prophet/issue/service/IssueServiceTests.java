/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.service;

import static org.mockito.Mockito.*;
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

import com.vminger.prophet.issue.dao.IssueDaoImpl;
import com.vminger.prophet.issue.entity.IssueEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IssueServiceTests {
	
	private MockMvc mockMvc;
	
	@Mock
	IssueDaoImpl issueDaoImpl;
	
	@InjectMocks
	IssueServiceImpl issueServiceImpl;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(issueServiceImpl).build();
	}
	
	@Test
	public void testAddIssue() throws Exception {
		IssueEntity issue;
		String issues;
	}
	
	@Test
	public void testAddIssueWithBadIssueEntity() throws Exception {
		IssueEntity issue;
		String issues;
	}
	
	@After
	public void tearDown() {
		
	}
}
