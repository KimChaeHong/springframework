package com.mycompany.springframework.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springframework.dto.Ch06Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch06")
public class Ch06Controller {
	
	@GetMapping("/forward")
	public String forward(Model model, HttpServletRequest request) {
		//DAO : 나중에 서비스 객체로 디비에서 멤버를 가져오는거 배운다.
		Ch06Member member = new Ch06Member();
		member.setMid("user1");
		member.setMname("사용자1");
		member.setMemail("user1@mycompany.com");
		
		//방법 1
		model.addAttribute("member1",member);
		//방법 2
		request.setAttribute("member2", member);
		
		return "ch06/forward";
		
	}
	
	@GetMapping("/redirect")
	public String redirect(Model model, HttpServletRequest request) {

		Ch06Member member = new Ch06Member();
		member.setMid("user1");
		member.setMname("사용자1");
		member.setMemail("user1@mycompany.com");
		
		//방법 1
		model.addAttribute("member1",member);
		//방법 2
		request.setAttribute("member2", member);

		return "redirect:/ch06/sessionData"; //리다이렉트 되어서 /sessionData로 감
		
	}
	
	@GetMapping("/sessionData")
	public String sessionData() {
		
		return "ch06/sessionData";
	}
	

}
