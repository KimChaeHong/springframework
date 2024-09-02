package com.mycompany.springframework.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch12Service2 {			//┌정적메소드
	public static Ch12Service2 getInstance() { //static이 있으면 객체의 객체 없이도 실행 가능	
		log.info("실행");
		return new Ch12Service2();
	}
							//┌인스턴스메소드
	public Ch12Service3 getCh12Service3() {
		return new Ch12Service3();
	}
	
	
}
