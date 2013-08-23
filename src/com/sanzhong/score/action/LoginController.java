package com.sanzhong.score.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sanzhong.score.listener.ResourceListener;
import com.sanzhong.score.service.UserService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sanzhong.score.pojo.Permission;
import com.sanzhong.score.pojo.Resource;
import com.sanzhong.score.pojo.Role;
import com.sanzhong.score.pojo.User;
import com.sanzhong.score.util.MD5Util;

@Controller
@RequestMapping("/")
public class LoginController {

	Logger logger = Logger.getLogger(LoginController.class);

	@RequestMapping(value = "/login",method=RequestMethod.POST)
	public @ResponseBody Map<String,String> login(@RequestParam("username")String account,@RequestParam("password")String password) {
		Map<String,String> map = new HashMap<String,String>();
		if(account==null||password==null||"".equals(account)||"".equals(password)){
			map.put("success", "false");
			map.put("msg", "用户名或密码不能为空!");
			return map;
		}
		Subject subject = SecurityUtils.getSubject();
		try{
			password = MD5Util.getMD5((password+"{"+account+"}").getBytes());
			subject.login(new UsernamePasswordToken(account,password));
		}catch(UnknownAccountException e){
			map.put("success", "false");
			map.put("msg", "用户名不存在!");
			return map;
		}catch(IncorrectCredentialsException e){
			map.put("success", "false");
			map.put("msg", "用户名或密码错误!");
			return map;
		}catch(AuthenticationException e){
			map.put("success", "false");
			map.put("msg", "未知错误!联系管理员!");
			logger.warn("错误详情:-->"+e.getMessage());
			return map;
		}
		map.put("success", "true");
		map.put("url","/index.json");
		return map;
	}
	@RequestMapping(value="/login",method= RequestMethod.GET)
    public String showLoginForm() {
        return "login";
    }
	@RequestMapping(value="/index",method= RequestMethod.GET)
    public String index(HttpServletRequest request) {
		Subject currentUser = SecurityUtils.getSubject();
		User user = (User) currentUser.getPrincipal();
		Map<Resource,List<Resource>> resourceMap = new HashMap<Resource,List<Resource>>();
		for (Role role : user.getRoleList()) {
			for (Permission permission : role.getPermissions()) {
                Resource parentResource = ResourceListener.getResourceMap().get(permission.getResource().getParent_id());
                Resource resource = ResourceListener.getResourceMap().get(permission.getResource().getId());
                if(resourceMap.containsKey(parentResource)){
                    resourceMap.get(parentResource).add(resource);
                }else{
                    List<Resource> resourceList = new ArrayList<Resource>();
                    resourceList.add(resource);
                    resourceMap.put(parentResource,resourceList);
                }
            }
        }
        request.setAttribute("resourceMap",resourceMap);
        request.setAttribute("user",user);
		return "index";
    }

    @RequestMapping(value = "/logout" ,method = RequestMethod.POST)
    public String logout(){
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser!=null)
        currentUser.logout();
        return "login";
    }
}
