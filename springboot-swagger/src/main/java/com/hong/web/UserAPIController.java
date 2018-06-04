package com.hong.web;

import com.hong.domain.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * @ClassName: UserAPIController
 * @Description: (使用 swagger 展示API  )
 * @author hong
 * @date 2017/4/24
 * @version v1.1
 */
@RestController
@RequestMapping("/users")
public class UserAPIController {

    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @ApiOperation(value = "获取用户列表",notes ="")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public List<User> getUserList(){
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        List<User> u = new ArrayList<>(users.values());
        return u;
    }

    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value="/", method=RequestMethod.POST)
    public String postUser(@ModelAttribute User user) {
        // 处理"/users/"的POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        users.put(user.getId(), user);
        return "success";
    }

    @ApiOperation(value="获取用户信息", notes="根据用户ID获取对应用户信息")
    @ApiImplicitParams(@ApiImplicitParam(name ="id" ,value = "用户ID",required = true,dataType ="Long"))
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        return users.get(id);
    }


    @ApiOperation(value ="修改用户信息",notes ="根据用户ID修改对应用户信息")
    @ApiImplicitParams({ @ApiImplicitParam(name ="id" ,value ="用户ID",required = true,dataType = "Long"),
                         @ApiImplicitParam(name ="user",value ="用户实体对象",required = true,dataType = "User")})
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @ModelAttribute User user) {
        // 处理"/users/{id}"的PUT请求，用来更新User信息
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @ApiOperation(value ="获取用户信息",notes = "根据用户ID删除对应用户信息")
    @ApiImplicitParam(name = "id",value = "用户ID",required = true,dataType = "Long")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User
        users.remove(id);
        return "success";
    }
}
