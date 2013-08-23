package com.sanzhong.score.listener;

import com.sanzhong.score.dao.ResourceDAO;
import com.sanzhong.score.pojo.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: LCD
 * Date: 13-8-18
 * Time: 下午3:22
 * To change this template use File | Settings | File Templates.
 */
public class ResourceListener implements ServletContextListener{

    private static Map<Integer,List<Resource>> map = new HashMap<Integer,List<Resource>>();
    private static Map<Integer, Resource> resourceMap = new HashMap<Integer, Resource>();

    public static Map<Integer, Resource> getResourceMap() {
        return resourceMap;
    }

    public static Map<Integer, List<Resource>> getMap() {
        return map;
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
        ResourceDAO resourceDAO = (ResourceDAO) applicationContext.getBean("resourceDAO");
        try {
            List<Resource> resources = resourceDAO.findAllResource();
            map.put(0,getResource(0,resources));
            for(Resource res:resources){
                if(res.getIs_module().intValue()!=0){
                    map.put(res.getId(),getResource(res.getId(),resources));
                }
            }
            for(Resource res:resources){
                resourceMap.put(res.getId(),res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Resource> getResource(Integer parent_id,List<Resource> resources){
        List<Resource> returnList = new ArrayList<Resource>();
        for (Resource res:resources){
            if(res.getParent_id().intValue()==parent_id){
                returnList.add(res);
            }
        }
        return returnList;
    }
}
