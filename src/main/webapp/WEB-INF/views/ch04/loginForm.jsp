<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- taglib 지시자 : 라이브러리가 제공해주는 태그를 쓰게한다. uri: 어떤 라이브러리인지?-->
<jsp:include page="/WEB-INF/views/common/top.jsp" />
<div class="card">
	<div class="card-header">로그인 양식</div>
	<div class="card-body">
		<form class="m-2" method="post" action="login">  <!-- ※네 -->
			<div class="form-group mb-2">
				<label for="mid">아이디</label> <!--for : label에 포커스를 맞춰도 입력 양식으로 이동 되게한다. -->
				<input type="text" class="form-control" id="mid" name="mid" value="${ch04LoginForm.mid}"><!-- dto의 이름은 첫자를 소문자로 -->
				<div class="text-danger" style="font-size: 0.7rem">
					<form:errors path="ch04LoginForm.mid"/><!-- 어떤 dto의 어떤 filed 인지 -->
				</div>
			</div>

			<div class="form-group mb-2">
				<label for="mpassword">패스워드</label> 
				<input type="password" class="form-control" id="mpassword" name="mpassword" value="${ch04LoginForm.mpassword}">
				<div class="text-danger" style="font-size: 0.7rem">
					<form:errors path="ch04LoginForm.mpassword"/><!-- 어떤 dto의 어떤 filed 인지 -->
				</div>
			</div>

			<!-- 제출 버튼: 양식의 데이터를 서버로 보내겠다. -->
			<button type="submit" class="btn btn-info btn-sm">로그인</button>
		</form>
	</div>
</div>
<jsp:include page="/WEB-INF/views/common/bottom.jsp" />