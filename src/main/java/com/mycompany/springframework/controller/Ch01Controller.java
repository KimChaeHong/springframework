package com.mycompany.springframework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller //자동적으로 객체가 만들어진다.
@Slf4j
public class Ch01Controller {
	@RequestMapping("/ch01/content")
	public String content(Model model) {
		log.info("실행");
		model.addAttribute("chNum","ch01");
		return "ch01/content";
	}

}
