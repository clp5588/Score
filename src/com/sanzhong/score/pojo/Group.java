package com.sanzhong.score.pojo;

import java.io.Serializable;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("serial")
public class Group extends BaseBean implements Serializable{

	private Integer id;
	private String name;
	private List<User> users;
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
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	@SuppressWarnings("rawtypes")
	public static RowMapper getRowMapper() throws Exception{
		return mapRow(Group.class);
	}
}
