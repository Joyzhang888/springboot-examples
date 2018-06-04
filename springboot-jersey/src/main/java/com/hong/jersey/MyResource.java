package com.hong.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by hong on 2017/5/31.
 */
// 关注点1：资源路径
@Path("/myresource")
public class MyResource {

    // 关注点2：资源方法
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public  String hello(){
        return  "hello jersey!";
    }
}
