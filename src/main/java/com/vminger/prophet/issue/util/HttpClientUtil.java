/**
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

/**
 * Http Client Util.
 * @author Vminger
 *
 */
public class HttpClientUtil {
  
  public HttpClientUtil() {
    
  }
  
  /**
   * Get Method for HttpClientUtil.
   * @param url uri
   * @return response
   * @throws ClientProtocolException ClientProtocolException
   * @throws IOException IOException
   */
  public String get(String url, Map<String, String> headers)
      throws ClientProtocolException, IOException {
    
    HttpGet httpGet = new HttpGet(url);
    
    // Setup configration.
    RequestConfig requestConfig = RequestConfig.custom()
        .setConnectTimeout(200000).setSocketTimeout(200000).build();
    httpGet.setConfig(requestConfig);
    
    // Setup http request headers.
    for (String key : headers.keySet()) {
      httpGet.setHeader(key, headers.get(key));
    }
    
    // Setup http client.
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    
    // Execute http request.
    CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
    
    // Parse http response content.
    StringBuffer buffer = new StringBuffer();
    HttpEntity responeEntity = httpResponse.getEntity();
    int statusCode = httpResponse.getStatusLine().getStatusCode();
    if (statusCode == 200) {
      BufferedReader reader = new BufferedReader(
          new InputStreamReader(responeEntity.getContent()));
      String str = null;
      while ((str = reader.readLine()) != null) {
        buffer.append(str);
      }
    }
    
    // Close
    httpClient.close();
    if (httpResponse != null) {
      httpResponse.close();
    }
    
    return buffer.toString();
  }
  
  /**
   * Post Method for HttpClientUtil.
   * @param url uri
   * @param data json data
   * @return response
   * @throws ClientProtocolException ClientProtocolException
   * @throws IOException IOException
   */
  public String post(String url, Map<String, String> headers,
      String data, String charset)
      throws ClientProtocolException, IOException {
   
    RequestConfig requestConfig = RequestConfig.custom()
        .setConnectTimeout(200000).setSocketTimeout(200000).build();
    
    HttpPost httpPost = new HttpPost(url);
    httpPost.setConfig(requestConfig);
    
    // Setup http request headers.
    for (String key : headers.keySet()) {
      httpPost.setHeader(key, headers.get(key));
    }
    
    // Setup charset.
    MultipartEntityBuilder multipartEntityBuilder
        = MultipartEntityBuilder.create();
    multipartEntityBuilder.setCharset(Charset.forName(charset));
    HttpEntity httpEntity = multipartEntityBuilder.build();
    httpPost.setEntity(httpEntity);
    
    // Setup Content-Type
    StringEntity stringEntity = new StringEntity(data);
    stringEntity.setContentType("application/json");
    httpPost.setEntity(stringEntity);
    
    // Setup http client.
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    
    // Execute http request.
    CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
    
    // Parse http response.
    HttpEntity responeEntity = httpResponse.getEntity();
    StringBuffer buffer = new StringBuffer();
    int statusCode = httpResponse.getStatusLine().getStatusCode();
    if (statusCode == 200) {
      BufferedReader reader = new BufferedReader(
          new InputStreamReader(responeEntity.getContent()));
      String str = null;
      while ((str = reader.readLine()) != null) {
        buffer.append(str);
      }
    }
    
    // Close connection.
    httpClient.close();
    if (httpResponse != null) {
      httpResponse.close();
    }
    
    return buffer.toString();
    
  }
  
  /**
   * Http post method for x-www-form-urlencoded.
   * @param url request url
   * @param headers request headers
   * @param params request params
   * @param charset request charset
   * @return response content
   * @throws IOException IOException
   * @throws ClientProtocolException ClientProtocolException
   * @throws UnsupportedOperationException UnsupportedOperationException
   */
  public String post(String url, Map<String, String> headers,
      Map<String, String> params, String charset)
          throws ClientProtocolException, IOException,
          UnsupportedOperationException {
    
    HttpPost httpPost = new HttpPost(url);
    
    // Setup configuration.
    RequestConfig config = RequestConfig.custom()
        .setConnectTimeout(200000).setSocketTimeout(200000).build();
    httpPost.setConfig(config);
    
    // Setup http request headers.
    for (String key : headers.keySet()) {
      httpPost.setHeader(key, headers.get(key));
    }
    
    // Setup http request forms.
    List<NameValuePair> forms = new ArrayList<NameValuePair>();
    for (String key : params.keySet()) {
      forms.add(new BasicNameValuePair(key, params.get(key)));
    }
    
    // Setup http request forms and charset.
    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(forms, charset);
    httpPost.setEntity(entity);
    
    // Setup http client.
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
    HttpEntity responeEntity = httpResponse.getEntity();
    
    // Get response.
    StringBuffer buffer = new StringBuffer();
    int statusCode = httpResponse.getStatusLine().getStatusCode();
    if (statusCode == 200) {
      BufferedReader reader = new BufferedReader(
          new InputStreamReader(responeEntity.getContent()));
      String str = null;
      while ((str = reader.readLine()) != null) {
        buffer.append(str);
      }
    }
    
    // Close connection.
    httpClient.close();
    if (httpResponse != null) {
      httpResponse.close();
    }
    
    return buffer.toString();
    
  }

}
