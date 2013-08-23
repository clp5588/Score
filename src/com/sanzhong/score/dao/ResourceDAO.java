package com.sanzhong.score.dao;

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

    public void insert(Resource resource) {
        String sql = "insert into resource(name,url,is_module,icon,parent_id,auth)values(?,?,?,?,?,?)";
        update(sql,resource.getName(),resource.getUrl(),resource.getIs_module(),resource.getIcon(),resource.getParent_id(),resource.getAuth());
    }
}
