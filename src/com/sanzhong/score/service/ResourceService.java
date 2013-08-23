package com.sanzhong.score.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanzhong.score.dao.ResourceDAO;

@Service
public class ResourceService {

	@Autowired
	private ResourceDAO resourceDAO;
	
}
