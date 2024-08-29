<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/common/top.jsp" />

<div class="card">
	<div class="card-header">session에 저장 된 데이터 이용</div>
	<div class="card-body">
		<div>
			<p>mid:${member.mid}</p> <!-- 리퀘스트나 세션에 저장된 데이터를 불러올 때 el을 쓴다. -->
			<p>mname:${member.mname}</p> <!-- 리퀘스트에 이 이름이 있으면 리퀘스트에서 가져오고 찾다가 없다면 세션에서 -->
			<p>memail:${member.memail}</p>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/bottom.jsp" />