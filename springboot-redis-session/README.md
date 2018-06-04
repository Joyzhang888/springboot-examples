

## Spring Boot 使用Redis 实现Session共享

 * 1.引入相关依赖jar包，在maven的pom.xml文件中增加如下图依赖：
 
         <!--Spring Boot Redis 依赖-->
         <dependency>
             <groupId>org.springframework.boot</groupId>
             <artifactId>spring-boot-starter-redis</artifactId>
             <version>1.3.2.RELEASE</version>
         </dependency>
 
         <!-- Spring Redis Session相关依赖  -->
         <dependency>
             <groupId>org.springframework.session</groupId>
             <artifactId>spring-session-data-redis</artifactId>
             <version>1.3.0.RELEASE</version>
         </dependency>
         
  * 2.创建一个RedisSession 的配置类，@EnableRedisHttpSession注解开启Springsession支持.
        
  * 3.配置Spring Boot  默认的Redis 连接属性
      
          # REDIS (RedisProperties)
          # Redis数据库索引（默认为0）
          spring.redis.database=0
          # Redis服务器地址
          spring.redis.host=192.168.6.204
          # Redis服务器连接端口
          spring.redis.port=6379
          # Redis服务器连接密码（默认为空）
          spring.redis.password=
          # 连接池最大连接数（使用负值表示没有限制）
          spring.redis.pool.max-active=8
          # 连接池最大阻塞等待时间（使用负值表示没有限制）
          spring.redis.pool.max-wait=-1
          # 连接池中的最大空闲连接
          spring.redis.pool.max-idle=8
          # 连接池中的最小空闲连接
          spring.redis.pool.min-idle=0
          # 连接超时时间（毫秒）
          spring.redis.timeout=0
      
   其他集中管理参照：http://docs.spring.io/spring-session/docs/1.2.2.RELEASE/reference/html5/#httpsession-jdbc       
         