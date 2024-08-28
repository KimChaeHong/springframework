<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/common/top.jsp"/>

<div class="card">
	<div class="card-header">
		쿠키생성
	</div>
	<div class="card-body">
		<p>브라우저 종류 : ${browser}</p>
		<p>클라이언트 IP : ${clientIP}</p>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/bottom.jsp"/>