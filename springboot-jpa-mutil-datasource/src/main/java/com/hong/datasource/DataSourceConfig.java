package com.hong.datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;


/**
 * @ClassName: DataSourceConfig 
 * @Description: (多数据源配置)
 * @author hong
 * @date 2017/4/26
 * @version v1.1
 */
@Configuration
/** 1.5.x 时,需要指定配置文件的地址,否则获取不到对应属性 **/
@PropertySource(ignoreResourceNotFound = true,value = "classpath:application.properties")
public class DataSourceConfig {

    /**
     * 多数据源配置
     *
     * 创建一个Spring配置类，定义两个DataSource用来读取application.properties中的不同配置。
     * 如下例子中，
     * 主数据源配置为spring.datasource.primary开头的配置，
     * 第二数据源配置为spring.datasource.secondary开头的配置
     */
    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }



}
