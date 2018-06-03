/**
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.ai.drivers.cosmos;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;

import com.vminger.prophet.issue.ai.AiGenerator;
import com.vminger.prophet.issue.ai.IssueAi;
import com.vminger.prophet.issue.ai.NlpEntity;
import com.vminger.prophet.issue.ai.OcrEntity;

public class IssueAiCosmos implements IssueAi {

  @Autowired
  CosmosConfiguration config;
  
  @Autowired
  CosmosHttpClient client;
  
  @Autowired
  AiGenerator generator;
  
  @Override
  public OcrEntity ocrGeneral(String image)
      throws ClientProtocolException, IOException {
    
    String url = config.getHost() + ":" + config.getPort();
    
    String data = generator.generateOcrJson(image);
    
    String content = client.post(url, data);
    
    OcrEntity ocrEntity = generator.generateOcrEntity(content);
    
    return ocrEntity;

  }

  @Override
  public NlpEntity nlpGeneral(String text)
      throws ClientProtocolException, IOException {
    
    String url = config.getHost() + ":" + config.getPort();
    
    String data = generator.generateNlpJson(text);
    
    String content = client.post(url, data);
    
    NlpEntity nlpEntity = generator.generateNlpEntity(content);
    
    return nlpEntity;
    
  }

}
