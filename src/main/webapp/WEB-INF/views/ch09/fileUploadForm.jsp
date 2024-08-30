<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/top.jsp"%>
<div class="card">
	<div class="card-header">
		파일 업로드
	</div>

	<div class="card-body">			
		<form method="post" enctype="multipart/form-data" action="singleFileUpload">
		 							<!-- └멀티파트로 보낼 경우 서버에서 읽을 줄 모르므로 해석자를 해줘야함 -->
									 <!-- 1. 서블릿이 기본으로 제공하는 api, 2. 외부 라이브러리(오래전 버전) 서블릿4.0은 걱정x -->
									 <!-- web.xml 루트 태그에 보면 버전 나와있다. -->
			<div class="form-group">
				<label for="title">File Title</label> 
				<input type="text" class="form-control" id="title" name="title" placeholder="제목">
			</div>
			<div class="mt-2 form-group">
				<label for="desc">File Description</label> 
				<input type="text" class="form-control" id="desc" name="desc" placeholder="설명">
			</div>
			<div class="mt-2 form-group">
				<label for="attach">File Attach</label>
				<input type="file" class="form-control-file" id="attach" name="attach">
			</div>

			<button type="submit" class="mt-2 btn btn-info btn-sm">싱글 파일 업로드</button>
		</form>
		
		<hr />
		
		<form method="post" enctype="multipart/form-data" action="multiFileUpload">
			<div class="form-group">
				<label for="title">File Title</label> 
				<input type="text" class="form-control" id="title" name="title" placeholder="제목">
			</div>
			<div class="mt-2 form-group">
				<label for="desc">File Description</label> 
				<input type="text" class="form-control" id="desc" name="desc" placeholder="설명">
			</div>
			<div class="mt-2 form-group">
				<label for="attach">File Attach</label> 
				<input type="file" multiple="multiple" class="form-control-file" id="attach" name="attach">
			</div>

			<button type="submit" class="mt-2 btn btn-info btn-sm">멀티 파일 업로드</button>
		</form>
		
		<hr />
		
		<form method="post" enctype="multipart/form-data" action="multiFileUpload">
			<div class="form-group">
				<label for="title">File Title</label> 
				<input type="text" class="form-control" id="ajax-title" name="title" placeholder="제목">
			</div>
			<div class="mt-2 form-group">
				<label for="desc">File Description</label> 
				<input type="text" class="form-control" id="ajax-desc" name="desc" placeholder="설명">
			</div>
			<div class="mt-2 form-group">
				<label for="attach">File Attach</label> 
				<input type="file" multiple="multiple" class="form-control-file" id="ajax-attach" name="attach">
			</div>

			<button type="button"  onclick="uploadFileFromAjax()" class="mt-2 btn btn-info btn-sm">AJAX로 파일 업로드</button>
		</form>
		
		<script>
		 function uploadFileFromAjax(){
			 //multipart/form-data 로 구조를 생성하는 객체
			 const formData =  new FormData(); 
			 formData.append("title",$("#ajax-title").val);
			 formData.append("desc",$("#ajax-desc").val);
			 formData.append("attach",$("#ajax-attach")[0].files[0]); //
			 /* └위와 동일한 코드 : formData.append("attach",document.querySelector("#attach").files[0]); */
			 
			 $.ajax({
				url:"uploadFileFromAjax",
				method:"post", //첨부파일을 보낼 땐 무조건 post
				data : formData,
				cache: false, // 브라우저 데이터를 메모리에 저장하지 말라는 뜻
				processData:false,//요청 HTTP 본문의 내용을 가공 처리 하지 말라는 뜻(QueryString으로 변환하지 말라는 뜻)
			 	contentType:false, //본문에는 여러개의 contentType이 존재하므로 한 개로 표현 못한다. 헤더에 추가를 하면 모든 값의 content를 동일하게 해석한다.
				success : function(data){
					//ajax응답은? http조각이거나 json이거나
							//응답으로 받아야 할 것은?
							//{"result":"ok"}
					if(data.result == "ok"){
						location.href= "downloadFileList";
					}else{
						console.log("전송")
					}
				}
			 });
		 }
			
		
		</script>
		
		
		
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/bottom.jsp" />

