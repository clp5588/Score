package com.sanzhong.score.pojo;

import java.io.Serializable;

import org.springframework.jdbc.core.RowMapper;

public class Permission extends BaseBean implements Serializable{

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

    public Integer getAuth() {
        return auth;
    }

    public void setAuth(Integer auth) {
        this.auth = auth;
    }

    public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public String getPer_key() {
		return per_key;
	}
	public void setPer_key(String per_key) {
		this.per_key = per_key;
	}
	public static RowMapper getRowMapper() throws Exception{
		return mapRow(Permission.class);
	}
	private Integer id ;
	private String name;
	private String per_key;
	private Integer auth;
	private Resource resource;
}
