package com.atguigu.eduservice.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientUtils {

    private static CloseableHttpClient httpClient = null;

    static {
        httpClient = HttpClientBuilder.create().build();
    }

    public static String get(String url){
        HttpGet httpGet = new HttpGet(url);
        String result = null;
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

            HttpEntity entity = httpResponse.getEntity();

            result = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return result;
        }
    }
}
