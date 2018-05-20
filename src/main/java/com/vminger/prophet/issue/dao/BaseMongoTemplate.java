/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class BaseMongoTemplate {

  @Autowired
  protected MongoTemplate template;

  @Autowired
  public void setMongoTemplate(MongoTemplate template) {
    this.template = template;
  }

}
