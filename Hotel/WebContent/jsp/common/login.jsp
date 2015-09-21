<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
 
<fmt:setLocale value = "${sessionScope.local}"/>
<fmt:setBundle basename="localization.locale" var="loc"/>

<fmt:message bundle="${loc }" key = "local.home" var="home" />
<fmt:message bundle="${loc }" key = "local.room" var="room" />
<fmt:message bundle="${loc }" key = "local.contacts" var="contact" />

<fmt:message bundle="${loc }" key = "local.login" var="login" />
<fmt:message bundle="${loc }" key = "local.password" var="password" />
<fmt:message bundle="${loc }" key = "local.authorization" var="authorization" />
<fmt:message bundle="${loc }" key = "local.button.enter" var="enter" />
<fmt:message bundle="${loc }" key = "local.palceholder.login" var="enter_login" />
<fmt:message bundle="${loc }" key = "local.palceholder.password" var="enter_password" />
<fmt:message bundle="${loc }" key = "local.validation.login" var="valid_login" />
<fmt:message bundle="${loc }" key = "local.validation.password" var="valid_password" />

<!DOCTYPE html >
<html>
<head>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/disable-f5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form-validator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

	
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${authorization}</title>
</head>
<body onload="JavaScript:document.body.focus();" onkeydown="return showKeyCode(event)">
	<div class="navbar navbar-default ">
		 <div class="container-fluid">
		    <div class="navbar-header">
		      <a class="navbar-brand" href="#"><img src="${pageContext.request.contextPath}/img/logo.png" width="98" height="34"></a>
		    </div>
		    <div>
		      <ul class="nav navbar-nav" id="bar-bottom">
		        <li><a href="${pageContext.request.contextPath}/index.jsp" style="size:20">${home}</a></li>
		        <li><a href="${pageContext.request.contextPath}/jsp/common/room.jsp">${room}</a></li>
		        <li><a href="#">${reservation}</a></li> 
		        <li><a href="${pageContext.request.contextPath}/jsp/common/contacts.jsp">${contact}</a></li> 
		      </ul>
		  </div>
		  </div>
	</div>
	
	<div class="container">
		 <div class = "row ">		 
			 <div class = "col-md-4 col-xs-4" ></div>	
		 	 <div class = "col-md-4 col-xs-4" id="login-div" >
		 		 <h2 align="center">${authorization}</h2>	<br/>		 	 	 
		 	 	 <form  action="${pageContext.request.contextPath}/Controller" method = "post" >
		 	 	 	<input type="hidden" name="command" value="log_in"/>
		 	 			<div class ="form-group">
			 	 	 		<label for = "login">${login }</label>
							<input class="form-control required" type="text" required name="login" 
								data-validation="custom" 
			 	 	 			data-validation-regexp="^[А-яЁёa-zA-Z0-9_-]{3,20}$"
								data-validation-help="${valid_login}"
								value="" placeholder="${enter_login}"/>
				 	 	</div>
				 	 	 <div class ="form-group">
				 	 	 	<label	for="pass">${password}</label>
							<input	class="form-control required" type="password" required name="password" 
								data-validation="custom"
			 	 	 			data-validation-regexp="^[a-z0-9_-]{5,20}$"
			 	 	 			data-validation-help="${valid_password}"
								value="" placeholder="${enter_password}" /> 
			 	 	 	</div>
		 	 	    <c:if test="${not empty errorMessage }">
						<div class="alert alert-danger">${errorMessage}</div>
				    </c:if>
				    <div id="center">
				   		 <input type="submit" class="btn btn-sm  btn-primary" style="margin-top: 5%" value="${enter}" />
				    </div>
				 </form>
		 	 </div>
		 	   <div class = "col-md-4 col-xs-4" ></div>	
		 </div>
	</div>
	
	<div class="clear"></div>
    <footer >
   		 <jsp:include page="/jsp/common/footer.jsp" flush="true" />
    </footer>
    
    <script>
		$.validate();
	</script>
</body>
</html>