# springboot-examples
> spring boot 出现的目的是为了使spring  framework 的应用开发变得简单，更容易上手非侵入性的提供了一套常用的配置，但是用户可以随时覆盖，
  提供了更多的基础性、非业务性的功能（内置Web容器、权限认证机制、监控、应用配置管理等等）。完全不依赖于xml。 

spring boot 学习系列 (使用版本 1.5.3)

- [x] 1.springboot-quickstart -- springboot 项目结构解析 
- [x] 2.springboot-profiles -- 配置文件详解：自定义属性、随机数、多环境配置等（目前只使用properties,后续添加yml的配置方式用法）
- [x] 3.springboot-rest -- spring boot 构建restful api以及单元测试,替换默认json 解析框架，使用fastjson
- [x] 4.springboot-jersey -- springboot 整合jersey 实现restful. 
- [x] 5.springboot-thymeleaf -- spring boot 国际化相关处理和thymeleaf 模板语法的基本使用 
- [x] 6.springboot-freemarker -- 使用freemarker模板引擎 
- [x] 7.springboot-jsp -- spring boot 对jsp 的支持 
- [x] 8.springboot-velocity -- spring boot 使用velocity 版本情况说明 
- [x] 9.springboot-swagger -- spring boot 使用swagger构建restful api 
- [x] 10.springboot-handle-exception -- spring boot 统一异常处理（返回异常对象json 或者modelandview） 
- [x] 11.springboot-servlet -- spring boot 下使用servlet、filter、listener以及springmvc interceptor 的相关处理.
- [x] 12.springboot-aoplog -- spring boot log4j 使用aop方式添加日志.
- [x] 13.springboot-jdbctemplate -- spring boot 整合jdbctemplate 操作数据库. 
- [x] 14.springboot-spring-data-jpa -- spring boot 整合spring data jpa ,简化数据库操作 
- [x] 15.springboot-mybatis -- spring boot 整合mybatis 使用,提供两种方式:xml 和annotation的方式。 
- [x] 16.springboot-mybatis-mapper-plugin -- spring boot 整合mybatis 第三方mapper 插件、mybatis自动生成工具 generator 简化开发. 
- [x] 17.springboot-jdbctemplate-mutil-datasource -- spring boot 整合jdbctemplate 多数据源 
- [x] 18.springboot-jpa-mutil-datasource -- spring boot 整合spring data jpa 多数据源 
- [x] 19.springboot-mybatis-mutil-datasource -- spring boot 使用druid 数据源 整合mybatis 多数据源 
- [x] 20.springboot-redis -- spring boot 整合redis 
- [x] 21.springboot-redis-cache -- spring boot 使用redis 作为缓存实例 
- [x] 22.springboot-redis-cluster -- 添加了注释说明了JedisCluster 与RedisTemplate 操作RedisCluster 的一些使用原理 .
- [x] 23.springboot-redis-session -- spring boot 整合Redis .
- [x] 24.springboot-shiro -- spring boot 整合shiro 基本实例
- [x] 25.springboot-jwt -- spring boot 整合jwt 实现token 认证.	
- [x] 26.springboot-security -- spring boot 整合security 实现简单权限控制.	
- [x] 27.springboot-security-jwt -- spring boot security token 整合实现认证登陆.
- [x] 28.springboot-caching-ehcache -- spring boot框架缓存系列 ehcache .
- [x] 29.springboot-caching-redis -- redis 使用annotation 方式实现cache .
- [x] 30.springboot-rocketmq -- spring boot 整合rocketmq 测试.	
- [x] 31.springboot-dubbo -- spring boot 整合dubbo两种使用方式.
- [x] 32.springboot-mongo -- spring boot 整合mongo使用.
- [x] 33.springboot-elasticsearch -- spring boot 整合elasticsearch使用.

## 测试运行
```
git clone https://github.com/t-hong/springboot-examples.git

jdk8

更新所有maven 依赖

各个模块相对独立，可直接运行spring boot 启动类（推荐） 或者加入tomcat运行的方式 

注意：部分模块需要第三方服务的，一定要检查配置连接是否正确

```             
## 后续计划...
* 日志系列：
     *  Log4J2
     *  Logback  
* nosql系列：
     *  MongoDB
     *  memcached
     *  Elasticsearch
     *  Solr

* Message ......     
     * kafka
     * rabbitmq
## 参考资源：
* [推荐阿里小马哥视频](https://segmentfault.com/n/1330000009887617) 
* https://git.oschina.net/didispace/SpringBoot-Learning
* http://412887952-qq-com.iteye.com/category/356333
* https://git.oschina.net/jeff1993/springboot-learning-example
* http://blog.csdn.net/column/details/spring-boot.html


