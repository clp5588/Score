package com.sanzhong.score.pojo;

import java.io.Serializable;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("serial")
public class Operation extends BaseBean implements Serializable{

	private Integer id;
	private String name;
	private String oper_key;
    private Integer auth;

    public Integer getAuth() {
        return auth;
    }

    public void setAuth(Integer auth) {
        this.auth = auth;
    }

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
	public String getOper_key() {
		return oper_key;
	}
	public void setOper_key(String oper_key) {
		this.oper_key = oper_key;
	}
	@SuppressWarnings("rawtypes")
	public static RowMapper getRowMapper() throws Exception{
		return mapRow(Operation.class);
	}
}
