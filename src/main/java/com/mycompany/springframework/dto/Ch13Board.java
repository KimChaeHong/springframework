package com.mycompany.springframework.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Ch13Board {
	private int bno;	//게시물 번호는 insert한 후 정해진다. 시퀀스 후에 부여.
	private String btitle;
	private String bcontent;
	private Date bdate;
	private String mid;
	private int bhitcount; 
	private String battachoname; 
	private String battachsname; //실제 서버 파일 스토리지에 저장 된 파일 이름
	private String battachtype; 
	private byte[]  battachdata; //데이터 베이스 자체에 저장하고 싶을 때
}
