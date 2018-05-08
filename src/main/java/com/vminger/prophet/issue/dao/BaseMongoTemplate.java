/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

public class BaseMongoTemplate {
	
	protected MongoTemplate template;
	
	public void setMongoTemplate(MongoTemplate template) {
		this.template = template;
	}
	
	public void setApplicationContext(ApplicationContext context) {
		MongoTemplate template = context.getBean("mongoTemplate", MongoTemplate.class);
		setMongoTemplate(template);
	}
}
