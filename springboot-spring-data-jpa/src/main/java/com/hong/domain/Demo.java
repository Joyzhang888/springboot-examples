package com.hong.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: Demo
 * @Description: (hibernate 实体类（可反向生成表结构）)
 * @author hong
 * @date 2017/4/25
 * @version v1.1
 */
@Entity
@Table(name="demo")
public class Demo {

    @Id
    @GeneratedValue
    private long id;//主键.
    private String name;//测试名称.


    public Demo() {
    }

    public Demo(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
