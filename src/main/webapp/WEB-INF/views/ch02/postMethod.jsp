<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp"%>
<div class="card">
	<div class="card-header">
		POST 방식
	</div>
	<div class="card-body">
		<form method="post" action="postFormTag"><!--form 태그는 default가 get이기 때문에 명시적으로 작성  -->
			<div class="mb-3">
					<label for="bno" class="form-label">게시물 번호</label> <input
						type="number" class="form-control" id="bno" name="bno" value="5">
				</div>
				<div class="mb-3">
					<label for="bkind" class="form-label">게시물 종류</label> <input
						type="text" class="form-control" id="bkind" name="bkind"
						value="free">
				</div>
				<!-- 제출 버튼을 만드는 방법 1 -->
				<input type="submit" value="제출" class="btn btn-info btn-sm">
				<br/>
				<!-- 제출 버튼을 만드는 방법 2 -->
				<input type="image" class="mt-2" src="${pageContext.request.contextPath}/resources/image/img_submit.gif">
				<br/>
				<!-- 제출 버튼을 만드는 방법 3 -->
				<button type="submit" class="mt-2 btn btn-info btn-sm">제출</button><!-- form 태그 안에 button이 있을 경우 디폴트 type이 submit -->
		</form>
		
		<hr/>
		<div class="mt-2">
			<form method="get" action="getFormTag">
				<div class="mb-3">
					<label for="bno" class="form-label">게시물 번호</label> <input
						type="number" class="form-control" id="bno" name="bno" value="5">
					<!-- 파라메타 값으로 name 속성 -->
				</div>
				<div class="mb-3">
					<label for="bkind" class="form-label">게시물 종류</label> <input
						type="text" class="form-control" id="bkind" name="bkind"
						value="free">
				</div>
				<input type="submit" value="제출" class="btn-info btn-sm">
			</form>
		</div>
		<hr />
		<div class="mt-2">
			<button onclick="requestPost1()" class="btn btn-info btn-sm mb-2">JavaScript:Ajax 이용</button>
			<button onclick="requestPost2()" class="btn btn-info btn-sm mb-2">JavaScript:Ajax 이용</button>
			
			<div id="ajaxResult" class="border mt-2">
			</div>
			<script>
				function requestPost1(){
					$.ajax({
						 url:"postAjax1",
						 method:"post",
						 //data:"bno=5&bkind=free",
						 data :{bno:5, bkind:"free"},
						 success:function(data){
						 	//data가 HTML 조각일 경우
							 $("#ajaxResult").html(data);
							 //document.queySelector("#ajaxResult").innerHTML = data;
						 }
					});
				}
					
				function requestPost2(){
					$.ajax({
							url:"postAjax2",
							method:"post",
							//data:"bno=5&bkind=free",
							data :{bno:5, bkind:"free"},
							success:function(data){
							 	//data가 json 일 경우
								console.log(data);
								var content = "";
								content += "<div class ='card'>";
								content += "<div class='card-header'>Ajax의 JSON 응답</div>";			
								content += "<div class='card-body'>";

								data.data.forEach(function(item) {
									content += "<img height='100' src='${pageContext.request.contextPath}/resources/image/photos/" + item + "'class='me-2'>"//람다식
								});
								content += "		</div>";
								content += "</div>";
								$("#ajaxResult").html(content);
							 }
						});	
				}
			</script>

		</div>
		
	</div>
</div>
<%@ include file="/WEB-INF/views/common/bottom.jsp"%>
