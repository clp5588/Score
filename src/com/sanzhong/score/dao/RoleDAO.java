package com.sanzhong.score.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sanzhong.score.pojo.Role;

@Component
public class RoleDAO extends BaseDAO{

	@SuppressWarnings("unchecked")
	public List<Role> findGroupRoleByAccount(String account)throws Exception{
		String sql = "select role.id,role.name,role.role_key from user,group_user,group_role,role " +
				"where user.id=group_user.user_id and group_user.group_id=group_role.group_id " +
				"and role.id=group_role.role_id and user.account=?";
		List<Role> groupRoleList = query(sql,new Object[]{account},Role.getRowMapper());
		return groupRoleList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Role> findUserRoleByAccount(String account) throws Exception{
		String sql = "select role.id,role.name,role.role_key from user,user_role,role " +
				"where user.id=user_role.user_id and role.id=user_role.role_id where user.account=?";
		List<Role> userRoleList = query(sql,new Object[]{account},Role.getRowMapper());
		return userRoleList;
	}

	@SuppressWarnings("unchecked")
	public List<Role> findAllRole() throws Exception {
		String sql = "select role.* from role";
		List<Role> roleList = query(sql, Role.getRowMapper()); 
		return roleList;
	}
}
