<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/common/top.jsp" />

<div class="card">
	<div class="card-header">계좌 이체 실패</div>
	<div class="card-body">
		<p>${errorMessage}</p>
	</div>
</div>
<jsp:include page="/WEB-INF/views/common/bottom.jsp" />