/**
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.ai;

public interface AiBase {

  /**
   * General OCR: character recognition.
   * @param image base64 image
   * @return text
   */
  public String ocr_general(String image);
  
  /**
   * General NLP: word segmentation.
   * @param text text
   * @return words
   */
  public String nlp_general(String text);
  
}
