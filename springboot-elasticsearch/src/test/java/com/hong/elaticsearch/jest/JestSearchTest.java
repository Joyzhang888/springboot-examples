package com.hong.elaticsearch.jest;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hong.elasticsearch.model.Article;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.*;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author tianhong
 * @Description  Jest 客户端 深入ES搜索
 * @date 2018/8/31 16:12
 * @Copyright (c) 2018, DaChen All Rights Reserved.
 */
public class JestSearchTest {


    private JestClient jestClient;

    /**
     * 获取JestClient连接
     *
     * @return
     */
    @Before
    public void getJestClient() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://192.168.3.163:9280")
                .gson(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create())
                .multiThreaded(true)
                .readTimeout(10000)
                .build());
        jestClient = factory.getObject();
    }



    @After
    public void tearDown() throws IOException {
        closeJestClient(jestClient);
    }

    /**
     * 关闭JestClient客户端
     * @param jestClient
     * @throws Exception
     */
    public void closeJestClient(JestClient jestClient) throws IOException {

        if (jestClient != null) {
            jestClient.close();
        }
    }

    /**
     * 查询所有
     */
    @Test
    public void queryAll() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        SearchSourceBuilder query = searchSourceBuilder.query(queryBuilder);
        Search search = new Search.Builder(query.toString()).addIndex("article").addType("article").build();
        SearchResult result = jestClient.execute(search);
        System.out.println("本次查询共查到："+result.getTotal()+"篇文章！");
        List<SearchResult.Hit<Article,Void>> hits = result.getHits(Article.class);
        foreachSout(hits);
    }

    /**
     * Get 查询
     * 注：type 默认_all
     */
    @Test
    public void GetQuery() throws IOException {
        Get get = new Get.Builder("article", "2").build();
        DocumentResult result = jestClient.execute(get);
        System.out.println(result.getJsonString());
    }

    /**
     * _mget 查询
     */
    @Test
    public void MultiGet() throws IOException {
        Doc doc =new Doc("article","article","2");
        Doc doc2 =new Doc("test","test-type","2");

        MultiGet multiGet = new MultiGet.Builder.ByDoc(Arrays.asList(doc,doc2)).build();
        JestResult result = jestClient.execute(multiGet);
        JsonObject jsonObject = result.getJsonObject();
        JsonElement docs = jsonObject.get("docs");
        JsonArray jsonArray = docs.getAsJsonArray();
        jsonArray.forEach(json -> {
            System.out.println(json);
        });
    }


    /**
     * 检索指定字段
     * @throws IOException
     */
    @Test
    public void matchQuery() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryMatchBuilder = QueryBuilders.matchQuery("title","我");
        SearchSourceBuilder query = searchSourceBuilder.query(queryMatchBuilder);
        Search search = new Search.Builder(query.toString()).addIndex("article").build();
        SearchResult result = jestClient.execute(search);
        System.out.println("本次共查询到："+result.getTotal()+"篇文章！");
        List<SearchResult.Hit<Article, Void>> hits = result.getHits(Article.class);
        foreachSout(hits);
    }

    /**
     * 多字段检索
     * 注：我们这里查询的字段使用的是elasticsearch 自带分词器："analyzer": "standard"
     *     标准分词器类型是standard，用于大多数欧洲语言，使用Unicode文本分割算法对文档进行分词
     */
    @Test
    public void multiMatchQuery() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("我").field("title").field("content");
        SearchSourceBuilder query = searchSourceBuilder.query(multiMatchQueryBuilder);
        Search search = new Search.Builder(query.toString()).addIndex("article").addType("article").build();
        SearchResult result = jestClient.execute(search);
        System.out.println("本次共查询到："+result.getTotal()+"篇文章！");
        List<SearchResult.Hit<Article, Void>> hits = result.getHits(Article.class);
        foreachSout(hits);
    }


    private void foreachSout(List<SearchResult.Hit<Article, Void>> hits) {
        for (SearchResult.Hit<Article, Void> hit : hits) {
            Article source = hit.source;
            System.out.println("标题："+source.getTitle());
            System.out.println("内容："+source.getContent());
            System.out.println("url："+source.getUrl());
            System.out.println("来源："+source.getSource());
            System.out.println("作者："+source.getAuthor());
        }
    }
}
