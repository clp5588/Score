package com.sanzhong.score.dao;

import com.sanzhong.score.pojo.Operation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OperationDAO extends BaseDAO{

	
	public Operation findOperationByPermissionId(Integer perid) throws Exception{
		String sql="select operation.* from permission,operation where permission.operation_id=operation.id and permission.id=?";
		return (Operation) queryForObject(sql, new Object[]{perid}, Operation.getRowMapper());
	}
    @SuppressWarnings("unchecked")
    public List<Operation> findResourceByType() throws Exception {
        String sql = "select operation.* from operation";
        return query(sql,Operation.getRowMapper());
    }
}
