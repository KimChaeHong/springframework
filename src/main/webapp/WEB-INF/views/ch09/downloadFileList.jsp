<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/WEB-INF/views/common/top.jsp"%>
<div class="card">
	<div class="card-header">파일 다운로드</div>

	<div class="card-body">

		<c:forEach items="${fileNames}" var="fileName">
			<li class="m-2">
				<img class="rounded-circle" src="downloadFile?fileName=${fileName}" width="30" height="30"/>
				<a href="downloadFile?fileName=${fileName}">${fileName}</a>
			</li>							<!-- └get 방식으로 파일 이름 보내기  -->
		</c:forEach> 

	</div>
</div>

<jsp:include page="/WEB-INF/views/common/bottom.jsp" />

