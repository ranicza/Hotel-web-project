<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<fmt:setLocale value = "${sessionScope.local}"/>
<fmt:setBundle basename="localization.locale" var="loc"/>

<fmt:message bundle="${loc }" key = "local.home" var="home" />
<fmt:message bundle="${loc }" key = "local.createorder.title" var="title" />
<fmt:message bundle="${loc }" key = "local.index.logout.exit" var="logout" />
<fmt:message bundle="${loc }" key = "local.index.cabinet" var="cabinet" />
<fmt:message bundle="${loc }" key = "local.createorder.date.arrival" var="date_arrival" />
<fmt:message bundle="${loc }" key = "local.createorder.date.departure" var="date_departure" />
<fmt:message bundle="${loc }" key = "local.createorder.free.rooms" var="free_rooms" />
<fmt:message bundle="${loc }" key = "createorder.roomnumber" var="room_number" />
<fmt:message bundle="${loc }" key = "createorder.room.category" var="category" />
<fmt:message bundle="${loc }" key = "createorder.room.positions" var="positions" />
<fmt:message bundle="${loc }" key = "createorder.room.cost" var="cost" />
<fmt:message bundle="${loc }" key = "createorder.check" var="check" />
<fmt:message bundle="${loc }" key = "createorder.createorder" var="create" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/disable-f5.js"></script>
<title>Free room</title>
</head>
<body onload="JavaScript:document.body.focus();" onkeydown="return showKeyCode(event)">
	<jsp:include page="/jsp/common/header_for_user.jsp" flush="true" />

	<div class="container">
	
		<!--  Message -->
		<c:if test="${not empty errorMessage }">
			<div class="alert alert-danger">${errorMessage}</div>
		</c:if>
		<c:if test="${not empty actionMessage }">
			<div class="alert alert-success">${actionMessage }</div>
		</c:if>
		<div class="clear"></div>
		
		<a href="${pageContext.request.contextPath}/jsp/client/find_apartment.jsp">
			<img id="back" src="${pageContext.request.contextPath}/img/arrow.png" >
		</a>
		<p style="font-size: 150%; text-align: center">${title}</p>	
		
		<div class="pull-center" >	
			<form action="${pageContext.request.contextPath}/Controller" method="post">					
			<p style="font-size: 130%">${free_rooms}</p>	
			<table class="table table-striped">				
				<tr class="info" id = "table-header" align="center">
					<td>${room_number }</td>
					<td>${category}</td>
					<td>${positions}</td>
					<td>${cost}</td>
					<td>${check}</td>
				</tr>
				<c:forEach var="apartment" items="${freeApartment}">
					<tr  align="center">
						<td><c:out value="${apartment.roomNumber}" /></td>
						<td><c:out value="${apartment.category}" /></td>
						<td><c:out value="${apartment.positions}" /></td>
						<td><c:out value="${apartment.cost}" /></td>
						<td><input type="radio" name="id_apartment" value="${apartment.id}" /></td>
					</tr>
				</c:forEach>
			</table>
			<div style="text-align:center">
				<input type="hidden" name="command" value="create_order" />	
				<button class="btn btn-sm btn-success" type="submit">
					<i class="glyphicon glyphicon-star"></i>
					${create}
				</button>
			</div>		
			</form>
		</div>
	
	</div>
	
	<div class="clear"></div>
    <footer >
   		 <jsp:include page="/jsp/common/footer.jsp" flush="true" />
    </footer>
</body>
</html>