<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="/WEB-INF/views/common/top.jsp"/>

<div class="card">
	<div class="card-header">
		국제화(다국어 처리)
	</div>
	<div class="card-body">
          <form id="joinForm" method="post" action="#">
           <div class="input-group">
              <div class="input-group-prepend"><span class="input-group-text"><spring:message code="joinForm.id"/></span></div>
              <input id="mid" type="text" name="mid" class="form-control">
           </div>
           
           <div class="input-group">
              <div class="input-group-prepend"><span class="input-group-text"><spring:message code="joinForm.name"/></span></div>
              <input id="mname" type="text" name="mname" class="form-control"">
           </div>
           
           <div class="input-group">
              <div class="input-group-prepend"><span class="input-group-text"><spring:message code="joinForm.password"/></span></div>
              <input id="mpassword" type="password" name="mpassword" class="form-control">
           </div>
           
                 <div class="input-group">
                    <div class="input-group-prepend">
                       <span class="input-group-text"><spring:message code="joinForm.job"/></span>
                    </div>
                    <input id="mjob" type="text" name="mjob" class="form-control"">
                 </div>
                 
                 <div class="input-group">
                    <div class="input-group-prepend">
                       <span class="input-group-text"><spring:message code="joinForm.city"/></span>
                    </div>
                    <input id="mcity" type="text" name="mcity" class="form-control"">
                 </div>
        
           <div class="mt-3">
              <button class="btn btn-info btn-sm mr-2"><spring:message code="joinForm.btnJoin"/></button>
           </div>
        </form>  
	</div>
</div>
<jsp:include page="/WEB-INF/views/common/bottom.jsp"/>