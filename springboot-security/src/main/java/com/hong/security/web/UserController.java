package com.hong.security.web;

import java.util.ArrayList;
import java.util.List;

import com.hong.security.domain.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



/**
 * 用户控制器.
 *
 * Created by hong on 2017/6/13.
 */
@RestController
public class UserController {

	/**
	 * 查询所用用户
	 * @return
	 */
	@GetMapping("/users")
	public ModelAndView list(Model model) {

		List<User> list = new ArrayList<>();	// 当前所在页面数据列表
		list.add(new User(1L, "zhangsan", 30));
		list.add(new User(2L,"hong", 29));
		model.addAttribute("title", "用户管理");
		model.addAttribute("userList", list);
		return new ModelAndView("users/list", "userModel", model);
	}
 

}
