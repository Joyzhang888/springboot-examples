#使用Thymeleaf模板引擎渲染web视图  

> 1.spring boot 使用Thymeleaf模板
> 2.Thymeleaf基础知识


一、资源文件的约定目录结构 
Maven的资源文件目录：/src/Java/resources 
spring-boot项目静态文件目录：/src/java/resources/static 
spring-boot项目模板文件目录：/src/java/resources/templates 
spring-boot静态首页的支持，即index.html放在以下目录结构会直接映射到应用的根目录下：
[html] view plain copy
classpath:/META-INF/resources/index.html    
classpath:/resources/index.html    
classpath:/static/index.html    
calsspath:/public/index.html    


##表达式支持的语法

    字面（Literals）
        文本文字（Text literals）: 'one text', 'Another one!',…
        数字文本（Number literals）: 0, 34, 3.0, 12.3,…
        布尔文本（Boolean literals）: true, false
        空（Null literal）: null
        文字标记（Literal tokens）: one, sometext, main,…
    文本操作（Text operations）
        字符串连接(String concatenation): +
        文本替换（Literal substitutions）: |The name is ${name}|
    算术运算（Arithmetic operations）
        二元运算符（Binary operators）: +, -, *, /, %
        减号（单目运算符）Minus sign (unary operator): -
    布尔操作（Boolean operations）
        二元运算符（Binary operators）:and, or
        布尔否定（一元运算符）Boolean negation (unary operator):!, not
    比较和等价(Comparisons and equality)
        比较（Comparators）: >, <, >=, <= (gt, lt, ge, le)
        等值运算符（Equality operators）:==, != (eq, ne)
    条件运算符（Conditional operators）
        If-then: (if) ? (then)
        If-then-else: (if) ? (then) : (else)
        Default: (value) ?: (defaultvalue)
    所有这些特征可以被组合并嵌套：
    
    'User is of type ' + (${user.isAdmin()} ? 'Administrator' : (${user.type} ?: 'Unknown'))

    参考: 博客 http://www.ityouknow.com/springboot/2016/05/01/springboot(%E5%9B%9B)-thymeleaf%E4%BD%BF%E7%94%A8%E8%AF%A6%E8%A7%A3.html
         thymeleaf官方指南 http://www.thymeleaf.org/doc/tutorials/2.1/thymeleafspring.html#integrating-thymeleaf-with-spring

###  国际化配置
#### 在页面上使用
springboot默认就支持国际化配置，只需要添加国际化配置文件即可
 +    格式为messages.properties(默认)，
 +    messages_zh_CN.properties(中文)
 +    messages_en_US.properties(英文)
使用方式: #{message}


关于spring boot thymeleaf国际化相关知识点:

(1) spring boot 加入thymeleaf；
     <!--spring boot 使用Thymeleaf模板引擎渲染web依赖-->
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-thymeleaf</artifactId>
     </dependency>

(2) 页面元素国际化；
     1. +    格式为messages.properties(默认，当找不到语言的配置的时候，使用该文件进行展示)
        +    messages_zh_CN.properties(中文)
        +    messages_en_US.properties(英文)

     2. th:text="${message}"

(3) spring boot默认国际化原理说明；
     1. 为什么命名必须是messages开头呢?
        org.springframework.boot.autoconfigure.MessageSourceAutoConfiguration：
        定义了 private String basename = "messages",我们没有在application.properties中
        配置spring.messages属性

     2. 为什么我看到的是中文（或者英文）呢？
        在Spring MVC应用程序中，用户的区域是通过区域解析器来识别的，它必须是实现LocaleResolver接口。
        Spring MVC提供了几个LocaleResolver实现，让你可以按照不同的条件来解析区域。除此之外，
        你还可以实现这个接口创建自己的区域解析器。如果没有做特殊的处理的话，Spring 采用的默认区域解析器
        是AcceptHeaderLocaleResolver。它通过检验HTTP请求的头部信息accept-language来解析区域。
        这个头部是由用户的wb浏览器底层根据底层操作系统的区域设置进行设定的。

(4) firefox浏览器修改区域语言；
     1. 浏览器地址栏输入:about:config
     2. 回车进入一个警告页面,搜索框输入accept，然后找到intl.accept_languages修改对应的值
        zh-cn, zh, en-us, en
        为了看到效果，修改为：
        en-us, en

(5)chrome浏览器修改区域语言；


(6)修改默认messages配置前缀；
     在MessageSourceAutoConfiguration中有定义:
     @ConfigurationProperties(prefix = "spring.messages")
     private String basename = "messages"

     所以,我们只需要修改application.properties文件即可:
     #指定message的basename，多个以逗号分隔，如果不加包名的话，默认从classpath路径开始，默认: messages
     spring.messages.basename=i18n/messages

     #设定加载的资源文件缓存失效时间，-1的话为永不过期，默认为-1
     spring.messages.cache-seconds= 3600

     #设定Message bundles的编码，默认: UTF-8
     #spring.messages.encoding=UTF-8

(7) 代码中如何获取国际化信息；
     @Autowired
     private MessageSource messageSource;

     需要注意的是messageSource是
     org.springframework.context.MessageSource

(8) 优化代码获取国际化信息；
     查看LocaleMessageSourceUtil.java

(9) 区域解析器之AcceptHeaderLocaleResolver；
     Spring采用的默认区域解析器是AcceptHeaderLocaleResolver。它通过检验HTTP请求的accept-language头部
     来解析区域。这个头部是由用户的web浏览器根据底层操作系统的区域设置进行设定。请注意，这个区域解析器无法改变
     用户的区域，因为它无法修改用户操作系统的区域设置。

(10) 会话区域解析器之SessionLocaleResolver；
     启动类App.java（按你的实际情况进行修改）配置区域解析器为SessionLocaleResolver，具体代码如下：

(11) Cookie区域解析器之CookieLocaleResolver；

(12)固定的区域解析器之FixedLocaleResolver ；

(13)使用参数修改用户的区域；
