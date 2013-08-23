package com.sanzhong.score.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sanzhong.score.pojo.Permission;

@Component
public class PermissionDAO extends BaseDAO{

	public List<Permission> findPermissionByRoleId(Integer role_id)throws Exception{
		String sql = "select permission.id,permission.name,permission.per_key,permission.auth from role_permission,permission" +
				" where permission.id=role_permission.permission_id and role_permission.role_id=?";
		List<Permission> permissionList = query(sql, new Object[]{role_id}, Permission.getRowMapper());
		return permissionList;
	}
	
}
