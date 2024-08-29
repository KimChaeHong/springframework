package com.mycompany.springframework.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springframework.dto.Ch06Member;
import com.mycompany.springframework.dto.Ch06Cart;
import com.mycompany.springframework.dto.Ch06Item;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch06")
public class Ch06Controller {
	
	@GetMapping("/forward")
	public String forward(Model model, HttpServletRequest request) {
		//DAO : 나중에 서비스 객체로 디비에서 멤버를 가져오는거 배운다.
		Ch06Member member = new Ch06Member();
		member.setMid("user1");
		member.setMname("사용자1");
		member.setMemail("user1@mycompany.com");
		
		//방법 1
		model.addAttribute("member1",member);
		//방법 2
		request.setAttribute("member2", member);
		
		model.addAttribute("chNum", "ch06");
		
		return "ch06/forward";
		
	}
	
	@GetMapping("/redirect")
	public String redirect(HttpServletRequest request) {

		Ch06Member member = new Ch06Member();
		member.setMid("user1");
		member.setMname("사용자1");
		member.setMemail("user1@mycompany.com");
	
		//세션에 저장
		request.setAttribute("member2", member);

		return "redirect:/ch06/sessionData"; //리다이렉트 되어서 /sessionData로 감
		
	}
	
	@GetMapping("/sessionData")
	public String sessionData(HttpSession session, Model model) {
		Ch06Member member = (Ch06Member) session.getAttribute("member"); //객체 가져오고 형변환
		
		//세션에서 찾아오기(읽기)
		log.info(member.getMid());
		log.info(member.getMname());
		log.info(member.getMemail());
		
		model.addAttribute("chNum", "ch06");
		return "ch06/sessionData"; //세션에 저장하면 jsp에 사용가능하다.
	}
	
	@GetMapping("/cartview")
	public String cartview(HttpSession session, Model model) {//el이 찾는  객체
		Ch06Cart cart = (Ch06Cart)session.getAttribute("cart");//세션에서 "cart"로 된 것을 찾아와라.
		if (cart == null) {
			session.setAttribute("cart", new Ch06Cart()); //없다면 저장해줘라.
			cart = (Ch06Cart) session.getAttribute("cart");
		}
		
		model.addAttribute("chNum", "ch06");
		return "ch06/cartview";
	}
	
	@GetMapping("/productlist")
	public String productlist(Model model) {
		List<Ch06Item> productList = new ArrayList<>();
		for(int i= 0 ; i<=5 ; i++) {
			Ch06Item item = new Ch06Item();
			
			item.setPno("p"+i);
			item.setPname("상품"+i);
			productList.add(item);	
		}
		model.addAttribute("productList", productList);
		
		model.addAttribute("chNum", "ch06");
		return "ch06/productlist";
	}
	
	@GetMapping("/cartadd")
	public String cartadd(Ch06Item item, HttpSession session) {
		//세션에서 Ch06Cart 가져오기
		Ch06Cart cart = (Ch06Cart) session.getAttribute("cart");
		//만약 Ch06Cart 객체가 없다면 새로 생성해서 세션에 저장
		if(cart == null) {
			cart = new Ch06Cart(); //처음 만들었을 때 빈카트고 세션에 저장하자
			session.setAttribute("cart", cart);
		}
		//상품 아이템을 Ch06Cart에 추가
		cart.addItem(item);
		return "redirect:/ch06/cartview";
	}
	
	@GetMapping("/deleteitem")
	public String deleteitem(String pno, HttpSession session) {
		//세션에서 Ch06Cart 가져오기
		Ch06Cart cart = (Ch06Cart) session.getAttribute("cart");
		
		//시도1.
		//삭제할 pid를 찾아서 장바구니 제거하기 
		//지우는 도중에 배열이나 리스트 내 갯수가 달라져서 for문이 안돌아가진다
		/*for (Ch06Item item : cart.getContents()) {
			cart.removeitem(item);
		}*/
		//시도2.
		//session.removeAttribute("cart");
		
		//시도3.
		Iterator<Ch06Item> iterator =  cart.getContents().iterator();
		while(iterator.hasNext()) {
			Ch06Item item = iterator.next();
			if(item.getPno().equals(pno)) {
				iterator.remove();
			}
		}
		return "redirect:/ch06/cartview";
	}
	
	

}
