/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.dao;

import java.util.List;

import com.vminger.prophet.issue.entity.IssueEntity;

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
   * Delete an issue.
   * @param issue issue entity
   */
  public void delete(IssueEntity issue);

  /**
   * Delete an issue by ID.
   * @param id context issue id
   */
  public void deleteById(String id);

  /**
   * Search an issue by ID.
   * @param id context issue id
   * @return
   */
  IssueEntity findById(String id);

  /**
   * Search issues by userID.
   * @param userId user id
   * @return
   */
  List<IssueEntity> findByUserId(String userId);

}
