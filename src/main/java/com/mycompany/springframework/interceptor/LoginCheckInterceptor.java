package com.mycompany.springframework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("pre실행");

		try {
			// 1. @LoginCheck 어노테이션이 붙어있는지 검사
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			// handlerMethod : 검사할 메소드, mypage()
			LoginCheck loginCheck = handlerMethod.getMethodAnnotation(LoginCheck.class);
			if (loginCheck == null) {
				// @LoginCheck 어노테이션이 붙어 있지않는 경우
				log.info("@LoginCheck가 붙어 있지 않음");

			} else {
				// @LoginCheck 어노테이션이 붙어 있는 경우
				log.info("@LoginCheck가 붙어 있음");

				// 2. 로그인 여부를 확인
				HttpSession session = request.getSession();
				String login = (String) session.getAttribute("login");	//세션에 로그인 키가 있으면 id 저장, 없으면 null 저장
												//로그인으로 저장 된 값은 String이므로 타입 변환
				if (login == null) {
					//로그인을 하지 않음
					String contextPath = request.getServletContext().getContextPath();
					response.sendRedirect(contextPath + "/ch02/loginForm");
					return false;
				} else {
					//로그인을 했을 경우
					 
				}
			}
		} catch (Exception e) {

		}
		return true; // 컨트롤러의 메소드를 실행해라.
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("post실행");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("after실행");

	}
}
