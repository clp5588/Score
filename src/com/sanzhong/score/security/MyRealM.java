package com.sanzhong.score.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.sanzhong.score.pojo.Role;
import com.sanzhong.score.pojo.User;
import com.sanzhong.score.service.UserService;

public class MyRealM extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user = null;
		try {
			user = userService.getUser(token.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user != null) {
			return new SimpleAuthenticationInfo(user, user.getPass(), getName());
		} else {
			return null;
		}
	}

	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String account = (String) principals.fromRealm(getName()).iterator().next();
		User user = null;
		try {
			user = userService.getUser(account);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			for (Role role : user.getRoleList()) {
				info.addRole(role.getName());
				info.addStringPermissions(role.getPermissionKeys());
			}
			return info;
		} else {
			return null;
		}
	}

}
