/**
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.ai.drivers.cosmos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.vminger.prophet.issue.util.HttpClientUtil;

/**
 * Http Client Util.
 * @author Vminger
 *
 */
public class CosmosHttpClient extends HttpClientUtil {
  
  public CosmosHttpClient() {
    
  }
  
  public void setUri(String uri) {
    
  }
  
  /**
   * Get Method for HttpClientUtil.
   * @param url uri
   * @return response
   * @throws ClientProtocolException ClientProtocolException
   * @throws IOException IOException
   */
  @Override
  public String get(String url) throws ClientProtocolException, IOException {
    
    RequestConfig requestConfig = RequestConfig.custom()
        .setConnectTimeout(200000).setSocketTimeout(200000).build();
    
    HttpGet httpGet = new HttpGet(url);
    httpGet.setConfig(requestConfig);
    
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
    HttpEntity responeEntity = httpResponse.getEntity();
    
    StringBuffer buffer = new StringBuffer();
    int statusCode = httpResponse.getStatusLine().getStatusCode();
    if (statusCode == 200) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(responeEntity.getContent()));
      String str = null;
      while ((str = reader.readLine()) != null) {
        buffer.append(str);
      }
    }
    
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
  @Override
  public String post(String url, String data)
      throws ClientProtocolException, IOException {
   
    RequestConfig requestConfig = RequestConfig.custom()
        .setConnectTimeout(200000).setSocketTimeout(200000).build();
    
    HttpPost httpPost = new HttpPost(url);
    httpPost.setConfig(requestConfig);
    httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
    
    MultipartEntityBuilder multipartEntityBuilder
        = MultipartEntityBuilder.create();
    multipartEntityBuilder.setCharset(Charset.forName("UTF-8"));
    
    HttpEntity httpEntity = multipartEntityBuilder.build();
    httpPost.setEntity(httpEntity);
    
    StringEntity stringEntity = new StringEntity(data);
    stringEntity.setContentType("text/json");
    httpPost.setEntity(stringEntity);
    
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
    HttpEntity responeEntity = httpResponse.getEntity();
    
    StringBuffer buffer = new StringBuffer();
    int statusCode = httpResponse.getStatusLine().getStatusCode();
    if (statusCode == 200) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(responeEntity.getContent()));
      String str = null;
      while ((str = reader.readLine()) != null) {
        buffer.append(str);
      }
    }
    
    httpClient.close();
    if (httpResponse != null) {
      httpResponse.close();
    }
    
    return buffer.toString();
    
  }
  
}
