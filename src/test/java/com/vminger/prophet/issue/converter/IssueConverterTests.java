/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.converter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vminger.prophet.issue.ProphetIssueApplication;
import com.vminger.prophet.issue.repo.drivers.mongodb.entity.IssueEntityMongo;

/**
 * Test for IssueConverter.
 * @author vminger
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProphetIssueApplication.class)
public class IssueConverterTests {

  @Autowired
  IssueConverter converter;

  /**
   * Before test.
   * @throws Exception exception
   */
  @Before
  public void before() throws Exception {
  }

  /**
   * After test.
   * @throws Exception exception
   */
  @After
  public void after() throws Exception {
  }

  /**
   * Test for creating entity from json.
   * @throws Exception exception
   */
  @Test
  public void testCreateIssueEntityFromJson() throws Exception {
    String json = ""
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
    
    IssueEntityMongo issue = converter.createIssueEntityFromJson(json);
    
    assertEquals("vimger test context", issue.getContext());
    assertEquals("000", issue.getK12n());
    assertEquals("000", issue.getSubject());
    assertEquals(60, issue.getDod(), 0.1);
    assertEquals("000", issue.getType());
  }

  /**
   * Test for creating json from entity.
   * @throws Exception exception
   */
  @Test
  public void testCreateJsonFromIssueEntity() throws Exception {
    List<IssueEntityMongo> issueEntities = new ArrayList<IssueEntityMongo>();
    
    String json1 = ""
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
    IssueEntityMongo issue1 = converter.createIssueEntityFromJson(json1);
    issueEntities.add(issue1);
    
    String json2 = ""
        + "{\n"
        + "  \"issues_in_text\": {\n"
        + "    \"context\": \"vimger test context\",\n"
        + "    \"k12n\": \"010\",\n"
        + "    \"subject\": \"010\",\n"
        + "    \"dod\": 70,\n"
        + "    \"type\": \"010\",\n"
        + "    \"qas\": [\n"
        + "      {\n"
        + "        \"question\": \"which one is wrong?\",\n"
        + "        \"options\": [\n"
        + "          {\n"
        + "            \"option\": \"A. 1+1=1\",\n"
        + "            \"answer\": \"0\"\n"
        + "          },\n"
        + "          {\n"
        + "            \"option\": \"B. 1+1=2\",\n"
        + "            \"answer\": \"1\"\n"
        + "          },\n"
        + "          {\n"
        + "            \"option\": \"C. 2+2=2\",\n"
        + "            \"answer\": \"0\"\n"
        + "          },\n"
        + "          {\n"
        + "            \"option\": \"D. 2+2=4\",\n"
        + "            \"answer\": \"1\"\n"
        + "          }\n"
        + "        ]\n"
        + "      }\n"
        + "    ]\n"
        + "  }\n"
        + "}";
    IssueEntityMongo issue2 = converter.createIssueEntityFromJson(json2);
    issueEntities.add(issue2);
    
    String actual = converter.createJsonFromIssueEntity(issueEntities);
    
    assertEquals(issue1, actual);
  }

}
