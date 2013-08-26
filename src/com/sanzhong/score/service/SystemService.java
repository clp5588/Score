package com.sanzhong.score.service;

import com.sanzhong.score.common.Page;
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

    public int findAllResourceSize() {
        return findResourceSize(null);
    }
    public int findResourceSize(String name){
        if(name!=null){
            return resourceDAO.findResourceSize(name.trim());
        }else {
            return resourceDAO.findAllResourceSize();
        }
    }
    public List<Resource> findAllResourceByPage(Page page) throws Exception {
        return this.findResourceByPage(page,null);
    }
    public List<Resource> findResourceByPage(Page page,String name) throws Exception {
        if(name!=null){
            return resourceDAO.findResourceByPage(page,name.trim());
        }else {
            return resourceDAO.findAllResourceByPage(page);
        }
    }

    public void deleteResource(String id) {
        resourceDAO.deleteResourceById(id);
    }

    public void modifyResource(Resource resource) {
        resourceDAO.modifyResource(resource);
    }
}
