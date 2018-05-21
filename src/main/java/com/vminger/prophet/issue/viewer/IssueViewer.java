/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.viewer;

import org.springframework.stereotype.Component;

@Component("issueViewer")
public class IssueViewer {

  /**
   * Create issues viewer.
   * @param result create issue result
   * @return create issue viewer
   */
  public String createIssuesViewer(String result) {
    String viewer = ""
        + "{"
        + "  \"issues_in_text\": {"
        + result
        + "}";
    return viewer;
  }
  
  /**
   * List all issues viewer.
   * @param result list all issues result
   * @return list all issues viewer
   */
  public String listAllIssuesViewer(String result) {
    String viewer = ""
        + "{"
        + "  \"issues_in_text\": {"
        + result
        + "}";
    return viewer;
  }
  
}
