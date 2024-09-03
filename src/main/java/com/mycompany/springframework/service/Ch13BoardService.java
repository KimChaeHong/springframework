package com.mycompany.springframework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.springframework.dao.mybatis.Ch13BoardDao;
import com.mycompany.springframework.dto.Ch13Board;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Ch13BoardService {
	@Autowired //관리 객체를 부여하고 mybatis가 관리객체를 만들어서 자동으로 객체 생성해준다.
	private Ch13BoardDao boardDao;
	
	//게시물 목록 가져오기
	public List<Ch13Board> getBoardList(){
		return null;
	}
	
	//게시물 하나 가져오기
	public Ch13Board getBoard(int bno) {
		return null;
	}
	
	//게시물 작성
	public void writeBoard(Ch13Board board) {
		
		log.info("실행");
		boardDao.insert(board);
	}
	
	//게시물 수정
	public void updateBoard(Ch13Board board) {
		
	}
	
	//게시물 삭제
	public void deleteBoard(int bno) {
		
	}
}
