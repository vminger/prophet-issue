/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.repo.drivers.elasticsearch;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vminger.prophet.issue.ProphetIssueApplication;
import com.vminger.prophet.issue.converter.IssueConverter;
import com.vminger.prophet.issue.repo.IssueEntity;
import com.vminger.prophet.issue.repo.drivers.elasticsearch.IssueRepoElastic;
import com.vminger.prophet.issue.repo.drivers.elasticsearch.IssueElasticRepository;
import com.vminger.prophet.issue.repo.drivers.elasticsearch.IssueEntityElastic;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProphetIssueApplication.class)
public class IssueRepoElasticTests {

  @Mock
  IssueElasticRepository dao;
  
  @Mock
  IssueConverter converter;
  
  @InjectMocks
  IssueRepoElastic repo;
  
  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);
  }
  
  @After
  public void after() {
  }
  
  @Test
  public void testCreate1() throws Exception {
    
    String issueInstance = ""
        + "{\n"
        + "  \"issue_in_text\": {\n"
        + "    \"context\": \"vimger test context 2\",\n"
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
        + "            \"option\": \"C. 2+2=3\",\n"
        + "            \"answer\": \"1\"\n"
        + "          },\n"
        + "          {\n"
        + "            \"option\": \"D. 2+2=4\",\n"
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
    
    IssueEntityElastic issueEntityElastic =
        issueConverter.createElasticFromIssueEntity(issueEntity);
    
    when(converter.createElasticFromIssueEntity(issueEntity))
      .thenReturn(issueEntityElastic);
    
    repo.create(issueEntity);
    
    verify(converter, times(1)).createElasticFromIssueEntity(issueEntity);
    verify(dao, times(1)).save(issueEntityElastic);
    
  }
  
  @Test
  public void testCreate2() throws Exception {
    
    String issueInstance1 = ""
        + "{\n"
        + "  \"issue_in_text\": {\n"
        + "    \"context\": \"vimger test context 3\",\n"
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
        + "            \"option\": \"C. 2+2=3\",\n"
        + "            \"answer\": \"1\"\n"
        + "          },\n"
        + "          {\n"
        + "            \"option\": \"D. 2+2=4\",\n"
        + "            \"answer\": \"0\"\n"
        + "          }\n"
        + "        ]\n"
        + "      }\n"
        + "    ]\n"
        + "  }\n"
        + "}";
    
    String issueInstance2 = ""
        + "{\n"
        + "  \"issue_in_text\": {\n"
        + "    \"context\": \"vimger test context 4\",\n"
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
        + "            \"option\": \"C. 2+2=3\",\n"
        + "            \"answer\": \"1\"\n"
        + "          },\n"
        + "          {\n"
        + "            \"option\": \"D. 2+2=4\",\n"
        + "            \"answer\": \"0\"\n"
        + "          }\n"
        + "        ]\n"
        + "      }\n"
        + "    ]\n"
        + "  }\n"
        + "}";
    
    IssueConverter issueConverter = new IssueConverter();
    
    IssueEntity issueEntity1 =
        issueConverter.createIssueEntityFromJson(issueInstance1);
    IssueEntity issueEntity2 =
        issueConverter.createIssueEntityFromJson(issueInstance2);
    List<IssueEntity> issueEntities = new ArrayList<IssueEntity>();
    issueEntities.add(issueEntity1);
    issueEntities.add(issueEntity2);
    
    IssueEntityElastic issueEntityElastic1 =
        issueConverter.createElasticFromIssueEntity(issueEntity1);
    IssueEntityElastic issueEntityElastic2 =
        issueConverter.createElasticFromIssueEntity(issueEntity2);
    List<IssueEntityElastic> issueEntityElastics =
        new ArrayList<IssueEntityElastic>();
    issueEntityElastics.add(issueEntityElastic1);
    issueEntityElastics.add(issueEntityElastic2);
    
    when(converter.createElasticFromIssueEntity(issueEntity1))
      .thenReturn(issueEntityElastic1);
    when(converter.createElasticFromIssueEntity(issueEntity2))
      .thenReturn(issueEntityElastic2);
    
    repo.create(issueEntities);
    
    verify(converter, times(1)).createElasticFromIssueEntity(issueEntity1);
    verify(converter, times(1)).createElasticFromIssueEntity(issueEntity2);
    verify(dao, times(1)).saveAll(issueEntityElastics);
    
  }
  
  @Test
  public void testFindByIssueId() throws Exception {
    
    String id = "4fbd11f9-46e9-4707-857c-5de75d2bee5f";
    
    IssueEntity issueEntity = new IssueEntity();
    issueEntity.setContextId(id);
    IssueEntityElastic elastic =
        converter.createElasticFromIssueEntity(issueEntity);
    
    when(dao.findById(id).get()).thenReturn(elastic);
    when(converter.createIssueEntityFromElastic(elastic))
      .thenReturn(issueEntity);
    
    IssueEntity actualIssueEntity = repo.findByIssueId(id);
    
    verify(dao, times(1)).findById(id).get();
    verify(converter, times(1)).createIssueEntityFromElastic(elastic);
    
    assertEquals(issueEntity, actualIssueEntity);
    
  }
  
  @Test
  public void testUpdate() throws Exception {
    String issueInstance = ""
        + "{\n"
        + "  \"issue_in_text\": {\n"
        + "    \"context\": \"vimger test context 20\",\n"
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
        + "            \"option\": \"C. 2+2=3\",\n"
        + "            \"answer\": \"1\"\n"
        + "          },\n"
        + "          {\n"
        + "            \"option\": \"D. 2+2=4\",\n"
        + "            \"answer\": \"0\"\n"
        + "          }\n"
        + "        ]\n"
        + "      }\n"
        + "    ]\n"
        + "  }\n"
        + "}";
    
    IssueConverter issueConverter = new IssueConverter();
    
    IssueEntity issue =
        issueConverter.createIssueEntityFromJson(issueInstance);
    issue.setContextId("b957ba10-850b-4901-acb5-b56372205ee7");
    
    IssueEntityElastic elastic =
        issueConverter.createElasticFromIssueEntity(issue);
    
    when(converter.createElasticFromIssueEntity(issue)).thenReturn(elastic);
    when(dao.save(elastic)).thenReturn(elastic);
    
    String actual = repo.update(issue);
    
    verify(converter, times(1)).createElasticFromIssueEntity(issue);
    verify(dao, times(1)).save(elastic);
    
    assertEquals(issue.toString(), actual);
    
  }
  
  @Test
  public void testDeleteByIssueId() throws Exception {
    
    String id = "4fbd11f9-46e9-4707-857c-5de75d2bee5f";
    
    String expected = "Delete an issue = " + id + " successfully";
    
    String actual = repo.deleteByIssueId(id);
    
    assertEquals(expected, actual);
    
  }
  
}
