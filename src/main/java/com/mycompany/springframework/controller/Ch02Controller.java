package com.mycompany.springframework.controller;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.springframework.dto.Ch02LoginResult;
import com.mycompany.springframework.interceptor.LoginCheck;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/ch02") //경로에 ch02가 들어가면 Ch02Controller가 처리하게 한다는 뜻, get post 다 지원한다.
@Slf4j //실행 되는지 확인 하기 위한 로그를 찍는다는 뜻
public class Ch02Controller {
	//예전에 개발 된 것들은 이 방법을 씀
	private static final Logger logger = LoggerFactory.getLogger(Ch02Controller.class);
	
	@GetMapping("/getMethod")
	public String getMethod() { //getMethod()가 실행 되게 하기 위해 RequestMapping /ch02/getMethod
		log.info("GET 실행");
		return "ch02/getMethod"; //veiw jsp 이름
	}
	
	//GET 방식만하고 POST 허용 안하겠다는 방법 두가지
	//1 번 방법
	//@RequestMapping(value="/getAtag", method=RequestMethod.GET) 
	
	//2번 방법
	@GetMapping("/getAtag")
	public String getAtag(String bno, String bkind) {
		log.info("getAtag 실행");
		log.info("bno :" + bno);
		log.info("bkind :" + bkind);
		return "ch02/getMethod";//뷰이름
	}
	
	@GetMapping("/getFormTag")
	public String getFormTag(String bno, String bkind) {
		log.info("getAtag 실행");
		log.info("bno :" + bno);
		log.info("bkind :" + bkind);
		return "redirect:/"; //홈으로 리다이렉트시킨다.
	}
	
	@GetMapping("/getLocationHref")
	public String getLocationHref(String bno, String bkind) {
		log.info("getLocationHref 실행");
		log.info("bno :" + bno);
		log.info("bkind :" + bkind);
		return "ch02/getMethod";
	}
	
	@GetMapping("/getAjax1")
	public String getAjax1(String bno, String bkind) {
		log.info("getAjax1 실행");
		log.info("bno :" + bno);
		log.info("bkind :" + bkind);
		return "ch02/ajaxFragmentHtml";
	}
	
	@GetMapping("/getAjax2")
	public String getAjax2(String bno, String bkind) {
		log.info("getAjax2 실행");
		log.info("bno :" + bno);
		log.info("bkind :" + bkind);
		return "ch02/ajaxJSON";
	}
	
	@GetMapping("/postMethod") //경로가 포스트방식으로 요청을해야 에러가 안난다.
	public String postMethod() { //postMethod()가 실행 되게 하기 위해 RequestMapping /ch02/getMethod
		log.info("POST 실행");
		return "ch02/postMethod";
	}
	
	@PostMapping("/postFormTag")
	public String postFormTag(String bno, String bkind) {
		log.info("postFormTag 실행");
		log.info("bno :" + bno);
		log.info("bkind :" + bkind);
		return "redirect:/";
	}
	
	@PostMapping("/postAjax1")
	public String postAjax1(String bno, String bkind) {
		log.info("postAjax1 실행");
		log.info("bno :" + bno);
		log.info("bkind :" + bkind);
		return "ch02/ajaxFragmentHtml";
	}
	
	@PostMapping("/postAjax2")
	public String postAjax2(String bno, String bkind) {
		log.info("postAjax2 실행");
		log.info("bno :" + bno);
		log.info("bkind :" + bkind);
		return "ch02/ajaxJSON";
	}

	@GetMapping("/returnModelAndView")
	public ModelAndView returnModelAndView() {
		log.info("returnModelAndView 실행");
		ModelAndView mav = new ModelAndView();
		mav.addObject("bno", 15);
		mav.addObject("bkind", "notice");
		mav.addObject("mid", "user1");
		mav.addObject("memail", "user1@mycompany.com");
		mav.setViewName("ch02/returnModelAndView");
		return mav;
	}
	
	@GetMapping("/returnVoid")
	public void returnVoid(HttpServletResponse response) throws Exception{
		//JSONObject jsonObject = null; //json 객체를 만들기 위해 선언하고 의존성을 추가해주었다.
		//{} : 객체	JSONObject jsonObject = new JSONObject();
		//[] : 배열	JSONArray JSONaRRAY = NEW JSONArray();
		
		//{"result": "success","mid":"user1"}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		jsonObject.put("mid", "user1");
		String json = jsonObject.toString();
		log.info("returnVoid 실행");
		//응답객체생성
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.println(json);
		pw.flush();
		pw.close();
	}
	
	@GetMapping(value="/returnObject", produces="application/json; charset=UTF-8")
	@ResponseBody //리턴 타입이 그냥 객체타입일 경우 : 객체가 json으로 변환 되고 응답body로 들어간다.
	public Ch02LoginResult returnObject() { //메서드가 실행 되면 produces 속성 값으로 실행이 된다.
		log.info("returnObject 실행");
		
		Ch02LoginResult obj = new Ch02LoginResult();
		obj.setResult("success");
		obj.setMid("user1");
		
		return obj;	
	}

	//filter
	@LoginCheck
	@GetMapping("/mypage")
	public String mypage() {
		log.info("실행");
		return "ch02/mypage";//views 이름
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		log.info("loginForm 실행");
		return "ch02/loginForm";
	}
	
	@PostMapping("/login")
	public String login(String mid, String mpassword, HttpSession session) {
		log.info("login 실행");
		log.info("mid : " + mid);
		log.info("mpassword : " + mpassword);
		//세션에 로그인 정보를 저장 (나중에 이어서 배울거임.)
		session.setAttribute("login", mid); //세션이 이 키가 있다면 로그인이 된 것이다. map 타입. // 이 검사를 filter와 interceptor 에서 가능
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		log.info("logoutForm 실행");
		session.removeAttribute("login");
		return "redirect:/ch02/loginForm";
	}
	
}
