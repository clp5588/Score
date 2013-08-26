package com.sanzhong.score.dao;

import com.sanzhong.score.common.Page;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.sanzhong.score.pojo.Resource;

import java.util.List;

@Component
public class ResourceDAO extends BaseDAO {

    @SuppressWarnings("unchecked")
    public Resource findResourceByPermissionId(Integer permission_id) throws Exception {
        String sql = "select resource.* from permission,resource where permission.resource_id=resource.id and permission.id=?";
        return (Resource) queryForObject(sql, new Object[]{permission_id}, Resource.getRowMapper());
    }
    @SuppressWarnings("unchecked")
    public List<Resource> findAllResource() throws Exception {
        String sql = "select * from resource";
        return query(sql, Resource.getRowMapper());
    }

    public List<Resource> findAllResourceByPage(Page page) throws Exception {
        String sql = "select * from resource limit "+(page.getCurrent()-1)*page.getPerPage()+","+page.getPerPage();
        return query(sql, Resource.getRowMapper());
    }
    public List<Resource> findResourceByPage(Page page,String name) throws Exception {
        String sql = "select * from resource where name like ? limit "+(page.getCurrent()-1)*page.getPerPage()+","+page.getPerPage();
        return query(sql,new Object[]{"%"+name+"%"}, Resource.getRowMapper());
    }
    public void insert(Resource resource) {
        String sql = "insert into resource(name,url,is_module,icon,parent_id,auth)values(?,?,?,?,?,?)";
        update(sql,resource.getName(),resource.getUrl(),resource.getIs_module(),resource.getIcon(),resource.getParent_id(),resource.getAuth());
    }

    public int findResourceSize(String name) {
        String sql = "select count(*) from resource where name like ?";
        return queryForInt(sql,"%"+name+"%");
    }
    public int findAllResourceSize() {
        String sql = "select count(*) from resource";
        return queryForInt(sql);
    }

    public void deleteResourceById(String id) {
        String sql = "delete from resource where id=?";
        update(sql,id);
    }

    public void modifyResource(Resource resource) {
        String sql = "update resource set name=?,url=?,parent_id=?,is_module=?,icon=?,auth=? where id=?";
        update(sql,resource.getName(),resource.getUrl(),resource.getParent_id(),resource.getIs_module(),
                resource.getIcon(),resource.getAuth(), resource.getId());
    }
}
