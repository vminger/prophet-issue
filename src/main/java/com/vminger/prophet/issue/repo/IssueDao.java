/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.repo;

import java.util.List;

import org.springframework.stereotype.Component;

import com.vminger.prophet.issue.repo.drivers.mongodb.entity.IssueEntityMongo;

@Component
public interface IssueDao {

  /**
   *  Insert an issue.
   * @param issue issue entity
   */
  public void insert(IssueEntityMongo issue);

  /**
   * Insert all issues.
   * @param issues issue entities
   */
  public void insertAll(List<IssueEntityMongo> issues);

  /**
   * Search an issue by ID.
   * @param id context issue id
   * @return
   */
  IssueEntityMongo findById(String id);

  /**
   * Search issues by userID.
   * @param userId user id
   * @return
   */
  List<IssueEntityMongo> findByUserId(String userId);
  
  /**
   * List all issues.
   * @return issue entities
   */
  List<IssueEntityMongo> listAllIssues();
  
  /**
   * Delete an issue.
   * @param issue issue entity
   */
  public void delete(IssueEntityMongo issue);

  /**
   * Delete an issue by ID.
   * @param id context issue id
   */
  public void deleteById(String id);

}
