package com.mycompany.springframework.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.springframework.dto.Ch09FileUploadForm;
import com.mycompany.springframework.dto.Ch09MultiFileUploadForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch09")
public class Ch09Controller {
	@GetMapping("/fileUploadForm")
	public String fileUploadForm(Model model) {
		model.addAttribute("chNum","ch09");
		
		return "/ch09/fileUploadForm"; 
	}
	
	@PostMapping("singleFileUpload")//ajax응답은 json이거나 조각이어야한다.
	public String singleFileUpload(Ch09FileUploadForm form) throws Exception{
		
		log.info("title: "+form.getTitle());
		log.info("desc: "+form.getTitle());
		MultipartFile attach = form.getAttach();
		if (!attach.isEmpty()) {
			//파일 정보 읽기
			log.info("originalFilename : "+attach.getOriginalFilename()); //브라우저가 선택한 파일이름
			log.info("contenType : "+attach.getContentType());	//그 파일의 종류가 무엇이냐? 
			log.info("size : "+attach.getSize());// 그 파일의 사이즈가 얼마냐? 
			
			//파일을 파일 스토리지에 저장
			String saveDir = "C:\\2024-oti\\workspace-spring\\uploadfiles"; //저장 할 경로
			String saveFileName = new Date().getTime() + "-" + attach.getOriginalFilename(); //중복이 되지않는 파일 이름으로 저장
			File file = new File(saveDir, saveFileName);
			
			attach.transferTo(file);
		} // 나중에 db에 저장 해야한다.
		return "redirect:/ch09/downloadFileList";
	}
	
	@PostMapping("multiFileUpload")
	public String multiFileUpload(Ch09MultiFileUploadForm form) throws Exception{
		
		log.info("title: "+form.getTitle());
		log.info("desc: "+form.getTitle());
		for(MultipartFile mf : form.getAttach()) {
			if (!mf.isEmpty()) {
				//파일 정보 읽기
				log.info("originalFilename : "+mf.getOriginalFilename()); //브라우저가 선택한 파일이름
				log.info("contenType : "+mf.getContentType());	//그 파일의 종류가 무엇이냐? 
				log.info("size : "+mf.getSize());// 그 파일의 사이즈가 얼마냐? 
				log.info("");
				//파일을 파일 스토리지에 저장
				String saveDir = "C:\\2024-oti\\workspace-spring\\uploadfiles"; //저장 할 경로
				String saveFileName = new Date().getTime() + "-" + mf.getOriginalFilename(); //중복이 되지않는 파일 이름으로 저장
				File file = new File(saveDir, saveFileName);
				
				mf.transferTo(file);
			} // 나중에 db에 저장 해야한다.
			
		}
		
		return "redirect:/ch09/downloadFileList";
	}
	
	@GetMapping("/downloadFileList")
	public String downloadFileList(Model model) {
		model.addAttribute("chNum","ch09");
		
		String saveDir = "C:/2024-oti/workspace-spring/uploadfiles";
		File file = new File(saveDir);
		String[] fileNames = file.list();
		model.addAttribute("fileNames", fileNames);
		
		
		return "ch09/downloadFileList";
	}
	
	@GetMapping("/downloadFile")//void -> 응답 직접 만들기
	public void downloadFile(String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception{
		//응답 헤더에 들어가는 Content-Type 파일 확장명을 보고 저장을 자동으로 해주기
		String contentType = request.getServletContext().getMimeType(fileName);
		response.setContentType(contentType);
		
		//파일로 저장하기 위한 설정				┌한글로 변환  ┌한글은 3바이트
		String encodingfileName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");//파일이름이 한글일 때 변환해줘야 제대로 된다. 
		response.setHeader("Content-Disposition", "attachment; filename=\""+encodingfileName+"\"");
		//a태그는 get방식이라 영어,숫자 만 가능하고 한글은 안된다.
		
		//응답 본문에 파일 데이터를 출력(그냥 보기만 할 뿐.)
		String saveDir = "C:\\2024-oti\\workspace-spring\\uploadfiles";
		Path path = Paths.get(saveDir + "/" + fileName);
		OutputStream out = response.getOutputStream();
		Files.copy(path, out); //Input 스트림에서 읽고 바로 복사해서 출력 스트림으로 전달한다.
					//└어떤│파일로부터 연결 할것이냐 path 객체
					//	   └어디로 출력할 것이냐 out 객체
		out.flush();
		out.close();
	}
	
	
	@PostMapping("/uploadFileFromAjax")
	public void uploadFileFromAjax(Ch09FileUploadForm form, HttpServletResponse response) throws Exception {
		//요청 데이터 받기
		log.info("title: "+form.getTitle());
		log.info("desc: "+form.getTitle());
		MultipartFile attach = form.getAttach();
		//파일이 포함되어 있는지 여부 조사
		if (!attach.isEmpty()) {
			//파일 정보 읽기
			log.info("originalFilename : "+attach.getOriginalFilename()); 
			log.info("contenType : "+attach.getContentType());
			log.info("size : "+attach.getSize());
			
			//파일을 파일 스토리지에 저장
			String saveDir = "C:\\2024-oti\\workspace-spring\\uploadfiles";
			String saveFileName = new Date().getTime() + "-" + attach.getOriginalFilename();
			File file = new File(saveDir, saveFileName);
			attach.transferTo(file);
		}
		//응답 생성
		//{"result":"ok"}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "ok");
		String json = jsonObject.toString();
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.println(json);
		pw.flush();
		pw.close();
		
	}

}
