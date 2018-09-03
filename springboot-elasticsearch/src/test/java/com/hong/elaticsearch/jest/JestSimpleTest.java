package com.hong.elaticsearch.jest;


import com.google.gson.GsonBuilder;
import com.hong.elasticsearch.model.Article;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.cluster.Health;
import io.searchbox.cluster.NodesInfo;
import io.searchbox.cluster.NodesStats;
import io.searchbox.core.*;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.IndicesExists;
import io.searchbox.indices.mapping.PutMapping;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * @author tianhong
 * @Description  Jest 客户端简单测试
 * @date 2018/8/22 10:51
 * @Copyright (c) 2018, DaChen All Rights Reserved.
 */
public class JestSimpleTest {

    private JestClient jestClient;

    /**
     * mapping 设置
     */
    final static String MAPPING_JSON_STRING ="{\n" +
            "  \"article\": {\n" +
            "    \"properties\": {\n" +
            "      \"id\": {\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"doc_values\":false\n" +
            "      },\n" +
            "      \"title\": {\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"standard\"\n" +
            "      },\n" +
            "      \"content\": {\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"standard\"\n" +
            "      },\n" +
            "      \"url\": {\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"standard\"\n" +
            "      },\n" +
            "      \"source\": {\n" +
            "        \"type\": \"text\"\n" +
            "      },\n" +
            "      \"author\": {\n" +
            "        \"type\": \"text\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";

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
     * 查看集群健康信息
     *
     * @throws Exception
     */
    @Test
    public void health() throws Exception {
        Health health = new Health.Builder().build();
        JestResult result = jestClient.execute(health);
        System.out.println("集群信息：" + result.getJsonString());
    }

    /**
     * 查看节点信息
     */
    @Test
    public void nodeInfo() throws IOException {
        NodesInfo nodeInfo = new NodesInfo.Builder().build();
        JestResult result = jestClient.execute(nodeInfo);
        System.out.println("节点信息：" + result.getJsonString());
    }

    /**
     * 查看节点状态
     */
    @Test
    public void nodeStatus() throws IOException {
        NodesStats nodeStats = new NodesStats.Builder().build();
        JestResult result = jestClient.execute(nodeStats);
        System.out.println("节点状态：" + result.getJsonObject());
    }

    /**
     * 检查索引是否存在
     */
    @Test
    public void indexExists() throws IOException {
        IndicesExists indicesExists = new IndicesExists.Builder("article").build();
        JestResult result = jestClient.execute(indicesExists);
        System.out.println("索引是否存在：" + result.getJsonString());
    }

    /**
     * 创建index
     */
    @Test
    public void createIndex() throws Exception {
        CreateIndex createIndex = new CreateIndex.Builder("article").build();
        JestResult result = jestClient.execute(createIndex);
        if (result == null || !result.isSucceeded()) {
            throw new Exception(result.getErrorMessage() + "创建索引失败!");
        }
    }

    /**
     * 设置index的mapping（设置数据类型和分词方式）
     */
    @Test
    public void setIndexMapping() throws IOException {
        PutMapping putMapping = new PutMapping.Builder("article","article",MAPPING_JSON_STRING).build();
        JestResult result = jestClient.execute(putMapping);
        System.out.println(result.getJsonString());

    }

    /**
     * 添加文档 Or 修改(若存在修改)
     */
    @Test
    public void addDocument() throws IOException {
        Article article = new Article(10,"我我我","helloWord","www.baidu.com","baidu","wangluo");
        Index index = new Index.Builder(article).index("article").type("article").build();
        JestResult result = jestClient.execute(index);
        System.out.println(result.getJsonObject());
    }



    /**
     * 批量新增
     */
    @Test
    public void bulkAddDocument() throws IOException {
        Article article = new Article(4,"yyyyy","text-4","www.baidu.com","baidu","wangwu");
        Article article2 = new Article(5,"xxxxx","text-5","www.baidu.com","baidu","mazi");
        Article article3 = new Article(6,"ooooo","text-6","www.baidu.com","baidu","jiaozi");
        Bulk bulk = new Bulk.Builder()
                .defaultIndex("article")
                .defaultType("article")
                .addAction(Arrays.asList(
                        new Index.Builder(article).build(),
                        new Index.Builder(article2).build(),
                        new Index.Builder(article3).build()
                )).build();
        BulkResult bulkResult = jestClient.execute(bulk);
        List<BulkResult.BulkResultItem> failedItems = bulkResult.getItems();
        failedItems.forEach(bulkResultItem -> {
            System.out.println(bulkResultItem.operation);
        });
    }

    /**
     * 修改文档 (指定修改部分文档)
     */
    @Test
    public void updateDocument() throws IOException {
         String script = "{\"doc\" : {\n" +
                 "  \"author\":\"hong\",\n" +
                 "  \"content\": \"json\"\n" +
                 "}}";
        DocumentResult result = jestClient.execute(new Update.Builder(script).index("article").type("article").id("1").build());
        assertTrue(result.getErrorMessage(), result.isSucceeded());
        assertEquals("article", result.getIndex());
        assertEquals("article", result.getType());
        assertEquals("1", result.getId());
        System.out.println(result.getJsonString());
    }

    /**
     * 根据version 控制并发修改问题
     */
    @Test
    public void updateVersionDocument() throws IOException {
        String doc ="{\n" +
                "  \"doc\":{\n" +
                "    \"author\":\"张三\"\n" +
                "  }\n" +
                "}";

        //language=JSON
        String doc_2 ="{\n" +
                "  \"doc\":{\n" +
                "    \"author\":\"李四\"\n" +
                "  }\n" +
                "}";

        Get get = new Get.Builder("article", "2").type("article").build();
        DocumentResult result = jestClient.execute(get);
        Long version = result.getVersion();

        result = jestClient.execute(new Update.VersionBuilder(doc, version)
                .index("article")
                .type("article")
                .id("2")
                .build());
        System.out.println("更新完成，返回参数："+result.getJsonString());

        result = jestClient.execute(new Update.VersionBuilder(doc_2, version)
                .index("article")
                .type("article")
                .id("2")
                .build());
        System.out.println("更新完成，返回参数："+result.getJsonString());
    }

    /**
     * 根据id 删除文档
     * @throws IOException
     */
    @Test
    public void deleteDocument() throws IOException {
        Delete delete = new Delete.Builder("2").build();
        DocumentResult result = jestClient.execute(delete);
        System.out.println(result.getJsonString());
    }
}


