<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/common/top.jsp" />

<div class="card">
	<div class="card-header">장바구니</div>
	<div class="card-body">
		<ul>					<!-- cart에 대한 get메서드 -->
								<!-- Ch06Item에 대한 리스트 참조 -->
			<c:forEach items="${cart.contents}" var="item">			
				<li class="m-2">
				<span> ${item.pname}</span>
				<a href="deleteitem?pno=${item.pno}"class="btn btn-danger btn-sm">삭제</a>
				</li>
			</c:forEach>
	
		</ul>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/bottom.jsp" />