package com.mycompany.springframework.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mycompany.springframework.dao.mybatis.Ch13MemberDao;
import com.mycompany.springframework.dto.Ch13Member;

import lombok.extern.slf4j.Slf4j;

@Service 
@Slf4j
public class Ch17UserDetailService implements UserDetailsService{
	@Autowired
	private Ch13MemberDao memberDao;
	
	@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
			Ch13Member member = memberDao.selectByMid(username);
			if(member == null) {
				throw new UsernameNotFoundException("Bad username");
			}
			
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(member.getMrole()));
		
			UserDetails userDetails = new Ch17UserDetails(member, authorities);
			
			return userDetails;//userdetials를 가져와야함
		}
	
	
	
	
	
	
}
