package com.sanzhong.score.action;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sanzhong.score.pojo.User;
import com.sanzhong.score.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/add")
	public String test(@RequestParam("username")String account,@RequestParam("password")String password) {
		Subject subject = SecurityUtils.getSubject();
		subject.login(new UsernamePasswordToken(account,password));
		return "index";
	}
	
	@RequestMapping(value="/userlist",method=RequestMethod.POST)
	public @ResponseBody List<User> getUserList(){
		return null;
	}
}
