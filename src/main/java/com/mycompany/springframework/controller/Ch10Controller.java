package com.mycompany.springframework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springframework.exception.Ch10CustomException;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch10")
public class Ch10Controller {
	@GetMapping("/handlingException1")
	public String handlingException1(String data, Model model) {
		try {
			if (data.equals("java")) {
				//만약 이 데이터라는 파라미터가 클라이언트에서 넘어오지않으면 NULL이 온다.
				//data를 브라우저가 제공하지 않을 경우, NullPointerException
			}
		}catch (NullPointerException e) {
			model.addAttribute("chNum","ch10");
			return "ch10/nullPointerException";
		}

		return "redirect:/";
	}
	
	@GetMapping("/handlingException2")
	public String handlingException2(String data, Model model) {
			if (data.equals("java")) {
				//만약 이 데이터라는 파라미터가 클라이언트에서 넘어오지않으면 NULL이 온다.
				//data를 브라우저가 제공하지 않을 경우, NullPointerException
			}

		return "redirect:/";
	}
	
	@GetMapping("/handlingException3")
	public String handlingException3() throws Ch10CustomException{
		if(true) {
		
			throw new Ch10CustomException("길주가 질문해서 예외 발생함");
		}
		return "redirect:/";
	}
	
	@GetMapping("/handlingException4")
	public String handlingException4(String data){
		int value = Integer.parseInt(data); //runtimeException이라 throws안 붙여도된다.
		return "redirect:/";
	}
	
}
