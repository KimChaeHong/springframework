package com.mycompany.springframework.controller;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.springframework.dto.Ch03Dto;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch03")
public class Ch03Controller {
	//GET 방식 데이터 읽기
	//요청 파라미터명과 동일한 매개변수 선언
	@GetMapping("/receiveParamData")//localhost:8080/receiveParamData 요청이 들어오면 실행하라는 뜻
	public String receiveParamData(String param1, String param2, String param3, String param4, String param5,
			Model model) {
		log.info("param1 : " + param1);
		log.info("param2 : " + param2);
		log.info("param3 : " + param3);
		log.info("param4 : " + param4);
		log.info("param5 : " + param5);

		// JSP로 데이터 전달
		model.addAttribute("param1", param1);
		model.addAttribute("param2", param2);
		model.addAttribute("param3", param3);
		model.addAttribute("param4", param4);
		model.addAttribute("param5", param5);
		
		model.addAttribute("chNum","ch03");

		return "ch03/receiveParamData";
	}

	//POST 방식 데이터 읽기
	@GetMapping("/postMethodForm")
	public String postMethodForm(Model model) {
		
		model.addAttribute("chNum","ch03");

		return "ch03/postMethodForm";
	}

	@PostMapping("/receivePostMethodForm")
	public String receivePostMethodForm(String param1, int param2, double param3, boolean param4,
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date param5, Model model) {

		log.info("param1 : " + param1);
		log.info("param2 : " + param2);
		log.info("param3 : " + param3);
		log.info("param4 : " + param4);
		log.info("param5 : " + param5);

		// JSP로 데이터 전달
		model.addAttribute("param1", param1);
		model.addAttribute("param2", param2);
		model.addAttribute("param3", param3);
		model.addAttribute("param4", param4);
		model.addAttribute("param5", param5);
		
		model.addAttribute("chNum","ch03");


		return "ch03/receiveParamData";
	}

	//파라미터 생략 시 디폴트 값 설정
	@GetMapping("/defaultValue")
	public String defaultValue(String param1, // 브라우저가 값을 매기지 않았다면 기본값인 null 값으로 들어간다.
			@RequestParam(defaultValue = "0"/* , required=true */) int param2, // @RequestParam 을 써서
			@RequestParam(defaultValue = "0.0") double param3, // 자바가 null을 int로 못 바꿔주니 기본값이 마치 0인것처럼 해주자
			@RequestParam(defaultValue = "false") boolean param4, String param5, Model model) {
		log.info("param1 : " + param1);
		log.info("param2 : " + param2);
		log.info("param3 : " + param3);
		log.info("param4 : " + param4);
		log.info("param5 : " + param5);

		// JSP로 데이터 전달
		model.addAttribute("param1", param1);
		model.addAttribute("param2", param2);
		model.addAttribute("param3", param3);
		model.addAttribute("param4", param4);
		model.addAttribute("param5", param5);
		
		model.addAttribute("chNum","ch03");


		return "ch03/receiveParamData";
	}

	//파라미터명과 매개변수명이 다를 경우
	@GetMapping("/otherArgName")
	public String otherArgName(@RequestParam("param1") String arg1,
			@RequestParam(value = "param2", defaultValue = "0") int arg2,
			@RequestParam(value = "param3", defaultValue = "0.0") double arg3,
			@RequestParam(value = "param4", defaultValue = "false") boolean arg4, 
			String arg5, Model model) {
		log.info("param1 : " + arg1);
		log.info("param2 : " + arg2);
		log.info("param3 : " + arg3);
		log.info("param4 : " + arg4);
		log.info("param5 : " + arg5);

		// JSP로 데이터 전달
		model.addAttribute("param1", arg1);
		model.addAttribute("param2", arg2);
		model.addAttribute("param3", arg3);
		model.addAttribute("param4", arg4);
		model.addAttribute("param5", arg5);
		
		model.addAttribute("chNum","ch03");


		return "ch03/receiveParamData";
	}
	
	//DTO로 파라미터 값을 모두 받기
	@GetMapping("/commandObject")
	public String commandObject(Ch03Dto dto, Model model) {
		log.info("param1 : " + dto.getParam1());
		log.info("param2 : " + dto.getParam2());
		log.info("param3 : " + dto.getParam3());
		log.info("param4 : " + dto.isParam4());
		log.info("param5 : " + dto.getParam5());

		// JSP로 데이터 전달(CommendObject를 사용할 경우 자동으로 전달)
		// model.addAllAttribute("ch03Dto",dto); 첫번째가 소문자가 된다.
		
		
		model.addAttribute("chNum","ch03");


		return "ch03/receiveCommandObject";
	}

	//AJAX로 보낸 데이터를 DTO로 받기
	@GetMapping("/ajaxParam")
	public String ajaxParam(Model model) {
		
		model.addAttribute("chNum","ch03");

		return "ch03/ajaxParam";
	}

	@PostMapping("/requestAjax")
	public void requestAjax(Ch03Dto dto, HttpServletResponse response) throws Exception {
		log.info(dto.toString());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "OK");
		String json = jsonObject.toString();

		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();

		pw.println(json);
		pw.flush();
		pw.close();
	}

}
