package com.mycompany.springframework.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springframework.dto.Ch13Account;
import com.mycompany.springframework.service.Ch15AccountService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch15")
public class Ch15Controller {
	
	@Autowired
	private Ch15AccountService accountService;
	
	//페이지로 이동
	@GetMapping("/accountList")
	public String accountList(Model model) {
		model.addAttribute("chNum","ch15");
		
		List<Ch13Account> list = accountService.getAccountList();
		model.addAttribute("list", list) ;//jsp에서 ${list}로 사용할 수 있도록 객체 저장
		
		return "ch15/accountList";
	}
	
	@PostMapping("/transfer")
	public String transfer(int fromAno, int toAno, int amount) {
		accountService.transfer(fromAno, toAno, amount);
		return "redirect:/ch15/accountList";
	}
	
	
	
	
	
	
}
