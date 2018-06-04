package com.hong;

import com.hong.web.HelloController;
import com.hong.web.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author hong
 * @version v1.1
 * @ClassName: ApplicationMvcTest
 * @Description: (Rest API 请求测试)
 * @date 2017/4/20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication(scanBasePackageClasses = {MockServletContext.class})
@WebAppConfiguration
public class ApplicationMvcTest {


    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new HelloController(), new UserController()).build();
    }

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("hello spring boot")));
    }

    @Test
    public void testRestApi() throws Exception {
        RequestBuilder requestBuilder = null;

        //获取user列表
        mvc.perform(MockMvcRequestBuilders.get("/users/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

        //添加user 信息
        requestBuilder = MockMvcRequestBuilders.post("/users/")
                .param("id", "1")
                .param("age", "23")
                .param("name", "zhangsan");
        mvc.perform(requestBuilder).andExpect(content().string(equalTo("success")));

        //获取user 列表信息
        mvc.perform(MockMvcRequestBuilders.get("/users/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"zhangsan\",\"age\":23}]")));

        //获取user id=1的信息
        requestBuilder = MockMvcRequestBuilders.get("/users/1");
        mvc.perform(requestBuilder).andExpect(content().string(equalTo("{\"id\":1,\"name\":\"zhangsan\",\"age\":23}")));

        //修改user id=1的信息
        requestBuilder = MockMvcRequestBuilders.put("/users/1")
                .param("name","lisi")
                .param("age","18");
        mvc.perform(requestBuilder).andExpect(content().string(equalTo("success")));

        requestBuilder = MockMvcRequestBuilders.get("/users/1");
        mvc.perform(requestBuilder).andExpect(content().string(equalTo("{\"id\":1,\"name\":\"lisi\",\"age\":18}")));

        //删除user id=1的元素
        requestBuilder = MockMvcRequestBuilders.delete("/users/1");
        mvc.perform(requestBuilder).andExpect(content().string(equalTo("success")));

        requestBuilder = MockMvcRequestBuilders.get("/users/").accept(MediaType.APPLICATION_JSON);
        mvc.perform(requestBuilder).andExpect(content().string(equalTo("[]")));

    }





}
