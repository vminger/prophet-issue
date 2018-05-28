/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.repo;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface IssueDao {

  /**
   * Create an issue.
   * @author VMINGER
   * @param issueEntity issue entity
   * @exception Exception create exception
   */
  public void create(IssueEntity issueEntity) throws Exception;

  /**
   * Create issues.
   * @author VMINGER
   * @param issueEntities issue entities
   * @exception Exception create exception
   */
  public void create(List<IssueEntity> issueEntities) throws Exception;

  /**
   * Find an issue by issue id.
   * @author VMINGER
   * @param id issue id
   * @return issue entity
   */
  public IssueEntity findByIssueId(String id) throws Exception;

  /**
   * Find issues by user id.
   * @author VMINGER
   * @param userId user id
   * @return issue entities
   */
  public List<IssueEntity> findByUserId(String userId) throws Exception;
  
  /**
   * List all issues.
   * @author VMINGER
   * @return issue entities
   */
  public List<IssueEntity> listIssues() throws Exception;
  
  /**
   * Update an issue.
   * @author VMINGER
   * @param issueEntity issue entity
   * @return result
   * 
   */
  public String update(IssueEntity issueEntity) throws Exception;
  
  /**
   * Delete an issue.
   * @author VMINGER
   * @param issue issue entity
   */
  public String delete(IssueEntity issue) throws Exception;

  /**
   * Delete an issue.
   * @author VMINGER
   * @param id issue id
   */
  public String deleteByIssueId(String id) throws Exception;

}
