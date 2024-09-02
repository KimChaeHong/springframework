package com.mycompany.springframework.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch12Service1 {
	public Ch12Service1() { 	//생성자가 있는데 매개변수가 없으면 객체가 만들어지지 않는다. 
		log.info("실행");
	}
}
