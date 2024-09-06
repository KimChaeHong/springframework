package com.mycompany.springframework.dao.mybatis;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.springframework.dto.Ch13Member;

@Mapper
public interface Ch13MemberDao {

	int insert(Ch13Member member);
	Ch13Member selectByMid(String mid);
	
	/*public void insert(Ch13Member member);
	public Ch13Member selectByMid(String mid);*/

}
