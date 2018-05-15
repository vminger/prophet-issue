/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UniDatetime {
  
  /**
   * UniDatetime generator.
   * @return datetime
   */
  public static String getDatetime() {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String datetime = df.format(new Date());
    return datetime;
  }
  
}
