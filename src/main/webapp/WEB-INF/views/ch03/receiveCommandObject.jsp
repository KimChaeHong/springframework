<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp"%>
<div class="card">
	<div class="card-header">
		Command Object 데이터 읽기
	</div>
	<div class="card-body">
		<p>param1 : ${ch03Dto.param1}<!-- el 객체의 필드값을 복사할 때 참조에 .을 찍고 필드이름만 넣어주면 내부적으로 get 메서드호출-->
		<p>param2 : ${ch03Dto.param2}<!-- el -->
		<p>param3 : ${ch03Dto.param3}<!-- el -->
		<p>param4 : ${ch03Dto.param4}<!-- el -->
		<p>param5 : ${ch03Dto.param5}<!-- el -->
	</div>
		
</div>
<%@ include file="/WEB-INF/views/common/bottom.jsp"%>


