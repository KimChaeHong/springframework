package com.mycompany.springframework.service;

import com.mycompany.springframework.dao.Ch12Dao1;
import com.mycompany.springframework.dao.Ch12Dao2;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
//@AllArgsConstructor //모든 필드를 매개변수로 하는 생성자가 만들어주는 것
@NoArgsConstructor	//매개변수가 없는 기본 생성자
public class Ch12Service6 {			
	private Ch12Dao1 dao1;
	private Ch12Dao2 dao2;
	
	 //이것을 작성 할 필요는 없고 @AllArgsConstructor 넣어주면 된다.
	public Ch12Service6(Ch12Dao1 dao1, Ch12Dao2 dao2) {
		super();
		this.dao1 = dao1;
		this.dao2 = dao2;
		log.info("실행");
	}
	


}
