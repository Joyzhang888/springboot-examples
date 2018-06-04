package com.hong;

//import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.alibaba.fastjson.support.config.FastJsonConfig;
//import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName: Application
 * @Description: (Spring boot 启动类)
 * @author hong
 * @date 2017/4/20
 * @version v1.1
 */
@SpringBootApplication
/**@SpringBootApplication 等价于@Configuration，@EnableAutoConfiguration和@ComponentScan **/
public class Application { //extends WebMvcConfigurerAdapter {
    /**
     * 如果我们不使用默认的json 解析工具，怎么替换成比较习惯的json框架是fastjson呢？ 有两种方式：
     * 第一种方法就是：
     *（1）启动类继承extends WebMvcConfigurerAdapter
     *（2）覆盖方法configureMessageConverters
     *
     * 第二种方方法是：
     *（1）在App.java启动类中，注入Bean : HttpMessageConverters
     *
     */
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        super.configureMessageConverters(converters);
//
//        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//
//        fastJsonConfig.setSerializerFeatures(
//                SerializerFeature.PrettyFormat
//        );
//
//        List<MediaType> mediaTypeList=new ArrayList<>();
//        mediaTypeList.add(MediaType.APPLICATION_JSON_UTF8);
//        fastConverter.setSupportedMediaTypes(mediaTypeList);
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//
//        converters.add(fastConverter);
//    }

    /**
     * 1.需要定义一个convert转换消息的对象
     * 2.创建配置信息，加入配置信息：比如是否需要格式化返回的json
     * 3.converter中添加配置信息
     * 4.convert添加到converters当中
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 1.需要定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        //2.创建配置信息，加入配置信息：比如是否需要格式化返回的json
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

        //3.converter中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);

        /**
         *  设置json 返回格式和编码方式 处理乱码问题
         */
        List<MediaType> mediaTypeList=new ArrayList<>();
        mediaTypeList.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(mediaTypeList);

        //4.convert添加到converters当中
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

}
