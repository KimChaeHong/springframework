package com.mycompany.springframework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springframework.dto.Ch13Member;
import com.mycompany.springframework.security.Ch17UserDetails;
import com.mycompany.springframework.service.Ch13MemberService;
import com.mycompany.springframework.service.Ch13MemberService.JoinResult;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch17")
public class Ch17Controller {
	@Autowired
	private Ch13MemberService memberService;
	
	
	
	@RequestMapping("/loginForm")
	public String loginForm(Model model) {
		model.addAttribute("chNum","ch17");
		
		return "ch17/loginForm";
	}
	
	@GetMapping("/authorityCheck")
	public String authorityCheck(Model model) {
		model.addAttribute("chNum","ch17");
		return "ch17/authorityCheck";
	}
	
	@GetMapping("/userInfo")
	public String userInfo(Model model, Authentication authentication)  {
		model.addAttribute("chNum","ch17");
		
		//사용자의 아이디만 얻고 싶을 경우
		//방법1
		String mid = authentication.getName();
		//방법2
		//Ch17UserDetails userDetails = (Ch17UserDetails) authentication.getPrincipal();
		//String mid = userDetails.getUsername();
				
		model.addAttribute("mid",mid);
		
		
		//사용자의 모든 정보를 얻고 싶을 경우
		Ch17UserDetails userDetails = (Ch17UserDetails) authentication.getPrincipal();
		Ch13Member member = userDetails.getMember();
		model.addAttribute("member", member);
		return "ch17/userInfo";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/admin/page")
	public String adminPage(Model model) {
		log.info("실행");
		return "redirect:/ch17/authorityCheck";
	}
	
	@Secured({"ROLE_MANAGER"})
	@GetMapping("/manager/page")
	public String managerPage(Model model) {
		log.info("실행");
		return "redirect:/ch17/authorityCheck";
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/user/page")
	public String userPage(Model model) {
		log.info("실행");
		return "redirect:/ch17/authorityCheck";
	}
	
	@GetMapping("/error403")
	public String error403(Model model) {
		model.addAttribute("chNum","ch17");
		return "ch17/error403";
	}
	
	@GetMapping("/joinForm")
	public String joinForm(Model model) {
		model.addAttribute("chNo","ch17");
		return "ch17/joinForm";
	}
	
	@PostMapping("/join")
	public String join(Ch13Member member, Model model) { 
		//계정 활성화
		member.setMenabled(true);
		//비밀번호 암호화 - 스프링 시큐리티 의존 설정이 되어야 쓸 수 있다.
		PasswordEncoder passwordEncoder = 
				PasswordEncoderFactories.createDelegatingPasswordEncoder();
		member.setMpassword(passwordEncoder.encode(member.getMpassword()));
		
		log.info(member.toString());
		
		log.info(member.toString());
		JoinResult joinResult = memberService.join(member);
		if (joinResult == JoinResult.FAIL_DUPLICATED_MID) {
			String errorMessage = "아이디가 존재합니다.";
			model.addAttribute("errorMessage",errorMessage);
			return "ch17/joinForm"; 
		}else {
			return "redirect:/ch17/loginForm";			
		}
	}
	
	
}
