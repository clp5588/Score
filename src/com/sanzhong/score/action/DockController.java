package com.sanzhong.score.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sanzhong.score.pojo.Permission;
import com.sanzhong.score.pojo.Resource;
import com.sanzhong.score.pojo.Role;
import com.sanzhong.score.pojo.User;

@Controller
@RequestMapping("/dock")
public class DockController {

	@RequestMapping(value = "/docks", method = RequestMethod.POST)
	@ResponseBody
	public List<Resource> dock(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Subject currentUser = SecurityUtils.getSubject();
		User user = (User) currentUser.getPrincipal();
		List<Resource> resList = new ArrayList<Resource>();
		for (Role role : user.getRoleList()) {
			for (Permission permission : role.getPermissions()) {
				if (permission.getResource().getIs_module() == 1&&!resList.contains(permission.getResource())) {
					resList.add(permission.getResource());
				}
			}
		}
		return resList;
	}
}
