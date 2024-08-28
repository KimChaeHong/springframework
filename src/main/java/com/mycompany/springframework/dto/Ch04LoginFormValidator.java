package com.mycompany.springframework.dto;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch04LoginFormValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		log.info("support 실행");
		// 객체 clazz값이 Ch04LoginFormValidator로 잘 들어왔는지 result로 선언하고 true이면 리턴값으로 보내주기
		boolean result = Ch04LoginForm.class.isAssignableFrom(clazz);
		return result;
	}

	@Override
	public void validate(Object target, Errors errors) { // Object tartget : dto 객체
		log.info("validate 실행");
		Ch04LoginForm loginForm = (Ch04LoginForm) target; // dto로 타입 변환

		// mid 검사
		String mid = loginForm.getMid();
		if (mid == null || mid.equals("")) {
			errors.rejectValue("mid", "errors.mid.required"); // 에러코드를 message/properties에서 가져온다.
		} else if (mid.length() < 6 || mid.length() > 12) {
			errors.rejectValue("mid", "errors.mid.length", new Object[] { "6", "12" }, null);// 알수 없는 에러가 있을 수 있기에 적어주면
																								// 된다. 근데 난 적음.

		}

		// mpassword 검사
		String mpassword = loginForm.getMpassword();
		String pattern = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}";
		if (mpassword == null || mpassword.equals("")) {
			errors.rejectValue("mpassword", "errors.mpassword.required");
		} else if (mpassword.length() < 8 || mpassword.length() > 15) {
			errors.rejectValue("mpassword", "errors.mpassword.length", new Object[] { "8", "15" }, null);
		} else if (!Pattern.matches(pattern, mpassword)) {// 이 검사 값을 만족하지 않다면 false, 만족한다면 true
			errors.rejectValue("mpassword", "errors.mpassword.wrongchar");

		}

	}

}
