package cn.connie.common.weichat.utils;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/*
 * 利用HttpClient进行post请求的工具类
 */
public class HttpsClientUtils {

  public String doPost(String url, Map<String, String> map, String charset) {
    HttpClient httpClient = null;
    HttpPost httpPost = null;
    String result = null;
    try {
      httpClient = new SSLClient();
      httpPost = new HttpPost(url);
      //设置参数
      List<NameValuePair> list = new ArrayList<NameValuePair>();
      Iterator iterator = map.entrySet().iterator();
      while (iterator.hasNext()) {
        Entry<String, String> elem = (Entry<String, String>) iterator.next();
        list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
      }
      if (list.size() > 0) {
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
        httpPost.setEntity(entity);
      }
      HttpResponse response = httpClient.execute(httpPost);
      if (response != null) {
        HttpEntity resEntity = response.getEntity();
        if (resEntity != null) {
          result = EntityUtils.toString(resEntity, charset);
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return result;
  }

  public String doPost(String url, String body, String charset) {
    HttpClient httpClient = null;
    HttpPost httpPost = null;
    String result = null;
    try {
      httpClient = new SSLClient();
      httpPost = new HttpPost(url);
      System.out.println(url);
      System.out.println(body);
      //设置参数
      ByteArrayEntity entity = new ByteArrayEntity(body.getBytes(), ContentType.APPLICATION_XML);
      httpPost.setEntity(entity);

      HttpResponse response = httpClient.execute(httpPost);
      if (response != null) {
        HttpEntity resEntity = response.getEntity();
        if (resEntity != null) {
          result = EntityUtils.toString(resEntity, charset);
          System.out.println(result);
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return result;
  }

}