/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.viewer;

import java.util.List;

import org.springframework.stereotype.Component;

@Component("issueViewer")
public class IssueViewer {

  /**
   * View for create an issue.
   * @param result result for create an issue
   * @return view create an issue
   */
  public String createIssueView(String result) {
    String view = ""
        + "{"
        + "  \"issue_in_text\":"
        + result
        + "}";
    return view;
  }

  /**
   * View for show an issue.
   * @param result result for show an issue
   * @return view for show an issue
   */
  public String showIssueView(String result) {
    String view = ""
        + "{"
        + "  \"issue_show_text\":"
        + result
        + "}";
    return view;
  }
  
  /**
   * View for update an issue.
   * @param result result for update an issue
   * @return view for update an issue
   */
  public String updateIssueView(String result) {
    String view = ""
        + "{"
        + "  \"issue_update_text\":"
        + result
        + "}";
    return view;
  }
  
  /**
   * View for delete an issue.
   * @param result result for delete an issue
   * @return view for delete an issue
   */
  public String deleteIssueView(String result) {
    String view = ""
        + "{"
        + "  \"issue_delete_text\":"
        + result
        + "}";
    return view;
  }
  
  /**
   * View for list issues.
   * @param result result for list issues
   * @return view for list issues
   */
  public String listIssuesView(String result) {
    String view = ""
        + "{"
        + "  \"issue_list_text\":"
        + result
        + "}";
    return view;
  }
  
  /**
   * View for list issues by user id.
   * @param result result for list issues by user id
   * @return
   */
  public String listIssuesByUserIdView(String result) {
    String view = ""
        + "{"
        + "  \"issue_list_text\":"
        + result
        + "}";
    return view;
  }
  
  /**
   * View for error.
   * @param result result for error
   * @return
   */
  public String errorView(String result) {
    String view = ""
        + "{"
        + "  \"error\":"
        + result
        + "}";
    return view;
  }

  /**
   * View for error.
   * @param results results for error
   * @return
   */
  public String errorView(List<String> results) {
    String formatResult = "";
    for (String result : results) {
      formatResult += "{" + result + "},"; 
    }
    
    String view = ""
        + "{"
        + "  \"error\":["
        + formatResult
        + "]}";
    return view;
  }
  
}