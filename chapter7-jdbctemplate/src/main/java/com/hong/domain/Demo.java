package com.hong.domain;



/**
 * @ClassName: Demo
 * @Description:
 * @author hong
 * @date 2017/4/25
 * @version v1.1
 */
public class Demo {

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

    @Override
    public String toString() {
        return "Demo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
