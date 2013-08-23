package com.sanzhong.score.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanzhong.score.dao.OperationDAO;
import com.sanzhong.score.dao.PermissionDAO;
import com.sanzhong.score.dao.ResourceDAO;
import com.sanzhong.score.dao.RoleDAO;
import com.sanzhong.score.dao.UserDAO;
import com.sanzhong.score.pojo.Operation;
import com.sanzhong.score.pojo.Permission;
import com.sanzhong.score.pojo.Resource;
import com.sanzhong.score.pojo.Role;
import com.sanzhong.score.pojo.User;

@Service
public class UserService {

	@Autowired
	private UserDAO userDao;
	@Autowired
	private RoleDAO roleDao;
	@Autowired
	private PermissionDAO permissionDao;
	@Autowired
	private ResourceDAO resourceDao;
	@Autowired
	private OperationDAO operationDao;
	
	public User getUser(String account) throws Exception{
		User user = userDao.findByName(account);
		List<Role> allRoleList = new ArrayList<Role>();
		if(user.getIs_sys()==1){
			allRoleList = roleDao.findAllRole();
		}else{
			List<Role> groupRoleList = roleDao.findGroupRoleByAccount(account);
			List<Role> userRoleList = roleDao.findUserRoleByAccount(account);
			allRoleList.addAll(groupRoleList);
			allRoleList.addAll(userRoleList);
		}
		for(Role role:allRoleList){
			List<Permission> permissionList = permissionDao.findPermissionByRoleId(role.getId());
			for(Permission permission:permissionList){
				Resource res = resourceDao.findResourceByPermissionId(permission.getId());
				permission.setResource(res);
			}
			role.setPermissions(permissionList);
		}
		user.setRoleList(allRoleList);
		return user;
	}
	
}
