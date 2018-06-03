/**
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.ai;

import java.util.Map;

public class OcrEntity {

  private int count;
  
  private Map<String, Float> result;
  
  public int getCount() {
    return count;
  }
  
  public void setCount(int count) {
    this.count = count;
  }
  
  public Map<String, Float> getResult() {
    return result;
  }
  
  public void setResult(Map<String, Float> result) {
    this.result = result;
  }
  
}
