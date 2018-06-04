# 本章内容
###构建一个较为复杂的RESTful API以及单元测试 
###spring boot 返回json 数据 和 替换默认的json框架，使用第三方json 框架fastjson 
  
  
##提供两种配置fastjson方式：
   >第一种方法就是：
   * 启动类继承extends WebMvcConfigurerAdapter
   * 覆盖方法configureMessageConverters
   >第二种方方法是：
   * 在App.java启动类中，注入Bean : HttpMessageConverters
     
     
     
     
rest(restful)架构属性:
   * 性能（Performance）
   * 可伸缩性（Scalability）
   * 统一接口简化性（Simplicity of a uniform Interface）
   * 组件可修改性（Modifiability of components）
   * 组件通讯可见性（Visibility of communication between components）
   * 组件可移植性（Portability of component）
   * 可靠性（Reliability）
   
统一接口（Uniform interface):
   * 资源识别（Identification of resources）
     - URI（Uniform Resource Identifier ）
   * 资源操作（Manipulation of resources through representations）
     - HTTP verbs：GET、PUT、POST、DELETE
   * 自描述消息（Self-descriptive messages）
     - Content-Type
     - MIME-Type
     - Media Type： application/javascript、 text/html
   * 超媒体（HATEOAS）
     - Hypermedia As The Engine Of Application State
     
     
Spring Boot REST:
   * 核心接口
     * 定义相关
       - @Controller
       - @RestController
     * 映射相关
       - @RequestMapping
       - @PathVariable
     * 请求相关
       - @RequestParam
       - @RequestHeader
       - @CookieValue
       - RequestEntity
     * 响应相关
       - @ResponseBody
       - ResponseEntity
     
     
REST 服务端实践：    
  * Spring Boot REST 应用 
    - spring mvc rest 支持
    - jersy rest 支持  
  * HATEOAS 应用
  * 文档生成等
   
     
  