package com.mycompany.springframework.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.springframework.aspect.LogincheckAOP;
import com.mycompany.springframework.aspect.RuntimeCheck;
import com.mycompany.springframework.dto.Ch13Board;
import com.mycompany.springframework.dto.Ch13Pager;
import com.mycompany.springframework.interceptor.LoginCheck;
import com.mycompany.springframework.service.Ch13BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch14")
public class Ch14Controller {
	/*
	 * public String beforeMethod1() {} public String beforeMethod2(String arg) {}
	 * public void beforeMethod3(String arg, Model model);
	 */

	@RequestMapping("/before")
	public String before(Model model) {
		log.info("실행");
		model.addAttribute("chNum", "ch14");
		return "redirect:/";
	}
	
	@RequestMapping("/after")
	public String after(Model model) {
		log.info("실행");
		model.addAttribute("chNum", "ch14");
		return "redirect:/";
	}
	
	@RequestMapping("/afterReturning")
	public String afterReturning(Model model) {
		log.info("실행");
		model.addAttribute("chNum", "ch14");
		return "redirect:/";
	}
	
	@RequestMapping("/afterThrowing")
	public String afterThrowing(Model model) {
		log.info("실행");
		boolean result = true;
		if(result) {
			throw new RuntimeException("예외가 발생됨");
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/around")
	public String around() {
		log.info("실행");
		return "redirect:/";
	}
	
	@Autowired
	private Ch13BoardService boardService;
	
	@RuntimeCheck
	@GetMapping("/boardList")								
	public String boardList(Model model,@RequestParam(defaultValue = "1") int pageNo,
				HttpSession session) {
		model.addAttribute("chNum","ch14");
		
		int totalRows = boardService.getTotalRows();
		Ch13Pager pager = new Ch13Pager(10, 5, totalRows, pageNo);
		session.setAttribute("pager", pager);
		
		List<Ch13Board> list = 	boardService.getBoardList(pager);
		model.addAttribute("list",list);
		return "redirect:/";
	}
	
	@LogincheckAOP
	@GetMapping("/writeBoardForm")
	public String writeBoardForm(Model model) {
		model.addAttribute("chNum","ch14");
		
		return "redirect:/ch13/writeBoardForm";
	}
	
	
	
}
