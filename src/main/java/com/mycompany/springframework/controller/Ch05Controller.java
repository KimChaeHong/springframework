package com.mycompany.springframework.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch05")
public class Ch05Controller {

	@GetMapping("/header")									//헤더 값을 얻는 방법1
	public String header(@RequestHeader("User-Agent") String userAgent, Model model, HttpServletRequest request) {
		log.info("userAgent: " + userAgent);
		
		//헤더 값을 얻는 방법2
		//userAgent = request.getHeader("User-Agent");
		
		String browser = null;
		if (userAgent.contains("Edg")) {
			browser = "Edg";
		} else if (userAgent.contains("Chrome")) {
			browser = "Chrome";
		}
		model.addAttribute("browser", browser);
		
		String clientIP = request.getRemoteAddr();
		log.info("clientIP: "+ clientIP);
		model.addAttribute("clientIP", clientIP);

		model.addAttribute("chNum", "ch05");
		return "ch05/header";
	}

	@GetMapping("/createCookie")
	public String createCookie(HttpServletResponse response) {
		//쿠키생성
		Cookie cookie1 = new Cookie("mid","user1");
		cookie1.setMaxAge(60);//.setMaxAge() : 저장기간, 초단위로 저장할 시간을 주면 브라우저가 저장하는 파일시스템에 1분동안저장
		//cookie1.setDomain(".samsung.com");//*.samsung.com 이라면 쿠키전송
		//cookie1.setSecure(true);//https에서만 쿠키를 전송, 브라우저에서 수정을 할 수 없다.
		//cookie1.setHttpOnly(true);//http에서 cookie 수정/읽기 못함, https에서는 가능, 브라우저에 자바스크립트로 쿠키접근 허용 안함
		
		Cookie cookie2 = new Cookie("memail","user1@mycompany.com");
		cookie2.setMaxAge(60);
		cookie2.setSecure(true);
		cookie2.setHttpOnly(true);
		
		//쿠키를 응답 헤더에 추가해서 브라우저로 보냄
		response.addCookie(cookie1);
		response.addCookie(cookie2);

		return "redirect:/";
	}

	@GetMapping("/readCookie")
	public String readCookie(@CookieValue("mid") String mid, @CookieValue("memail") String memail, Model model) {
		log.info("mid : "+ mid);
		log.info("memail : "+ memail);
		
		model.addAttribute("mid", mid);
		model.addAttribute("memail",memail);
		
		
		model.addAttribute("chNum", "ch05");
		return "ch05/readCookie";
	}

}
