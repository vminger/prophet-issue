/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.repo;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface IssueDao {

  /**
   *  Insert an issue.
   * @param issue issue entity
   */
  public void insert(IssueEntity issue);

  /**
   * Insert all issues.
   * @param issues issue entities
   */
  public void insertAll(List<IssueEntity> issues);

  /**
   * Search an issue by ID.
   * @param id context issue id
   * @return
   */
  public IssueEntity findById(String id);

  /**
   * Search issues by userID.
   * @param userId user id
   * @return
   */
  public List<IssueEntity> findByUserId(String userId);
  
  /**
   * List all issues.
   * @return issue entities
   */
  public List<IssueEntity> listAllIssues();
  
  /**
   * Delete an issue.
   * @param issue issue entity
   */
  public void delete(IssueEntity issue);

  /**
   * Delete an issue by ID.
   * @param id context issue id
   */
  public void deleteById(String id);

}
