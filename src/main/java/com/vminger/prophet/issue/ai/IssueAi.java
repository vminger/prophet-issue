/**
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.ai;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public interface IssueAi {

  /**
   * General OCR: character recognition.
   * @param image base64 image
   * @return text
   */
  public OcrEntity ocrGeneral(String image)
      throws ClientProtocolException, IOException;

  /**
   * General NLP: word segmentation.
   * @param text text for word segmentation
   * @return words
   */
  public NlpEntity nlpGeneral(String text)
      throws ClientProtocolException, IOException;

}
