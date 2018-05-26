/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.recomend;

import java.util.LinkedList;
import java.util.List;

import com.vminger.prophet.issue.repo.drivers.mongodb.entity.IssueEntityMongo;

public class TagFactory {

  public static List<String> getTags(IssueEntityMongo issueEntity) {
    List<String> tags = new LinkedList<String>();
    return tags;
  }
  
}
