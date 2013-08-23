package com.sanzhong.score.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.Assert;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class SplitPageResultSetExtractor implements ResultSetExtractor {

	private final int startIndex;// 起始行号
	private final int pageSize;// 结果集合的长度
	private final RowMapper rowMapper;// 行包装器

	public SplitPageResultSetExtractor(RowMapper rowMapper, int start, int len) {
		Assert.notNull(rowMapper, "RowMapper is required");
		this.rowMapper = rowMapper;
		this.startIndex = start;
		this.pageSize = len;
	}
/* 暂时不用会内存溢出
	public Object extractDatas(ResultSet rs) throws SQLException,
			DataAccessException {
		List result = new ArrayList();
		int rowNum = 0;
		int end = startIndex + pageSize;
		point: while (rs.next()) {
			++rowNum;
			if (rowNum < startIndex) {
				continue point;
			} else if (rowNum >= end) {
				break point;
			} else {
				result.add(this.rowMapper.mapRow(rs, rowNum));
			}
		}
		return result;
	}
*/
	public Object extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		List result = new ArrayList();
		rs.first();
		rs.relative(startIndex - 1);
		int count = 0;
		while (rs.next()) {
			count++;
			result.add(this.rowMapper.mapRow(rs, startIndex + count));
			if (count == pageSize) {
				break;
			}
		}
		return result;
	}
}
