/**
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.ai;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class AiGenerator {
  
  /**
   * Generate ocr json instance.
   * @param image base64 image data
   * @return
   */
  public String generateOcrJson(String image) {
    
    String data = ""
        + "{"
        + "\"ocr\": {"
        + "\"image\": " + image
        + "}"
        + "}";
    
    return data;
  }
  
  /**
   * Generate nlp json instance.
   * @param text text data
   * @return
   */
  public String generateNlpJson(String text) {
    
    String data = ""
        + "{"
        + "\"nlp\": {"
        + "\"text\": " + text
        + "}"
        + "}";
    
    return data;
  }
  
  /**
   * Generate ocr entity.
   * @param content response data
   * @return
   */
  public OcrEntity generateOcrEntity(String content) {
    
    OcrEntity ocrEntity = new OcrEntity();
    
    JSONObject jsonRoot = new JSONObject(content);
    
    JSONObject jsonOcr = jsonRoot.getJSONObject("ocr");
    
    int count = jsonOcr.getInt("result_num");
    ocrEntity.setCount(count);
    
    Map<String, Float> results = new HashMap<String, Float>();
    JSONArray jsonResults = jsonOcr.getJSONArray("result");
    for (int i = 0; i < jsonResults.length(); i++) {
      JSONObject jsonResult = jsonResults.getJSONObject(i);
      String text = jsonResult.getString("text");
      float probility = jsonResult.getFloat("probability");
      results.put(text, probility);
    }
    ocrEntity.setResult(results);
    
    return ocrEntity;
    
  }
  
  /**
   * Generate ocr entity.
   * @param content response data
   * @return
   */
  public NlpEntity generateNlpEntity(String content) {
    
    NlpEntity nlpEntity = new NlpEntity();
    
    JSONObject jsonRoot = new JSONObject(content);
    
    JSONObject jsonOcr = jsonRoot.getJSONObject("ocr");
    
    int count = jsonOcr.getInt("result_num");
    nlpEntity.setCount(count);
    
    Map<String, Float> results = new HashMap<String, Float>();
    JSONArray jsonResults = jsonOcr.getJSONArray("result");
    for (int i = 0; i < jsonResults.length(); i++) {
      JSONObject jsonResult = jsonResults.getJSONObject(i);
      String text = jsonResult.getString("text");
      float probility = jsonResult.getFloat("probability");
      results.put(text, probility);
    }
    nlpEntity.setResult(results);
    
    return nlpEntity;
  }
  
}
