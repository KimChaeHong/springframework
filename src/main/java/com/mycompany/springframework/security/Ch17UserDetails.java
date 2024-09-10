package com.mycompany.springframework.security;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.mycompany.springframework.dto.Ch13Member;

public class Ch17UserDetails extends User{
	private Ch13Member member;
	
	public Ch17UserDetails(Ch13Member member,
							List<GrantedAuthority> authorities) {
		//자바 6장
		super(
				member.getMid(),
				member.getMpassword(),
				member.isMenabled(),
				true,true,true, 
				authorities);
			
		this.member = member;
	}
	
	public Ch13Member getMember() {
		return member;
	}
}
