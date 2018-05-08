/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.dao;

import java.util.List;

import com.vminger.prophet.issue.entity.IssueEntity;

public interface IssueDao {

	/**
	 * Insert an issue
	 * @param issue
	 */
	public void insert(IssueEntity issue);
	
	/**
	 * Insert all issues
	 * @param issues
	 */
	public void insertAll(List<IssueEntity> issues);
	
	/**
	 * Delete an issue
	 * @param issue
	 */
	public void delete(IssueEntity issue);
	
	/**
	 * Delete an issue by ID
	 * @param issue
	 */
	public void deleteById(String id);
	
	/**
	 * Search an issue by ID
	 * @param id
	 * @return
	 */
	IssueEntity findById(String id);
	
	
	/**
	 * Search issues by userID
	 * @param userID
	 * @return
	 */
	List<IssueEntity> findByUserId(String userID);
	
}
