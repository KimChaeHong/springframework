package com.mycompany.springframework.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

/*@Component
@ControllerAdvice*/
@Slf4j
public class Ch10ExceptionHandler {
	@ExceptionHandler(NullPointerException.class)
	public String handleNullPointerException() {
		return "ch10/nullPointerException";
	}
	
	@ExceptionHandler(Ch10CustomException.class)
	public String handleCh10CustomException() {
		return "ch10/customException";
	}
	
	@ExceptionHandler(Exception.class)
	public String handleException() {
		return "ch10/exception";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public String handleNoHandlerFoundException() {
		return "ch10/404";
	}
	
	@ExceptionHandler(Ch15AccountNotExistException.class)
	public String handleCh15AccountNotExistException(
			Ch15AccountNotExistException e, Model model) {
		model.addAttribute("errorMessage", e.getMessage());
		return "ch15/accountNotExistException";
	}
	
}
