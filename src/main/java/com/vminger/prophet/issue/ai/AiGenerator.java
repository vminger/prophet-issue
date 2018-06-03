/**
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.ai;

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
    
    // TODO
    OcrEntity ocrEntity = new OcrEntity();
    
    return ocrEntity;
  }
  
  /**
   * Generate ocr entity.
   * @param content response data
   * @return
   */
  public NlpEntity generateNlpEntity(String content) {
    
    // TODO
    NlpEntity nlpEntity = new NlpEntity();
    
    return nlpEntity;
  }
  
}
