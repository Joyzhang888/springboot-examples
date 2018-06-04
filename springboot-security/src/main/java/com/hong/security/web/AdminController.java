package com.hong.security.web;

import java.util.ArrayList;
import java.util.List;

import com.hong.security.domain.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * 用户控制器.
 * Created by hong on 2017/6/13.
 */
@RestController
@RequestMapping("/admins")
public class AdminController {

	/**
	 * 查询所用用户
	 * @return
	 */
	@GetMapping
	public ModelAndView list(Model model) {

		List<User> list = new ArrayList<>();	// 当前所在页面数据列表
		list.add(new User(1L,"hong", 30));
		list.add(new User(2L,"admin", 29));
		model.addAttribute("title", "管理员管理");
		model.addAttribute("userList", list);
		return new ModelAndView("users/list", "userModel", model);
	}
 

}
