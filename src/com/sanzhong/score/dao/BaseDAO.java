package com.sanzhong.score.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
@Component
public abstract class BaseDAO extends JdbcTemplate{
	@Resource(name="dataSource")
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	public DataSource getDataSource(){
		return this.dataSource;
	}
	public List<Map<String,Object>> queryForListPage(String sql, int startRow, int rowsCount)
			throws DataAccessException {
		return queryForListPage(sql, startRow, rowsCount,getColumnMapRowMapper());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map<String,Object>> queryForListPage(String sql, int startRow, int rowsCount,
			RowMapper rowMapper) throws DataAccessException {
		return (List) query(sql, new SplitPageResultSetExtractor(rowMapper,startRow, rowsCount));
	}
}
