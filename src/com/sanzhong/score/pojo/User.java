package com.sanzhong.score.pojo;

import java.io.Serializable;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("serial")
public class User extends BaseBean implements Serializable {

	private Integer id;
	private String name;
	private String pass;
	private String account;
	private String reg_time;
	private Integer is_valid;
	private Integer is_sys;
	private List<Role> roleList;

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
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getReg_time() {
		return reg_time;
	}
	public void setReg_time(String reg_time) {
		this.reg_time = reg_time;
	}
	public Integer getIs_valid() {
		return is_valid;
	}
	public void setIs_valid(Integer is_valid) {
		this.is_valid = is_valid;
	}
	public Integer getIs_sys() {
		return is_sys;
	}
	public void setIs_sys(Integer is_sys) {
		this.is_sys = is_sys;
	}
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	@SuppressWarnings("rawtypes")
	public static RowMapper getRowMapper() throws Exception{
		return mapRow(User.class);
	}
}
