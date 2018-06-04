package com.hong.elaticsearch;


import com.alibaba.fastjson.JSONObject;
import com.hong.elasticsearch.ElasticsearchApplication;
import com.hong.elasticsearch.repository.ESRepository;
import com.hong.elasticsearch.repository.TestIndexRepository;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.script.ScriptService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * @author tianhong
 * @Description 测试transport 操作elasticsearch
 * @date 2018/5/31 11:20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ElasticsearchApplication.class)
public class TransportClientTest {

    private static final Logger logger = LoggerFactory.getLogger(ESRepository.class);

    @Autowired
    private TestIndexRepository testIndexRepository;

    @Test
    public void testCreateIndex() {
        boolean success = testIndexRepository.buildIndex("test-index-2");
        logger.info("创建索引是否成功：{}", success);
    }

    @Test
    public void testDelete() {
        boolean success = testIndexRepository.deleteIndex("test-index-2");
        logger.info("删除索引是否成功：{}", success);
    }

    @Test
    public void testCreateIndexAddData() throws IOException {
        // 添加json 字符串数据
        String json = "{" +
                "\"name\":\"深入浅出Node.js\"," +
                "\"author\":\"朴灵 \"," +
                "\"pubinfo\":\"人民邮电出版社 \"," +
                "\"pubtime\":\"2013-12-1 \"," +
                "\"desc\":\"本书从不同的视角介绍了 Node 内在的特点和结构。由首章Node 介绍为索引，涉及Node 的各个方面，主要内容包含模块机制的揭示、异步I/O 实现原理的展现、异步编程的探讨、内存控制的介绍、二进制数据Buffer 的细节、Node 中的网络编程基础、Node 中的Web 开发、进程间的消息传递、Node 测试以及通过Node 构建产品需要的注意事项。最后的附录介绍了Node 的安装、调试、编码规范和NPM 仓库等事宜。本书适合想深入了解 Node 的人员阅读。\"" +
                "}";
        String id = testIndexRepository.insert("book-index", "book", json, "123");
        logger.info("添加json 字符串数据，返回id：{}", id);

        // 添加map 数据
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", "Go Web编程");
        paramMap.put("author", "谢孟军 ");
        paramMap.put("pubinfo", "电子工业出版社");
        paramMap.put("pubtime", "2013-6");
        paramMap.put("desc", "《Go Web编程》介绍如何使用Go语言编写Web，包含了Go语言的入门、Web相关的一些知识、Go中如何处理Web的各方面设计（表单、session、cookie等）、数据库以及如何编写GoWeb应用等相关知识。通过《Go Web编程》的学习能够让读者了解Go的运行机制，如何用Go编写Web应用，以及Go的应用程序的部署和维护等，让读者对整个的Go的开发了如指掌。");
        id = testIndexRepository.insert("book-index", "book", paramMap, "456");
        logger.info("添加json 字符串数据，返回id：{}", id);

        // 使用Elasticsearch XContentFactory 组装数据
        XContentBuilder builder = null;

        builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("name", "Java编程思想")
                .field("author", "Bruce Eckel")
                .field("pubinfo", "机械工业出版社")
                .field("pubtime", "2007-6")
                .field("desc", "本书赢得了全球程序员的广泛赞誉，即使是最晦涩的概念，在Bruce Eckel的文字亲和力和小而直接的编程示例面前也会化解于无形。从Java的基础语法到最高级特性（深入的面向对象概念、多线程、自动项目构建、单元测试和调试等），本书都能逐步指导你轻松掌握。从本书获得的各项大奖以及来自世界各地的读者评论中，不难看出这是一本经典之作。本书共22章，包括操作符、控制执行流程、访问权限控制、复用类、多态、接口、通过异常处理错误、字符串、泛型、数组、容器深入研究、JavaI/O系统、枚举类型、并发以及图形化用户界面等内容。这些丰富的内容，包含了Java语言基础语法以及高级特性，适合各个层次的Java程序员阅读，同时也是高等院校讲授面向对象程序设计语言以及Java语言的绝佳教材和参考书。")
                .endObject();
        String jsonstring = builder.string();
        id = testIndexRepository.insert("book-index", "book", jsonstring, "789");
        logger.info("添加json 字符串数据，返回id：{}", id);

    }

    @Test
    public void testDeleteDoc(){
        testIndexRepository.delById("book-index","book","123");
        testIndexRepository.delById("book-index","book","456");
        testIndexRepository.delById("book-index","book","789");
    }

    @Test
    public void testUpdateDoc(){
        // 使用map 更新
        Map<String,Object> map = new HashMap<>();
        map.put("name", "Java编程思想");
        map.put("author", "Bruce Eckel");
        map.put("pubinfo", "机械工业出版社");
        map.put("pubtime", "2007-6");
        map.put("desc", "本书赢得了全球程序员的广泛赞誉，即使是最晦涩的概念，在Bruce Eckel的文字亲和力和小而直接的编程示例面前也会化解于无形。从Java的基础语法到最高级特性（深入的面向对象概念、多线程、自动项目构建、单元测试和调试等），本书都能逐步指导你轻松掌握。从本书获得的各项大奖以及来自世界各地的读者评论中，不难看出这是一本经典之作。本书共22章，包括操作符、控制执行流程、访问权限控制、复用类、多态、接口、通过异常处理错误、字符串、泛型、数组、容器深入研究、JavaI/O系统、枚举类型、并发以及图形化用户界面等内容。这些丰富的内容，包含了Java语言基础语法以及高级特性，适合各个层次的Java程序员阅读，同时也是高等院校讲授面向对象程序设计语言以及Java语言的绝佳教材和参考书。");
        testIndexRepository.updateById(map,"book-index","book","123");

        // 使用script语法字符串更新
        String scriptData = "ctx._source.author = \"闫洪磊\";" +
                        "ctx._source.name = \"Activiti实战\";" +
                        "ctx._source.pubinfo = \"机械工业出版社\";" +
                        "ctx._source.pubtime = \"2015-01-01\";" +
                        "ctx._source.desc = \"《Activiti实战 》立足于实践，不仅让读者知其然，全面掌握Activiti架构、功能、用法、技巧和最佳实践，广度足够；而且让读者知其所以然，深入理解Activiti的源代码实现、设计模式和PVM，深度也足够。《Activiti实战 》一共四个部分：准备篇（1~2章）介绍了Activiti的概念、特点、应用、体系结构，以及开发环境的搭建和配置；基础篇（3~4章）首先讲解了Activiti Modeler、Activiti Designer两种流程设计工具的详细使用，然后详细讲解了BPMN2.0规范；实战篇（5~14章）系统讲解了Activiti的用法、技巧和最佳实践，包含流程定义、流程实例、任务、子流程、多实例、事件以及监听器等；高级篇（15~21）通过集成WebService、规则引擎、JPA、ESB等各种服务和中间件来阐述了Activiti不仅仅是引擎，实际上是一个BPM平台，最后还通过源代码对它的设计模式及PVM进行了分析。\"";
        testIndexRepository.updateById(scriptData,"book-index","book","456");

        // 使用JSON对象数据格式更新
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","深入理解ElasticSearch");
        jsonObject.put("author","〔美〕拉斐尔·酷奇");
        jsonObject.put("pubinfo","机械工业出版社");
        jsonObject.put("pubtime","2016-01");
        jsonObject.put("desc","本书内容丰富，不仅深入介绍了Apache Lucene的评分机制、查询DSL、底层索引控制，而且介绍了ElasticSearch的分布式索引机制、系统监控及性能优化、用户体验的改善、Java API的使用，以及自定义插件的开发。本书文笔优雅，辅以大量翔实的实例，能帮助读者快速提高ElasticSearch水平。需要提醒读者的是，本书的目标读者是ElasticSearch的中高级用户，如果读者对ElasticSearch的基础概念诸如Mapping、Types等缺乏了解的话，可先阅读作者的另外一本针对初学者的书籍《ElasticSearch Server》");
        testIndexRepository.updateById(jsonObject,"book-index","book","789");

    }

    @Test
    public void testQuery(){

    }











}
