/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.vminger.prophet.issue.dao.IssueDaoImpl;
import com.vminger.prophet.issue.entity.IssueEntity;

public class IssueServiceImpl implements IssueService {
	
	@Autowired
	private IssueDaoImpl issueDaoImpl;
	
	public String addIssue(String issues) {
		IssueEntity issue = new IssueEntity();
		issueDaoImpl.insert(issue);
		return issues;
	}
	
}
