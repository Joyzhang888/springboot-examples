package com.hong.xmlmapper;


import com.hong.domain.User;
import org.apache.ibatis.annotations.*;

/**
 * @ClassName: UserMapper
 * @Description: (mybatis xml方式使用)
 * @author hong
 * @date 2017/4/25
 * @version v1.1
 */

/** 在接口上 添加@Mapper 注解，声明对应接口是一个Mapper **/
@Mapper
public interface XUserMapper {

    User findById(@Param("id") Long id);

    int insert(@Param("name") String name, @Param("age") Integer age);

    int update(User user);

    int delete(Long id);

}

