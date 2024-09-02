package com.mycompany.springframework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.springframework.dao.Ch12Dao1;
import com.mycompany.springframework.dao.Ch12Dao2;
import com.mycompany.springframework.dao.Ch12Dao3;

import lombok.extern.slf4j.Slf4j;


@Service //생성자 주입하려면 관리객체가 되야하므로 이 어노테이션을 써줘야함
@Slf4j
public class Ch12Service7 {			
	/*필드주입*/ 	
	@Autowired //@Resource
	private Ch12Dao1 dao1; 
	
	/*생성자 주입*/ 
	private Ch12Dao2 dao2; 
	@Autowired //@Resource
	public Ch12Service7(Ch12Dao2 dao2) {
		this.dao2 = dao2;
		log.info("실행");
	}

	/*setter 주입*/
	private Ch12Dao3 dao3; 
	@Autowired //@Resource
	public void setCh12Service3(Ch12Dao3 dao3) {
		this.dao3 = dao3;
		log.info("실행");
	}
	
}
