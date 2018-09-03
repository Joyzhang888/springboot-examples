package com.hong.elaticsearch.rest;

import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author tianhong
 * @Description elasticsearch 官方rest client   https://www.elastic.co/guide/en/elasticsearch/client/java-api/5.6/index.html
 * @date 2018/8/22 10:51
 * @Copyright (c) 2018, DaChen All Rights Reserved.
 */
public class RestClientTest {

    private RestClient restClient;

    @Before
    public void restClient(){
         restClient = RestClient.builder(new HttpHost("192.168.3.163", 9280, "http")).build();
    }

    @After
    public void closeRestClient(){
        try {
            restClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test() throws IOException {
        Response response = restClient.performRequest("GET", "/article/article/_search");
        System.out.println("======================================================");
        System.out.println(response.getRequestLine());
    }

    @Test
    public void testAsync(){
        restClient.performRequestAsync("GET","/article/article/_search",responseListener);
    }

    ResponseListener responseListener = new ResponseListener() {
        @Override
        public void onSuccess(Response response) {
            // 请求成功回调
            System.out.println("======================================================");
            System.out.println(response.getEntity());
            try {
                String s = EntityUtils.toString(response.getEntity());
                System.out.println(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Exception exception) {
            //请求失败时回调
        }
    };



}
