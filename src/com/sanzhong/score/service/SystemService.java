package com.sanzhong.score.service;

import com.sanzhong.score.dao.OperationDAO;
import com.sanzhong.score.dao.ResourceDAO;
import com.sanzhong.score.pojo.Operation;
import com.sanzhong.score.pojo.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: LCD
 * Date: 13-8-20
 * Time: 上午12:05
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SystemService {

    @Autowired
    private ResourceDAO resourceDAO;
    @Autowired
    private OperationDAO operationDAO;

    public List<Resource> findAllResource() throws Exception {
        return resourceDAO.findAllResource();
    }

    public List<Operation> findResourceType()throws Exception{
        return operationDAO.findResourceByType();
    }

    public void addResource(Resource resource) {
        resourceDAO.insert(resource);
    }
}
