<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib uri="/WEB-INF/tld/hotel.tld" prefix="ht"%>

<fmt:setLocale value = "${sessionScope.local}"/>
<fmt:setBundle basename="localization.locale" var="loc"/>

<fmt:message bundle="${loc }" key = "local.index.logout.exit" var="logout" />
<fmt:message bundle="${loc }" key = "local.index.logout.exit.text" var="question" />
<fmt:message bundle="${loc }" key = "local.yes" var="yes" />
<fmt:message bundle="${loc }" key = "local.back" var="back" />
<fmt:message bundle="${loc }" key = "local.index.logout.exit" var="logout" />
<fmt:message bundle="${loc }" key = "local.room" var="room" />
<fmt:message bundle="${loc }" key = "local.reservation" var="reservation" />
<fmt:message bundle="${loc }" key = "local.contacts" var="contact" />
<fmt:message bundle="${loc }" key = "local.index.cabinet" var="cabinet" />
<fmt:message bundle="${loc }" key = "local.home" var="home" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />

<title>${logout}</title>
</head>
<body>
	<nav class="navbar navbar-default ">
	  	<div class="container-fluid">
	  		<div class="col-md-10 col-xs-10">
	  		<div class="navbar-header">
			     	 <a class="navbar-brand" href="#"><img src="${pageContext.request.contextPath}/img/logo.png" width="98" height="34"></a>
			    </div>
			    <div>
				      <ul class="nav navbar-nav" id="bar-bottom">
					        <li><a href="${pageContext.request.contextPath}/index.jsp">${home}</a></li>
					        <li><a href="${pageContext.request.contextPath}/jsp/common/room.jsp">${room}</a></li>
					        <li><a href="#">${reservation}</a></li> 
					        <li><a href="${pageContext.request.contextPath}/jsp/common/contacts.jsp">${contact}</a></li> 
					   </ul>
				    </div>		
				</div>
			    <div class="col-md-2 col-xs-2">
			        <div id="choose">
				        <ol class="breadcrumb" id ="for_user">						
							<c:if test="${user.role eq 'ADMIN'}">
								<li><span class="glyphicon glyphicon-user" aria-hidden="true"></span>   ${user.login}</li>	
								<li><a href="${pageContext.request.contextPath}/jsp/admin/admin.jsp">${cabinet}</a></li>
							</c:if>
							
							<c:if test="${user.role eq 'CLIENT'}">
								<li><span class="glyphicon glyphicon-user" aria-hidden="true"></span>   ${user.login}</li>	
								<li><a href="${pageContext.request.contextPath}/jsp/client/client_cabinet.jsp">${cabinet}</a></li>
							</c:if>																	
						</ol>
			        </div>
			   </div>
   		 </div>
	</nav>

	<div class="container" id="logout_form">
		  <h2>${question}</h2>		  	
		 <div class = 'row'>
	        	<form action="${pageContext.request.contextPath}/Controller" method = "post" id="logout_form" >
					<input type="hidden" name="command" value="log_out"/>
					<input type="submit" class="btn btn-sm btn-primary" value="${logout}" /> <br/>
				</form>
		</div>
	</div>
	
	<div class="clear"></div>
    <footer>
   		 <jsp:include page="/jsp/common/footer.jsp" flush="true" />
    </footer>
</body>
</html>