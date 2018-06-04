package com.hong.mapper;


import org.apache.ibatis.annotations.*;
import com.hong.domain.User;

/**
 * @ClassName: UserMapper
 * @Description: (mybatis 注解方式使用)
 * @author hong
 * @date 2017/4/25
 * @version v1.1
 */
/** 在接口上 添加@Mapper 注解，声明对应接口是一个Mapper **/
@Mapper
public interface UserMapper {
    /**
     * 传参方式:
     * 1> 使用@Param
     * 2> 使用Map
     * 3> 使用对象
     *
     * 增删改查,MyBatis针对不同的数据库操作分别提供了不同的注解来进行配置:
     * @Insert
     * @Select
     * @Update
     * @Delete
     *
     *  具体详解请查看：http://www.mybatis.org/mybatis-3/zh/java-api.html
     */

    /** 返回结果的绑定 ,通过@Results 和@Result 来绑定
     *  通过情况下，没有配置的话，字段不相同的就不会对应上，同时如果配置了结果绑定时，部分字段没有对应的话，那么对应字段的值为null
     * **/
    @Results({
      @Result(property = "name",column ="name"),
      @Result(property = "age" ,column ="age"),
      @Result(property = "id",column = "age")
    })
    @Select("select * from user where id= #{id}")
    User findById(@Param("id") Long id);

    @Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);

    @Insert("update user set age=#{age},name=#{name} where id =#{id}")
    int update(User user);

    @Delete("delete from user where id=#{id}")
    int delete(Long id);

}

