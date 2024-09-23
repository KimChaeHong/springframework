package com.mycompany.springframework.controller;

import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.springframework.dto.Ch13Board;
import com.mycompany.springframework.dto.Ch13Member;
import com.mycompany.springframework.dto.Ch13Pager;
import com.mycompany.springframework.dto.Ch13UpdateBoardForm;
import com.mycompany.springframework.dto.Ch13WriteBoardForm;
import com.mycompany.springframework.service.Ch13BoardService;
import com.mycompany.springframework.service.Ch13MemberService;
import com.mycompany.springframework.service.Ch13MemberService.JoinResult;
import com.mycompany.springframework.service.Ch13MemberService.LoginResult;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("ch13")
public class Ch13Controller {
	@Autowired
	private Ch13BoardService boardService;
	
	@Resource
	private Ch13MemberService memberService;
	
	//@LoginCheck
	@Secured("ROLE_ADMIN")
	@GetMapping("/writeBoardForm")
	public String writeBoardForm(Model model) {
		model.addAttribute("chNum","ch13");
		
		return "ch13/writeBoardForm";
	}
	
	/*파일 시스템에 저장하기*/
	/*@PostMapping("/writeBoard")
	public String writeBoard(Ch13WriteBoardForm form) throws Exception {
		Ch13Board board = new Ch13Board();
		board.setBtitle(form.getBtitle());
		board.setBcontent(form.getBcontent());
		board.setMid("user");
		MultipartFile battach= form.getBattach();
		
		if(!battach.isEmpty()) {
			board.setBattachoname(battach.getOriginalFilename());
			board.setBattachtype(battach.getContentType());
			String fileName = new Date().getTime() + "-" +battach.getOriginalFilename();
			board.setBattachsname(fileName);
			String saveDir = "C:/2024-oti/workspace-spring/uploadfiles";
			File file = new File(saveDir, fileName);
			battach.transferTo(file);
		}
		boardService.writeBoard(board);
		return "redirect:/";
	}*/
	
	@PostMapping("/writeBoard")
	public String writeBoard(Ch13WriteBoardForm form, HttpSession session) throws Exception {
		Ch13Board board = new Ch13Board();
		board.setBtitle(form.getBtitle());
		board.setBcontent(form.getBcontent());
		/*board.setMid("user");*/
		Ch13Member member = (Ch13Member) session.getAttribute("login");
		board.setMid(member.getMid());
		MultipartFile battach= form.getBattach();
		
		if(!battach.isEmpty()) {
			board.setBattachoname(battach.getOriginalFilename());
			board.setBattachtype(battach.getContentType());
			board.setBattachdata(battach.getBytes());//DB저장
		}
		boardService.writeBoard(board);
		return "redirect:/ch13/boardList";
	}
	
	//게시글 목록
	//@LoginCheck
	@Secured("ROLE_ADMIN")
	@GetMapping("/boardList")								/*┌페이지 넘버가 넘어오지 않았을 때 1이 되도록 하는 것*/ 
	public String boardList(Model model,@RequestParam(defaultValue = "1") int pageNo,
				HttpSession session) {
		model.addAttribute("chNum","ch13");
		
		int totalRows = boardService.getTotalRows();
		Ch13Pager pager = new Ch13Pager(10, 5, totalRows, pageNo);
		session.setAttribute("pager", pager);/*jsp 에서도 사용*/
		
		List<Ch13Board> list = 	boardService.getBoardList(pager);
		model.addAttribute("list",list);
		return "ch13/boardList";
	}
	
	//조회수
	@GetMapping("/detailBoardAddHitcount")
	public String detailBoardAddHitcount(int bno, Model model) {
		boardService.addHitcount(bno);
		return detailBoard(bno,model);
	}

	
	//게시글 보기
	@GetMapping("/detailBoard")
	public String detailBoard(int bno, Model model) {
		Ch13Board board = boardService.getBoard(bno);
		model.addAttribute("chNum","ch13");
		model.addAttribute("board", board);
		return "ch13/detailBoard";
	}
	
	//DB에 있는 첨부파일을 읽고 다운로드
	@GetMapping("/attachDownload")
	public void attachDownload(int bno, HttpServletResponse response) throws Exception {
		log.info("bno: " + bno);
		Ch13Board board = boardService.getBoardAttach(bno);
		
		//응답 헤더에 들어가는 Content-Type 파일 확장명을 보고 저장을 자동으로 해주기
		String contentType = board.getBattachtype();
		response.setContentType(contentType);
		
		//파일로 저장하기 위한 설정				
		String fileName = board.getBattachoname();
		String encodingfileName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\""+encodingfileName+"\"");
		
		//응답 본문에 파일 데이터를 출력(그냥 보기만 할 뿐.
		OutputStream out = response.getOutputStream();
		out.write(board.getBattachdata());
		out.flush();
		out.close();
	}
	
	@GetMapping("/updateBoardForm")
	public String updateBoardForm(int bno, Model model) {
		Ch13Board board = boardService.getBoard(bno);
		model.addAttribute("chNum","ch13");
		model.addAttribute("board", board);
		return "ch13/updateBoardForm";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(Ch13UpdateBoardForm form) throws Exception {
		Ch13Board board = new Ch13Board();
		board.setBno(form.getBno());
		board.setBtitle(form.getBtitle());
		board.setBcontent(form.getBcontent());
		MultipartFile mf = form.getBattach();
		if(!mf.isEmpty()){
			board.setBattachoname(mf.getOriginalFilename());
			board.setBattachtype(mf.getContentType());
			board.setBattachdata(mf.getBytes());
		}
		boardService.updateBoard(board);
		return "redirect:/ch13/detailBoard?bno="+form.getBno();
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(int bno, HttpSession session) {
		boardService.deleteBoard(bno);
	
		Ch13Pager pager= (Ch13Pager) session.getAttribute("pager");
		int pageNo = pager.getPageNo();
		return "redirect:/ch13/boardList?pageNo="+pageNo;
	}
	
	@GetMapping("/joinForm")
	public String joinForm(Model model) {
		model.addAttribute("chNo","ch13");
		return "ch13/joinForm";
	}
	
	@PostMapping("/join")
	public String join(Ch13Member member, Model model) { 
		member.setMenabled(true);
		log.info(member.toString());
		JoinResult joinResult = memberService.join(member);
		if (joinResult == JoinResult.FAIL_DUPLICATED_MID) {
			String errorMessage = "아이디가 존재합니다.";
			model.addAttribute("errorMessage",errorMessage);
			return "ch13/joinForm"; //dto를 받았을 경우 return 값으로 바로 쓸 수 있다.
		}else {//if(joinResult==joinResult.SUCCESS) 
			return "redirect:/";			
		}
	}
	
	@GetMapping("/loginForm")
	public String loginForm(Model model) {
		model.addAttribute("chNum", "ch13");
		return "ch13/loginForm";
	}
	
	@PostMapping("/login")
	public String login(Ch13Member member, Model model, HttpSession session) {
		LoginResult loginResult =  memberService.login(member);
		if (loginResult == LoginResult.FAIL_MID) {
			model.addAttribute("chNum", "ch13");
			model.addAttribute("errorMid", "아이디가 존재하지 않습니다.");
			return "ch13/loginForm";
		} else if(loginResult == LoginResult.FAIL_MPASSSWORD) {
			model.addAttribute("chNum", "ch13");
			model.addAttribute("errorMpassword", "비밀번호가 존재하지 않습니다.");
			return "ch13/loginForm";
		} else if (loginResult == LoginResult.FAIL_ENABLED) {
			model.addAttribute("chNum", "ch13");
			return "redirect:/";

		} else { //loginResult == loginResult.SUCCESS 인 경우	
			session.setAttribute("login", member);
			return "redirect:/";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("login");
		return "redirect:/ch13/loginForm";
	}
	
}
