package com.mycompany.springframework.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.springframework.dao.mybatis.Ch13MemberDao;
import com.mycompany.springframework.dto.Ch13Member;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Ch13MemberService {
	public enum JoinResult{
		SUCCESS,
		FAIL_DUPLICATED_MID
		
	}
	
	public enum LoginResult{
		SUCCESS,
		FAIL_MID,
		FAIL_MPASSSWORD,
		FAIL_ENABLED
		
	}
	
	@Resource //db 주입 어노테이션
	private Ch13MemberDao memberDao;
	//컨트롤러에서 유효성 검사를 하고 ┌member 를 받는다.
	public JoinResult join(Ch13Member member) {
		boolean exist = isMid(member.getMid());
		if (exist) {
			return JoinResult.FAIL_DUPLICATED_MID;
		}
		
		memberDao.insert(member);
		return JoinResult.SUCCESS;
	
	}
	
	public boolean isMid(String mid) {
		Ch13Member member = memberDao.selectByMid(mid);
		if (member == null) {
			return false;
		}else {
			return true;
		}
		
	}
	
	public LoginResult login(Ch13Member member) {
		Ch13Member dbmember = memberDao.selectByMid(member.getMid());
		//1. 로그인
		if (dbmember == null) {
			return LoginResult.FAIL_MID;
		}
		//2. 아이디 존재 안함
		if (!dbmember.isMenabled()) {
			return LoginResult.FAIL_ENABLED;
		}
		//3. 비밀번호 틀림
		if (!dbmember.getMpassword().equals(member.getMpassword())) {
			return LoginResult.FAIL_MPASSSWORD;
		}
		return LoginResult.SUCCESS;
	}
	
	//세션방식으로 로그아웃한다면 서비스까지 할 필요는 없다.
/*	public void logout(String mid) {
		
	}
	*/

}
