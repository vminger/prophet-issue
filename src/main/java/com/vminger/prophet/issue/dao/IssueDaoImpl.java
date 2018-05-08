/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.dao;

import java.util.List;

import com.vminger.prophet.issue.entity.IssueEntity;

public class IssueDaoImpl extends BaseMongoTemplate implements IssueDao {

	@Override
	public void insert(IssueEntity issue) {
		// TODO Auto-generated method stub
		template.insert(issue);
	}

	@Override
	public void insertAll(List<IssueEntity> issues) {
		// TODO Auto-generated method stub
		template.insertAll(issues);
	}

	@Override
	public void delete(IssueEntity issue) {
		// TODO Auto-generated method stub
		template.remove(issue);
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IssueEntity findById(String id) {
		// TODO Auto-generated method stub
		return template.findById(id, IssueEntity.class);
	}

	@Override
	public List<IssueEntity> findByUserId(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

}
