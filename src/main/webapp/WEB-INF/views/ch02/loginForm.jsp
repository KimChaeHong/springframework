<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp"%>
<div class="card">
	<div class="card-header">
		로그인
	</div>
	<div class="card-body">
		<form method="post" action="login">
			<div class="mb-3">
					<label for="mid" class="form-label">아이디</label> 
					<input type="text" class="form-control" id="mid" name="mid" value="user1">
				</div>
				<div class="mb-3">
					<label for="mpassword" class="form-label">비밀번호</label> 
					<input type="password" class="form-control" id="mpassword" name="mpassword" value="user1">
				</div>
				
				<input type="submit" value="로그인" class="btn btn-info btn-sm"/>
		</form>
		<hr/>
		
		<a href="logout" class ="btn btn-danger btn-sm">로그아웃</a>
		
	</div>
</div>
<%@ include file="/WEB-INF/views/common/bottom.jsp"%>
