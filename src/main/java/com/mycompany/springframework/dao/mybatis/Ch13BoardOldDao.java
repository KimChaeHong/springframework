package com.mycompany.springframework.dao.mybatis;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycompany.springframework.dto.Ch13Board;

@Repository
public class Ch13BoardOldDao {
	@Autowired
	private SqlSessionTemplate sst;
	
	public int insert(Ch13Board board) {
		int row = sst.insert("com.mycompany.springframework.dao.mybatis.Ch13BoardDao.insert",board);
		return row;
	}
	
}
