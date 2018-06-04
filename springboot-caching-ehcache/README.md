##Spring Boot Cache 基本理论;
  >Spring 3.1 引入了激动人心的基于注释（annotation）的缓存（cache）技术，它本质上不是一个具体的缓存实现方案（例如EHCache 或者 OSCache、Redis等），而是一个对缓存使用的抽象，通过在既有代码中添加少量它定义的各种 annotation，即能够达到缓存方法的返回对象的效果。
  >Spring 的缓存技术还具备相当的灵活性，不仅能够使用 SpEL（Spring Expression Language）来定义缓存的 key 和各种 condition，还提供开箱即用的缓存临时存储方案，也支持和主流的专业缓存例如 EHCache 集成。
  * 通过少量的配置 annotation 注释即可使得既有代码支持缓存
  * 支持开箱即用 Out-Of-The-Box，即不用安装和部署额外第三方组件即可使用缓存
  * 支持 Spring Express Language，能使用对象的任何属性或者方法来定义缓存的 key 和 condition
  * 支持 AspectJ，并通过其实现任何方法的缓存支持
  * 支持自定义 key 和自定义缓存管理者，具有相当的灵活性和扩展性
  
  
  
##实现方式主要在方法上加上注解，可以注解的注解类有

      Cacheable 支持如下几个参数：
      value：缓存位置名称，不能为空，如果使用EHCache，就是ehcache.xml中声明的cache的name
      key：缓存的key，默认为空，既表示使用方法的参数类型及参数值作为key，支持SpEL
      condition：触发条件，只有满足条件的情况才会加入缓存，默认为空，既表示全部都加入缓存，支持SpEL
      
      //将缓存保存进andCache，并使用参数中的userId加上一个字符串(这里使用方法名称)作为缓存的key   
      @Cacheable(value="andCache",key="#userId + 'findById'")  
      public SystemUser findById(String userId) {  
          SystemUser user = (SystemUser) dao.findById(SystemUser.class, userId);        
          return user ;         
      }  
      //将缓存保存进andCache，并当参数userId的长度小于32时才保存进缓存，默认使用参数值及类型作为缓存的key  
      @Cacheable(value="andCache",condition="#userId.length < 32")  
      public boolean isReserved(String userId) {  
         System.out.println("hello andCache"+userId);  
         return false;  
     }  
     
     @CacheEvict 支持如下几个参数：
     value：缓存位置名称，不能为空，同上
     key：缓存的key，默认为空，同上
     condition：触发条件，只有满足条件的情况才会清除缓存，默认为空，支持SpEL
     allEntries：true表示清除value中的全部缓存，默认为false
     
     //清除掉指定key的缓存  
     @CacheEvict(value="andCache",key="#user.userId + 'findById'")  
     public void modifyUserRole(SystemUser user) {  
              System.out.println("hello andCache delete"+user.getUserId());  
     }  
       
     //清除掉全部缓存  
     @CacheEvict(value="andCache",allEntries=true)  
     public void setReservedUsers() {  
        System.out.println("hello andCache deleteall");  
     }  
     
     @CachePut 注释，这个注释可以确保方法被执行，同时方法的返回值也被记录到缓存中，实现缓存与数据库的同步更新，理解为update语句。