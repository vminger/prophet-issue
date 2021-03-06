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
import org.springframework.boot.test.context.SpringBootTest;import org.springframework.dao.support.DaoSupport;
import org.springframework.test.context.junit4.SpringRunner;

import com.vminger.prophet.issue.ProphetIssueApplication;
import com.vminger.prophet.issue.converter.IssueConverter;
import com.vminger.prophet.issue.repo.IssueRepo;
import com.vminger.prophet.issue.repo.IssueEntity;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProphetIssueApplication.class)
public class IssueServiceImplTests {

  @Mock
  IssueRepo repo;

  @Mock
  IssueConverter converter;
  
  @InjectMocks
  IssueServiceImpl service;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);
  }

  @After
  public void after() {
  }
  
  @Test
  public void testCreateIssue() throws Exception {
    
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
    
    IssueConverter issueConverter = new IssueConverter();
    
    IssueEntity issueEntity =
        issueConverter.createIssueEntityFromJson(issueInstance);
    
    String expected = issueConverter.createJsonFromIssueEntity(issueEntity);
    
    when(converter.createIssueEntityFromJson(issueInstance))
      .thenReturn(issueEntity);
    
    when(repo.findByIssueId(issueEntity.getContextId()))
      .thenReturn(issueEntity);
    
    when(converter.createJsonFromIssueEntity(issueEntity))
      .thenReturn(expected);
    
    String actual = service.createIssue(issueInstance);
    
    assertEquals(expected, actual);
    
    verify(converter, times(1)).createIssueEntityFromJson(issueInstance);
    verify(repo, times(1)).create(issueEntity);
    verify(repo, times(1)).findByIssueId(issueEntity.getContextId());
    verify(converter, times(1)).createJsonFromIssueEntity(issueEntity);
    
  }
  
  @Test
  public void testShowIssue() throws Exception {

    String id = "84993e0c-5c95-4de3-a197-75c342a1cf44";
    IssueConverter issueConverter = new IssueConverter();
    IssueEntity issueEntity = new IssueEntity();
    String expected = issueConverter.createJsonFromIssueEntity(issueEntity);
    
    when(repo.findByIssueId(id)).thenReturn(issueEntity);
    when(converter.createJsonFromIssueEntity(issueEntity))
      .thenReturn(expected);
    
    String actual = service.showIssue(id);
    
    assertEquals(expected, actual);
    
    verify(repo, times(1)).findByIssueId(id);
    verify(converter, times(1)).createJsonFromIssueEntity(issueEntity);
    
  }
  
  @Test
  public void testUpdateIssue() throws Exception {
    
    String id = "76d42c7b-e5db-4fb8-b1f9-30216023ea9f";
    String patchIssueInstance = ""
        + "{\n"
        + "  \"issue_in_text\": {\n"
        + "    \"context\": \"vimger test context new\",\n"
        + "    \"k12n\": \"111\",\n"
        + "    \"subject\": \"111\",\n"
        + "    \"dod\": 100,\n"
        + "    \"type\": \"111\",\n"
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
    String newIssueInstance = ""
        + "{\n"
        + "  \"issue_in_text\": {\n"
        + "    \"context\": \"vimger test context old\",\n"
        + "    \"k12n\": \"111\",\n"
        + "    \"subject\": \"111\",\n"
        + "    \"dod\": 100,\n"
        + "    \"type\": \"111\",\n"
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
    String result = "updateIssue";
    
    IssueConverter issueConverter = new IssueConverter();
    IssueEntity patchIssueEntity =
        issueConverter.createIssueEntityFromJson(patchIssueInstance);
    IssueEntity newIssueEntity =
        issueConverter.createIssueEntityFromJson(newIssueInstance);
    
    when(converter.createIssueEntityFromJson(patchIssueInstance))
      .thenReturn(patchIssueEntity);
    when(repo.findByIssueId(id)).thenReturn(newIssueEntity);
    when(repo.update(newIssueEntity)).thenReturn(result);
    
    String actual = service.updateIssue(id, patchIssueInstance);
    
    assertEquals(result, actual);
    
    verify(converter, times(1)).createIssueEntityFromJson(patchIssueInstance);
    verify(repo, times(1)).findByIssueId(id);
    verify(repo, times(1)).update(newIssueEntity);
    
  }
  
  @Test
  public void testDeleteIssue() throws Exception {
    
    String id = "84993e0c-5c95-4de3-a197-75c342a1cf44";
    String result = "testDeleteIssue";
    
    when(repo.deleteByIssueId(id)).thenReturn(result);
    
    String actual = service.deleteIssue(id);
    
    verify(repo, times(1)).deleteByIssueId(id);
    
    assertEquals(result, actual);
    
  }
  
  @Test
  public void testListIssues() throws Exception {
    List<IssueEntity> issueEntities = new LinkedList<IssueEntity>();
    IssueEntity issueEntity = new IssueEntity();
    issueEntity.setContextId("f597e7aa-bae8-407b-a77c-9dd0a09d7a72");
    issueEntity.setContext("test context");
    issueEntities.add(issueEntity);
    
    String expected = ""
        + "{"
        + "\"context_id\":" + issueEntity.getContextId()
        + "\"context\":" + issueEntity.getContext()
        + "}";
    
    when(repo.listIssues()).thenReturn(issueEntities);
    when(converter.createJsonFromIssueEntity(issueEntities)).thenReturn(expected);
    String actual = service.listIssues();
    
    assertEquals(expected, actual);
  }

}