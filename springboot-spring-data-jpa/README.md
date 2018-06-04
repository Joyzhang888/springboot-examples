##Hibernate ORM是JPA规范的一个实现。
  >Jpa是一种规范，而Hibernate是它的一种实现。除了Hibernate，还有EclipseLink(曾经的toplink)，OpenJPA等可供选择，所以使用Jpa的一个好处是，可以更换实现而不必改动太多代码。
   从上面这个描述，我们能就是能看出: JPA定义了一个规范，Hibernate是其中的一种实现方式可以，所以我们可以说：Hibernate是JPA的一种实现方式。但是这么说就有点欠妥当了：开发是使用hibernate还是 jpa。
   如果你回答使用JPA的话，那么你根本做不了什么事情，因为你需要使用它具体的一种实现方式，比如：Hibernate,EclipseLink,toplink。如果回答说是使用Hibernate的话，还勉强说的过去，
   但是在Hibernate中也有JPA的影子。但是这里不要造成一个误解，hibernate一定依赖JPA什么之类的，JPA现在只是Hibernate功能的一个子集。Hibernate 从3.2开始，
   开始兼容JPA的。Hibernate3.2获得了Sun TCK的 JPA(Java  Persistence API) 兼容认证。
   那么我们在描述的时候，别人问你持久化具体使用了什么，我们可以说：使用了基于Hibernate实现的JPA，或者是Hibernate JPA，那么加上spring data的，我们一般都简化说：
   spring data jpa，一般默认的就是使用了hibernate进行实现，现在网上这方面的资料也比较多，可能就约定俗成了。当然你要别人清楚的话，可以自己在进行补充下。
   
*  创建实体类Demo,如果已经存在，可以忽略。
*  创建jpa repository类操作持久化。
*  创建service类。
*  创建restful请求类。
*  测试