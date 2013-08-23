package com.sanzhong.score.service;

import com.sanzhong.score.dao.PermissionDAO;
import com.sanzhong.score.pojo.Permission;
import com.sanzhong.score.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: LCD
 * Date: 13-8-19
 * Time: 上午12:03
 * To change this template use File | Settings | File Templates.
 */
@Service
public class PermissionService {
    @Autowired
    private PermissionDAO permissionDAO;

    public List<Permission> findPermissionsByRole(List user)throws Exception{
//        return permissionDAO.findPermissionByUserId(user.getAccount());
        return null;
    }
}
