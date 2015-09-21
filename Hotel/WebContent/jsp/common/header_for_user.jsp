<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value = "${sessionScope.local}"/>
<fmt:setBundle basename="localization.locale" var="loc"/>

<fmt:message bundle="${loc }" key = "local.home" var="home" />
<fmt:message bundle="${loc }" key = "local.index.logout.exit" var="logout" />
<fmt:message bundle="${loc }" key = "local.room" var="room" />
<fmt:message bundle="${loc }" key = "local.contacts" var="contact" />
<fmt:message bundle="${loc }" key = "local.index.cabinet" var="cabinet" />

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-default ">
	  	<div class="container-fluid">
	  		<div class="col-md-9 col-xs-9">
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
			    <div class="col-md-3 col-xs-3 pull-right">
			    	<div class="choose" id="choose">
				    	<ol id ="for_user" class="breadcrumb" >
							<c:if test="${user.role eq 'ADMIN'}">
								<li><span class="glyphicon glyphicon-user" aria-hidden="true"></span>   ${user.login}</li>	
								<li><a href="${pageContext.request.contextPath}/jsp/admin/admin.jsp">${cabinet}</a></li>
							</c:if>
							
							<c:if test="${user.role eq 'CLIENT'}">
								<li><span class="glyphicon glyphicon-user" aria-hidden="true"></span>   ${user.login}</li>	
								<li><a href="${pageContext.request.contextPath}/jsp/client/client_cabinet.jsp">${cabinet}</a></li>
							</c:if>									
							<li><a href="${pageContext.request.contextPath}/jsp/common/logout.jsp">${logout}</a></li>									
						</ol>
			    	</div>
					
			   </div>
   		 </div>
	</nav>
</body>
</html>