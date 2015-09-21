<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib uri="/WEB-INF/tld/hotel.tld" prefix="ht"%>

<fmt:setLocale value = "${sessionScope.local}"/>
<fmt:setBundle basename="localization.locale" var="loc"/>
<fmt:message bundle="${loc }" key = "local.home" var="home" />
<fmt:message bundle="${loc }" key = "local.index.cabinet" var="cabinet" />
<fmt:message bundle="${loc }" key = "local.index.logout.exit" var="logout" />
<fmt:message bundle="${loc }" key = "local.change.user.info" var="title" />
<fmt:message bundle="${loc }" key = "local.client.name" var="name" />
<fmt:message bundle="${loc }" key = "local.surname" var="surname" />
<fmt:message bundle="${loc }" key = "local.patronymic" var="patronymic" />
<fmt:message bundle="${loc }" key = "local.login" var="login" />
<fmt:message bundle="${loc }" key = "local.email" var="email" />
<fmt:message bundle="${loc }" key = "local.passport" var="passport" />
<fmt:message bundle="${loc }" key = "local.apply" var="apply" />
<fmt:message bundle="${loc }" key = "local.validation.email" var="valid_email" />
<fmt:message bundle="${loc }" key = "local.validation.name" var="valid_name" />
<fmt:message bundle="${loc }" key = "local.validation.surname" var="valid_surname" />
<fmt:message bundle="${loc }" key = "local.validation.patronymic" var="valid_patronymic" />
<fmt:message bundle="${loc }" key = "local.validation.passport" var="valid_passport" />


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form-validator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/disable-f5.js"></script>

<title>${title}</title>
</head>
<body onload="JavaScript:document.body.focus();" onkeydown="return showKeyCode(event)">
	<jsp:include page="/jsp/common/header_for_user.jsp" flush="true" />
	
	<div class="container">

		<!-- Date -->
		<div class="navbar-form pull-right wraper-date calendar">
				<ht:today format="MMMM dd, yyyy" />
		</div>
		<div class="clear"></div>
		
		<!--  Message -->
		<c:if test="${not empty errorMessage }">
			<div class="alert alert-danger">${errorMessage}</div>
		</c:if>
		<c:if test="${not empty actionMessage }">
			<div class="alert alert-success">${actionMessage }</div>
		</c:if>

		<div class="clear"></div>
		
		<div class="pull-center">
				<form  action="${pageContext.request.contextPath}/Controller" method = "post" >	
					<p  style="font-size: 150%">${title}</p>			
					<table class="table table-striped" style="text-align:center" id="change_apartment">						
						<tr class="info" id = "table-header"  align="center">		
							<td>${name}</td>
							<td>${surname}</td>
							<td>${patronymic}</td>	
							<td>${email}</td>	
							<td>${passport} </td>	
						</tr>
						
						<tr align="center">
							<td>
								<div class ="form-group">
									<input class="form-control required" size="5"  type="text" required name="name" 
										data-validation="custom" 
					 	 	 			data-validation-regexp="^[А-яЁёa-zA-Z]{1,20}$"
										data-validation-help="${valid_name}"
										value="${user.name}"/>
				 	 	 		</div>
							</td>
							<td>
								<div class ="form-group">
									<input class="form-control required" size="5"  type="text"  required name="surname" 
										data-validation="custom"
										data-validation-regexp="^[А-яЁёa-zA-Z]{1,20}$"
										data-validation-help="${valid_surname}"
										value="${user.surname}"/>
				 	 	 		</div>
							</td>
							<td>
								<div class ="form-group">
									<input class="form-control  required" size="5"  type="text"  required name="patronymic"
										data-validation="custom"
										data-validation-regexp="^[А-яЁёa-zA-Z]{1,20}$"
										data-validation-help="${valid_patronymic}"
									    value="${user.patronymic}"/>
				 	 	 		</div>
							</td>
							<td>
								<div class ="form-group">
									<input class="form-control  required" size="5"  type="text"  required name="email" 
										data-validation="custom"
										data-validation-regexp="^([A-Za-z0-9_\.-]+)@([A-Za-z0-9_\.-]+)\.([A-Za-z\.]{2,6})$"
										data-validation-help="${valid_email}"
										value="${user.email}"/>
				 	 	 		</div>
							</td>
							<td>
								<div class ="form-group">
									<input class="form-control required" size="5"  type="text" required name="passport"
										data-validation="custom"
					 	 	 			data-validation-regexp="^[а-яА-ЯёЁa-zA-Z]{2}[0-9]{7}$"
					 	 	 			data-validation-help="${valid_passport}"
									    value="${user.passport}"/>
				 	 	 		</div>
							</td>
						</tr>
					</table>
					<div id="center">
						<input type="hidden" name="command" value="apply_user_info"/>	
						<input type="hidden" name="id_user" value="${user.id}" />
						<input type="submit" id = "btn_apply" class="btn btn-sm  btn-success" value="${apply}"/>
					</div>
				</form>
		</div>
	</div>
	
	<script>
		$.validate();
	</script>

	<div class="clear"></div>
    <footer >
   		 <jsp:include page="/jsp/common/footer.jsp" flush="true" />
    </footer>
</body>
</html>