/**
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.ai.drivers.cosmos;

import org.springframework.beans.factory.annotation.Autowired;

import com.vminger.prophet.issue.ai.AiBase;

public class AiCosmos implements AiBase {

  @Autowired
  CosmosConfiguration config;
  
  @Override
  public String ocr_general(String image) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String nlp_general(String text) {
    // TODO Auto-generated method stub
    return null;
  }

}
