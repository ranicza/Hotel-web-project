<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value = "${sessionScope.local}"/>
<fmt:setBundle basename="localization.locale" var="loc"/>
<fmt:message bundle="${loc }" key = "local.home" var="home" />
<fmt:message bundle="${loc }" key = "local.room" var="room" />
<fmt:message bundle="${loc }" key = "local.locbutton.name.ru" var="ru_button" />
<fmt:message bundle="${loc }" key = "local.locbutton.name.en" var="en_button" />
<fmt:message bundle="${loc }" key = "local.contacts" var="contact" />
<fmt:message bundle="${loc }" key = "local.index.cabinet" var="cabinet" />
<fmt:message bundle="${loc }" key = "local.index.logout" var="logout" />
<fmt:message bundle="${loc }" key = "local.log.in" var="log_in" />
<fmt:message bundle="${loc }" key = "local.registration" var="registr" />
<fmt:message bundle="${loc }" key = "local.home" var="home" />
<fmt:message bundle="${loc }" key = "local.index.cabinet" var="cabinet" />

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
	<div class="navbar navbar-default ">
	  	<div class="container-fluid">
	  		<div class="col-md-10 col-xs-10">
		  		<div class="navbar-header">
			     	 <a class="navbar-brand" href="#"><img src="${pageContext.request.contextPath}/img/logo.png" width="98" height="34" style="margin-top: 0"></a>
			    </div>
			    <div>
				      <ul class="nav navbar-nav" id="bar-bottom">
					        <li><a href="${pageContext.request.contextPath}/index.jsp">${home}</a></li>
					        <li><a href="${pageContext.request.contextPath}/jsp/common/room.jsp">${room}</a></li>
					        <li><a href="${pageContext.request.contextPath}/jsp/common/contacts.jsp">${contact}</a></li> 
					   </ul>
				    </div>		
				</div>
			    <div class="col-md-2 col-xs-2">
				     <div id= "choose" style="width: 200%">
					    	<c:choose>
					    		<c:when test="${not empty user}" >
					    		<div id="active_user">
									<ol class="breadcrumb">
										<c:if test="${user.role eq 'ADMIN'}">
											<li><a href="${pageContext.request.contextPath}/jsp/admin/admin.jsp">${cabinet}</a></li>
										</c:if>
										<c:if test="${user.role eq 'CLIENT'}">
											<li><a href="${pageContext.request.contextPath}/jsp/client/client_cabinet.jsp">${cabinet}</a></li>
										</c:if>
										
										<li><a href="${pageContext.request.contextPath}/jsp/common/logout.jsp">${logout}</a></li>
									</ol>
								</div>
								</c:when>
								
								<c:otherwise>
									<div id="active_guest">
										<ol class="breadcrumb" >
											<li><a href="${pageContext.request.contextPath}/jsp/common/login.jsp">	${log_in}</a></li>
											<li><a href="${pageContext.request.contextPath}/jsp/common/registration.jsp">${registr}</a></li>
										</ol>
									</div>	
								</c:otherwise>
					    	</c:choose>
		    	    </div>
			   </div>
   		 </div>
	</div>
</body>
</html>