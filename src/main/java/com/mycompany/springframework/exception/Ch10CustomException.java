package com.mycompany.springframework.exception;

public class Ch10CustomException extends Exception{
	//어떤 조건이 맞을 때 쓰는 것은 throw
	public Ch10CustomException() {
		//기본 생성자
	}
	
	public Ch10CustomException(String message) {
		//예외메세지
		super(message);
	}
	
}
