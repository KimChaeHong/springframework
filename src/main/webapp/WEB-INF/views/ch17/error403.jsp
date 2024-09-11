<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/common/top.jsp"/>

<div class="card">
	<div class="card-header">
		권한 불충분
	</div>
	<div class="card-body">
		로그인이 되어 있지 않거나 <br/>
		현재 로그인 된 사용자는 요청할 권한이 없습니다.
	</div>
</div>
<jsp:include page="/WEB-INF/views/common/bottom.jsp"/>