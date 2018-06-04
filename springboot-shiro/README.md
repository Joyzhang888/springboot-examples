
## Spring Boot 整合Shiro 基本步骤:

   *  pom.xml中添加Shiro依赖；
      >在Spring中注入类都是使用配置文件的方式，在Spring Boot中是使用注解的方式，那么应该如何进行实现呢？
       最简单的配置就是注入这两个类就ok了 
      * 第一就是ShiroFilterFactory
      * 第二就是SecurityManager
   
   *  注入Shiro Factory和SecurityManager.
   
   *  身份认证
   
   *  权限控制
   
   
## Spring Boot Validator校验
    @null         验证对象是否为空
    @notnull      验证对象是否为非空
    @asserttrue   验证 boolean 对象是否为 true
    @assertfalse  验证 boolean 对象是否为 false
    @min          验证 number 和 string 对象是否大等于指定的值
    @max          验证 number 和 string 对象是否小等于指定的值
    @decimalmin   验证 number 和 string 对象是否大等于指定的值，小数存在精度
    @decimalmax   验证 number 和 string 对象是否小等于指定的值，小数存在精度
    @size         验证对象（array,collection,map,string）长度是否在给定的范围之内
    @digits       验证 number 和 string 的构成是否合法
    @past         验证 date 和 calendar 对象是否在当前时间之前
    @future       验证 date 和 calendar 对象是否在当前时间之后
    @pattern      验证 string 对象是否符合正则表达式的规则
    @Email        验证邮箱
   
会进入认证方法的情况(doGetAuthenticationInfo):
 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm (就是我们自定义的MyShiroRealm)执行必须的认证检查
 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法

 
   
会进入授权方法一共有三种情况(doGetAuthorizationInfo):
1、subject.hasRole(“admin”) 或 subject.isPermitted(“admin”)：自己去调用这个是否有什么角色或者是否有什么权限的时候；
2、@RequiresRoles("admin") ：在方法上加注解的时候；
3、[@shiro.hasPermission name = "admin"][/@shiro.hasPermission]：在页面上加shiro标签的时候，即进这个页面的时候扫描到有这个标签的时候。

