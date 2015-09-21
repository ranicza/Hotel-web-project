<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<fmt:setLocale value = "${sessionScope.local}"/>
<fmt:setBundle basename="localization.locale" var="loc"/>

<fmt:message bundle="${loc }" key = "local.home" var="home" />
<fmt:message bundle="${loc }" key = "local.room" var="room" />
<fmt:message bundle="${loc }" key = "local.locbutton.name.ru" var="ru_button" />
<fmt:message bundle="${loc }" key = "local.locbutton.name.en" var="en_button" />
<fmt:message bundle="${loc }" key = "local.reservation" var="reservation" />
<fmt:message bundle="${loc }" key = "local.contacts" var="contact" />

<fmt:message bundle="${loc }" key = "local.registration" var="registration" />
<fmt:message bundle="${loc }" key = "local.login" var="login" />
<fmt:message bundle="${loc }" key = "local.password" var="password" />
<fmt:message bundle="${loc }" key = "local.repeat.password" var="password_repeat" />
<fmt:message bundle="${loc }" key = "local.email" var="email" />
<fmt:message bundle="${loc }" key = "local.name" var="name" />
<fmt:message bundle="${loc }" key = "local.surname" var="surname" />
<fmt:message bundle="${loc }" key = "local.patronymic" var="patronymic" />
<fmt:message bundle="${loc }" key = "local.passport" var="passport" />

<fmt:message bundle="${loc }" key = "local.button.registrate" var="registrate" />

<fmt:message bundle="${loc }" key = "local.validation.login" var="valid_login" />
<fmt:message bundle="${loc }" key = "local.validation.password" var="valid_password" />
<fmt:message bundle="${loc }" key = "local.validation.repassword" var="valid_repassword" />
<fmt:message bundle="${loc }" key = "local.validation.email" var="valid_email" />
<fmt:message bundle="${loc }" key = "local.validation.name" var="valid_name" />
<fmt:message bundle="${loc }" key = "local.validation.surname" var="valid_surname" />
<fmt:message bundle="${loc }" key = "local.validation.patronymic" var="valid_patronymic" />
<fmt:message bundle="${loc }" key = "local.validation.passport" var="valid_passport" />

<fmt:message bundle="${loc }" key = "local.palceholder.login" var="enter_login" />
<fmt:message bundle="${loc }" key = "local.palceholder.password" var="enter_password" />
<fmt:message bundle="${loc }" key = "local.palceholder.email" var="enter_email" />
<fmt:message bundle="${loc }" key = "local.palceholder.name" var="enter_name" />
<fmt:message bundle="${loc }" key = "local.palceholder.surname" var="enter_surname" />
<fmt:message bundle="${loc }" key = "local.palceholder.patronymic" var="enter_patronymic" />
<fmt:message bundle="${loc }" key = "local.palceholder.passport" var="enter_passport" />

<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form-validator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/disable-f5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${registration}</title>
</head>
<body onload="JavaScript:document.body.focus();" onkeydown="return showKeyCode(event)">

	<nav class="navbar navbar-default ">
		 <div class="container-fluid">
		    <div class="navbar-header">
		      <a class="navbar-brand" href="#"><img src="${pageContext.request.contextPath}/img/logo.png" width="98" height="34"></a>
		    </div>
		    <div>
		      <ul class="nav navbar-nav" id="bar-bottom">
		        <li><a href="${pageContext.request.contextPath}/index.jsp" >${home}</a></li>
		        <li><a href="${pageContext.request.contextPath}/jsp/common/room.jsp">${room}</a></li>
		        <li><a href="#">${reservation}</a></li> 
		        <li><a href="${pageContext.request.contextPath}/jsp/common/contacts.jsp">${contact}</a></li> 
		      </ul>
		  </div>
		  </div>
	</nav>

	<div class="container">
		 <div class = "row">	
		 	 <div class = "col-md-4 col-xs-4" ></div>		 	
		 	 <div class = "col-md-4 col-xs-4">
		 	 	 <h2 align="center">${registration}</h2>
		 	 	 <form   class="form register-form" action="${pageContext.request.contextPath}/Controller" method = "post" >
		 	 		 <input type="hidden" name="command" value="registration"/>
		 	 	 
		 	 	    <!-- Login -->
		 	 	 	<div class ="form-group">
		 	 	 		<label for = "login">${login}</label>
		 	 	 		<input class="form-control required" type="text" required autofocus	name="login" 
		 	 	 			data-validation="custom" 
		 	 	 			data-validation-regexp="^[А-яЁёa-zA-Z0-9_-]{3,20}$"
							data-validation-help="${valid_login}"
							placeholder="${enter_login}" />
		 	 	 	</div>
		 	 	 	
		 	 	 	<!--  Password-->
		 	 	 	<div class ="form-group">
		 	 	 		<label	for="pass">${password}</label>
		 	 	 		<input class="form-control required" type="password" required name="password" 
		 	 	 			data-validation="custom"
		 	 	 			data-validation-regexp="^[a-z0-9_-]{5,20}$"
		 	 	 			data-validation-help="${valid_password}"
		 	 	 			placeholder="${enter_password}" />	
		 	 	 	</div>
		 	 	 	
		 	 	 	<!--  Password repeated-->
					<div class ="form-group">
						<label for="password_repeat">${password_repeat}</label>
						<input class="form-control required" type="password" required name="password_repeat" 
							data-validation="custom" 
							data-validation-matches-match="password"
							data-validation-help="${valid_repassword}"
							data-validation-regexp="^[a-z0-9_-]{5,20}$"
							placeholder="${password_repeat}" />
					</div>
					
					<!-- Email -->
					<div  class ="form-group">
						<label for="email">${email}</label>
						<input class="form-control required" type="text" required name="email" 
							data-validation="custom"
							data-validation-regexp="^([A-Za-z0-9_\.-]+)@([A-Za-z0-9_\.-]+)\.([A-Za-z\.]{2,6})$"		
							data-validation-help="${valid_email}"
							placeholder="${enter_email}" />
					</div>
					
					<!-- Name -->
					<div  class ="form-group">
						<label for="name">${name}</label>
						<input class="form-control required" type="text" required name="name"	
							data-validation="custom"
							data-validation-regexp="^[А-яЁёa-zA-Z]{1,20}$"
							data-validation-help="${valid_name}"
							placeholder="${enter_name}" />
					</div>
					
					<!-- Surename  -->
					<div  class ="form-group">
						<label for="surname">${surname}</label> 
						<input class="form-control required" type="text" required name="surname"
							data-validation="custom"
							data-validation-regexp="^[А-яЁёa-zA-Z]{1,20}$"
							data-validation-help="${valid_surname}"
							placeholder="${enter_surname}" />
					</div>
					
					<!-- Patronymic -->
					<div  class ="form-group">
						<label for="patronymic">${patronymic}</label> 
						<input class="form-control required" type="text" required name="patronymic"
							data-validation="custom"
							data-validation-regexp="^[А-яЁёa-zA-Z]{1,20}$"
							data-validation-help="${valid_patronymic}"
							placeholder="${enter_patronymic}" />
					</div>
					
					<!-- Passport -->
					<div  class ="form-group">
						<label for="passport">${passport}</label> 
						<input class="form-control required" type="text" required name="passport" 
							data-validation="custom"
		 	 	 			data-validation-regexp="^[а-яА-ЯёЁa-zA-Z]{2}[0-9]{7}$"
		 	 	 			data-validation-help="${valid_passport}"
							placeholder="${enter_passport}" />
					</div>
					
					<div id="center">
						<input type="submit" class="btn btn-sm btn-primary" value= "${registrate}" />
					</div>
				 </form>				 
		 	 </div>
		 	 <div class = "col-md-4 col-xs-4" ></div>	
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