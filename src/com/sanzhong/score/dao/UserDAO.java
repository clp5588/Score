package com.sanzhong.score.dao;

import org.springframework.stereotype.Component;

import com.sanzhong.score.pojo.User;

@Component
public class UserDAO extends BaseDAO{

	public User findByName(String account) throws Exception {
		String sql = "select id,name,pass,account,reg_time,is_valid,is_sys from user where account=? and is_valid=1";
		User user = (User)queryForObject(sql,new Object[]{account},User.getRowMapper());
		if(user==null){
			return null;
		}
		return user;
	}

}
