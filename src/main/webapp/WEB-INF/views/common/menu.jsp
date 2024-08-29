<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
			
<div class="accordion" id="accordionMenu">
	<div class="accordion-item">
		<h2 class="accordion-header">																					<!-- chNum의 값이 ch01이면 true 아니면 false -->
			<button class="accordion-button ${chNum=='ch01'?'':'collapsed'}" type="button" data-bs-toggle="collapse" data-bs-target="#ch01" aria-expanded="${chNum=='ch01'?true:false}" aria-controls="ch01">
				Ch01. Development Environment (Create MVC Project)
			</button>
	    </h2>
	<div id="ch01" class="accordion-collapse collapse ${chNum=='ch01'?'show':''}" data-bs-parent="#accordionMenu">
		<div class="accordion-body">
			<ul><li><a href="${pageContext.request.contextPath}/ch01/content">공통 레이아웃 작성</a></li></ul>
		</div>
		</div>
	</div>
	<div class="accordion-item">
		<h2 class="accordion-header">
			<button class="accordion-button ${chNum=='ch02'?'':'collapsed'}" type="button" data-bs-toggle="collapse" data-bs-target="#ch02" aria-expanded="${chNum=='ch02'?true:false}" aria-controls="ch02">
				Ch02. Controller (Request Mapping)
			</button>
	    </h2>
	<div id="ch02" class="accordion-collapse collapse ${chNum=='ch02'?'show':''}" data-bs-parent="#accordionMenu">
		<div class="accordion-body">
			<ul>
				<li><a href="${pageContext.request.contextPath}/ch02/getMethod">GET 방식</a></li>
				<li><a href="${pageContext.request.contextPath}/ch02/postMethod">POST 방식</a></li>
				<li><a href="${pageContext.request.contextPath}/ch02/returnModelAndView">ModelAndView 리턴</a></li>
				<li><a href="${pageContext.request.contextPath}/ch02/returnVoid">void 리턴</a></li>
				<li><a href="${pageContext.request.contextPath}/ch02/returnObject">Object 리턴</a></li>
				<li><a href="${pageContext.request.contextPath}/ch02/mypage">MyPage(로그인 체크 여부 확인)</a></li>
				<li><a href="${pageContext.request.contextPath}/ch02/loginForm">로그인/로그아웃</a></li>
			</ul>
		</div>
	</div>
	</div>
	<div class="accordion-item">
		<h2 class="accordion-header">
			<button class="accordion-button ${chNum=='ch03'?'':'collapsed'}" type="button" data-bs-toggle="collapse" data-bs-target="#ch03" aria-expanded="${chNum=='ch03'}" aria-controls="ch03">
				Ch03. Controller (Request Parameter)
			</button>
	    </h2>
	<div id="ch03" class="accordion-collapse collapse ${chNum=='ch03'?'show':''}" data-bs-parent="#accordionMenu">
		<div class="accordion-body">
			<ul>
				<li><a href="${pageContext.request.contextPath}/ch03/receiveParamData?param1=문자열&param2=5&param3=3.14&param4=true&param5=2024-08-27">GET 방식 데이터 얻기</a></li>
				<li><a href="${pageContext.request.contextPath}/ch03/postMethodForm">POST 방식 데이터 얻기</a></li>
				<li><a href="${pageContext.request.contextPath}/ch03/defaultValue?param1=문자열&param2=5&param3=3.14&param4=true&param5=2024-08-27">파라미터 생략 시 디폴트 값 설정</a></li>
				<li><a href="${pageContext.request.contextPath}/ch03/otherArgName?param1=문자열&param2=5&param3=3.14&param4=true&param5=2024-08-27">파라미터이름과 매개변수 이름이 다를 경우</a></li>
				<li><a href="${pageContext.request.contextPath}/ch03/commandObject?param1=문자열&param5=2024-08-27">DTO로 파라미터 값을 모두 받기</a></li>
				<li><a href="${pageContext.request.contextPath}/ch03/ajaxParam">AJAX로 보낸 데이터를 DTO로 받기</a></li>
			</ul>
		</div>
	</div>
	</div>
	<div class="accordion-item">
		<h2 class="accordion-header">
			<button class="accordion-button ${chNum=='ch04'?'':'collapsed'}" type="button" data-bs-toggle="collapse" data-bs-target="#ch04" aria-expanded="${chNum=='ch04'}" aria-controls="ch04">
				Ch04. Controller (Validation)
			</button>
	    </h2>
	<div id="ch04" class="accordion-collapse collapse ${chNum=='ch04'?'show':''}" data-bs-parent="#accordionMenu">
		<div class="accordion-body">
			<ul>
				<li><a href="${pageContext.request.contextPath}/ch04/loginForm">로그인 폼</a></li>
		
			</ul>
		</div>
	</div>
	</div>
	<div class="accordion-item">
		<h2 class="accordion-header">
			<button class="accordion-button ${chNum=='ch05'?'':'collapsed'}" type="button" data-bs-toggle="collapse" data-bs-target="#ch05" aria-expanded="${chNum=='ch05'}" aria-controls="ch05">
				Ch05. Controller (Header / Cookie)
			</button>
	    </h2>
	<div id="ch05" class="accordion-collapse collapse ${chNum=='ch05'?'show':''}" data-bs-parent="#accordionMenu">
		<div class="accordion-body">
			<ul>
				<li><a href="${pageContext.request.contextPath}/ch05/header">요청 헤더값 얻기</a></li>
				<li><a href="${pageContext.request.contextPath}/ch05/createCookie">쿠키 생성</a></li>
				<li><a href="${pageContext.request.contextPath}/ch05/readCookie">쿠키 읽기</a></li>
		
			</ul>
		</div>
	</div>
	</div>
	<div class="accordion-item">
		<h2 class="accordion-header">
			<button class="accordion-button ${chNum=='ch06'?'':'collapsed'}" type="button" data-bs-toggle="collapse" data-bs-target="#ch06" aria-expanded="${chNum=='ch06'}" aria-controls="ch06">
				Ch06. Controller (Forward / Redirect)
			</button>
	    </h2>
	<div id="ch06" class="accordion-collapse collapse ${chNum=='ch06'?'show':''}" data-bs-parent="#accordionMenu">
		<div class="accordion-body">
			<ul>
				<li><a href="${pageContext.request.contextPath}/ch06/forward">포워드</a></li>
				<li><a href="${pageContext.request.contextPath}/ch06/redirect">리다이렉트</a></li>
				<li><a href="${pageContext.request.contextPath}/ch06/sessionData">다른 요청시 세션 데이터 이용</a></li>
				<li><a href="${pageContext.request.contextPath}/ch06/cartview">장바구니 보기</a></li>
				<li><a href="${pageContext.request.contextPath}/ch06/productlist">상품목록</a></li>
			</ul>
		</div>
	</div>
	</div>
	<div class="accordion-item">
		<h2 class="accordion-header">
			<button class="accordion-button ${chNum=='ch07'?'':'collapsed'}" type="button" data-bs-toggle="collapse" data-bs-target="#ch07" aria-expanded="${chNum=='ch07'}" aria-controls="ch07">
				Ch07. Controller (Data Delivery)
			</button>
	    </h2>
	<div id="ch07" class="accordion-collapse collapse ${chNum=='ch07'?'show':''}" data-bs-parent="#accordionMenu">
		<div class="accordion-body">
			<ul>
				<li><a href="${pageContext.request.contextPath}/ch07/objectScope1">데이터(객체) 사용 범위</a></li>
				<li><a href="${pageContext.request.contextPath}/ch07/objectScope2">데이터(객체) 사용 범위</a></li>
			</ul>
		</div>
	</div>
	</div>
	
	<div class="accordion-item">
		<h2 class="accordion-header">
			<button class="accordion-button ${chNum=='ch08'?'':'collapsed'}" type="button" data-bs-toggle="collapse" data-bs-target="#ch08" aria-expanded="${chNum=='ch08'}" aria-controls="ch08">
				Ch08. Controller (Session Support) 세션 지원 API
			</button>
	    </h2>
	<div id="ch08" class="accordion-collapse collapse ${chNum=='ch08'?'show':''}" data-bs-parent="#accordionMenu">
		<div class="accordion-body">
			<ul>
				<li><a href="${pageContext.request.contextPath}/ch08/login">로그인 폼</a></li> <!-- a태그로 요청하는 것은 get 방식 -->
				<li><a href="${pageContext.request.contextPath}/ch08/loginInfo">로그인 정보</a></li>
				<li><a href="${pageContext.request.contextPath}/ch08/logout">로그아웃</a></li>
				
			</ul>
		</div>
	</div>
	</div>
	
</div>
