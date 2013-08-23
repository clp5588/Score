package com.sanzhong.score.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("serial")
public class Role extends BaseBean implements Serializable{

	private Integer id;
	private String name;
	private String role_key;
	private List<Permission> permissions;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole_key() {
		return role_key;
	}
	public void setRole_key(String role_key) {
		this.role_key = role_key;
	}
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	public List<String> getPermissionKeys(){
		List<String> list = new ArrayList<String>();
		for(Permission permission:permissions){
			list.add(permission.getPer_key());
		}
		return list;
	}
	@SuppressWarnings("rawtypes")
	public static RowMapper getRowMapper() throws Exception{
		return mapRow(Role.class);
	}
}
