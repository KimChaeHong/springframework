<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/common/top.jsp" />

<div class="card">
	<div class="card-header">상품 목록</div>
	<div class="card-body">
		<ul>					<!-- cart에 대한 get메서드 -->
								<!-- Ch06Item에 대한 리스트 참조 -->
			<c:forEach items="${productList}" var="item">			
				<li class="m-2">
				<span> ${item.pname}</span>
				<a href="cartadd?pno=${item.pno}&pname=${item.pname}" class="btn btn-info btn-sm">장바구니 넣기</a>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/bottom.jsp" />