<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/common/top.jsp"/>

<div class="card">
	<div class="card-header">
		로그인 정보
	</div>
	<div class="card-body">
		<p>mid:${login.mid}</p> <!-- session.setAttribute("login", member); -->
		<p>memail:${login.memail}</p>
		<p>mpassword:${login.mpassword}</p>
	</div>
</div>
<jsp:include page="/WEB-INF/views/common/bottom.jsp"/>