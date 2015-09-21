<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib uri="/WEB-INF/tld/hotel.tld" prefix="ht"%>

<fmt:setLocale value = "${sessionScope.local}"/>
<fmt:setBundle basename="localization.locale" var="loc"/>

<fmt:message bundle="${loc }" key = "local.home" var="home" />
<fmt:message bundle="${loc }" key = "local.index.logout.exit" var="logout" />
<fmt:message bundle="${loc }" key = "local.client.title" var="client_title" />
<fmt:message bundle="${loc }" key = "local.index.cabinet" var="cabinet" />
<fmt:message bundle="${loc }" key = "local.admin.info" var="admin_info" />
<fmt:message bundle="${loc }" key = "local.admin.room.administration" var="room_administration" />
<fmt:message bundle="${loc }" key = "local.admin.order.administration" var="order_administration" />
<fmt:message bundle="${loc }" key = "local.admin.user.administration" var="user_administration" />
<fmt:message bundle="${loc }" key = "local.client.name" var="name" />
<fmt:message bundle="${loc }" key = "local.surname" var="surname" />
<fmt:message bundle="${loc }" key = "local.patronymic" var="patronymic" />
<fmt:message bundle="${loc }" key = "local.login" var="login" />
<fmt:message bundle="${loc }" key = "local.email" var="email" />
<fmt:message bundle="${loc }" key = "local.passport" var="passport" />
<fmt:message bundle="${loc }" key = "local.client.role" var="role" />
<fmt:message bundle="${loc }" key = "local.room" var="room" />
<fmt:message bundle="${loc }" key = "local.reservation" var="reservation" />
<fmt:message bundle="${loc }" key = "local.contacts" var="contact" />
<fmt:message bundle="${loc }" key = "local.edit" var="edit" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/disable-f5.js"></script>

<title>${cabinet}</title>
</head>
<body>	
	<div class="navbar navbar-default ">
	  	<div class="container-fluid">
	  		<div class="col-md-9 col-xs-0">
	  		<div class="navbar-header">
			     	 <a class="navbar-brand" href="#"><img src="${pageContext.request.contextPath}/img/logo.png" width="98" height="34"></a>
			    </div>
			    <div>
				      <ul class="nav navbar-nav" id="bar-bottom">
					        <li><a href="${pageContext.request.contextPath}/index.jsp">${home}</a></li>
					        <li><a href="${pageContext.request.contextPath}/jsp/common/room.jsp">${room}</a></li>
					        <li><a href="${pageContext.request.contextPath}/jsp/common/contacts.jsp">${contact}</a></li> 
					   </ul>
				    </div>		
				</div>
			    <div class="col-md-3 col-xs-3" >
					 <div id="active_user" class="pull-right" style="width: 80%">
							<ol class="breadcrumb">
								<li><span class="glyphicon glyphicon-user"></span>${user.login}</li>	
								<li><a href="${pageContext.request.contextPath}/jsp/common/logout.jsp">${logout}</a></li>									
							</ol>
					</div>
			   </div>
   		 </div>
	</div>

	<div class="container">
	
		<!--  Message -->
		<c:if test="${not empty errorMessage }">
			<div class="alert alert-danger">${errorMessage}</div>
		</c:if>
		<c:if test="${not empty actionMessage }">
			<div class="alert alert-success">${actionMessage }</div>
		</c:if>
		
		<div class="clear"></div>

		<div class="btn-group pull-right" style="margin-bottom: 3%">
			<form class="navbar-form pull-right" action="${pageContext.request.contextPath}/Controller" method="post">
				<input type="hidden" name="command" value="room_administration" />
				<button class="btn btn-sm btn-info" type="submit">
					${room_administration}
				</button>
			</form>
			
			<form class="navbar-form pull-right" action="${pageContext.request.contextPath}/Controller" method="post">
				<input type="hidden" name="command" value="order_administration" />
				<button class="btn  btn-sm btn-info" type="submit">
					${order_administration}
				</button>
			</form>
			
			<form class="navbar-form pull-right" action="${pageContext.request.contextPath}/Controller" method="post">
				<input type="hidden" name="command" value="user_administration" />
				<button class="btn btn-sm btn-info " type="submit">
					${user_administration}
				</button>
			</form>
		</div>	
		

	<div style="clear: both">
	<p style="font-size: 150%">${admin_info}</p>
		<table class="table table-striped">			
			<tr class="info">
				<td><span id = "table-header">${name}</span></td>
				<td>${user.name}</td>
			</tr>
			<tr class="info">
				<td><span id = "table-header">${surname}</span></td>
				<td>${user.surname}</td>
			</tr>
			<tr class="info">
				<td><span id = "table-header">${patronymic}</span></td>
				<td>${user.patronymic}</td>
			</tr>
			<tr class="info">
				<td><span id = "table-header">${login}</span></td>
				<td>${user.login}</td>
			</tr>
			<tr class="info">
				<td><span id = "table-header">${email}</span></td>
				<td>${user.email}</td>
			</tr>
			<tr class="info">
				<td><span id = "table-header">${passport}</span></td>
				<td>${user.passport}</td>
			</tr>
			<tr class="success">
					<td>
						<form action="${pageContext.request.contextPath}/Controller" method="post">
							<input type="hidden" name="command" value="edit_user_info" /> 
							<input type="hidden" name="id_user" value="${user.id }" />
							<button class="btn  btn-sm  btn-success" type="submit">
									<span class="glyphicon glyphicon-pencil"></span>
									${edit}
							</button>
						</form>
					</td>
					<td></td>				
			</tr>
		</table>
	</div>
	</div>

	<div class="clear"></div>
    <footer >
   		 <jsp:include page="/jsp/common/footer.jsp" flush="true" />
    </footer>
</body>
</html>