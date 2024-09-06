package com.mycompany.springframework.aspect;


import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class Ch14Aspect4AfterReturning {
	//핵심 로직이 예외 없이 실행해야만 공통 기능을 실행 
	@AfterThrowing(
			pointcut="execution(public * com.mycompany.springframework.controller.Ch14Controller.afterThrowing*(..))",
			throwing="e")
	public void method(String returnValue) {
		//메소드 호출 후에 실행하는 공통 코드)
		log.info("실행");
		log.info("리턴값 :" + returnValue); //정상이면 리턴값이없다
	}
}
