package com.mycompany.springframework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mycompany.springframework.dao.Ch12DaoInterface;

import lombok.extern.slf4j.Slf4j;


@Service //생성자 주입하려면 관리객체가 되야하므로 이 어노테이션을 써줘야함
@Slf4j
public class Ch12Service8 {			
	
	@Autowired @Qualifier("ch12Dao4")
	//@Resource("name="ch12Dao4")
	private Ch12DaoInterface dao;
	
	public Ch12Service8() {
		log.info("실행");
	}
	
}
