<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/common/top.jsp"/>

<div class="card">
	<div class="card-header">
		session에 저장 된 데이터 이용
	</div>
	<div class="card-body">
		<p>mid:${member1.mid}</p>
		<p>mname:${member1.mname}</p>
		<p>memail:${member1.memail}</p>
	</div>
	<div class = "m-3">
		<p>mid:${member2.mid}</p>
		<p>mname:${member2.mname}</p>
		<p>memail:${member2.memail}</p>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/bottom.jsp"/>