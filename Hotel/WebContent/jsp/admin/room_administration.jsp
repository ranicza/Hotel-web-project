<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib uri="/WEB-INF/tld/hotel.tld" prefix="ht"%>


<fmt:setLocale value = "${sessionScope.local}"/>
<fmt:setBundle basename="localization.locale" var="loc"/>
<fmt:message bundle="${loc }" key = "local.admin.room.administration" var="room_administration" />
<fmt:message bundle="${loc }" key = "local.home" var="home" />
<fmt:message bundle="${loc }" key = "local.index.logout.exit" var="logout" />
<fmt:message bundle="${loc }" key = "local.admin.room.administration.roomlist" var="administration" />
<fmt:message bundle="${loc }" key = "local.room.administration.roomnumber" var="room_number" />
<fmt:message bundle="${loc }" key = "local.room.administration.positions" var="positions" />
<fmt:message bundle="${loc }" key = "local.room.administration.cost" var="cost" />
<fmt:message bundle="${loc }" key = "local.room.administration.category" var="category" />
<fmt:message bundle="${loc }" key = "local.room.administration.status" var="status" />
<fmt:message bundle="${loc }" key = "local.room.administration.action" var="action" />
<fmt:message bundle="${loc }" key = "local.client.page.return" var="back_client_page" />
<fmt:message bundle="${loc }" key = "local.room.administration.change.status" var="change" />
<fmt:message bundle="${loc }" key = "local.room.administration.change.status" var="choose" />
<fmt:message bundle="${loc }" key = "local.edit" var="edit" />
<fmt:message bundle="${loc }" key = "local.delete" var="delete" />
<fmt:message bundle="${loc }" key = "local.admin.add" var="add" />

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/disable-f5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>

<title>${room_administration}</title>
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
		
		
		
		<div class="pull-right" style="margin-top: 2%">
			<form action="${pageContext.request.contextPath}/Controller" method="post">
				<input type="hidden" name="command" value="add_new_apartment_form" />							
				<button type="submit" class="btn btn-sm btn-success">
				<span class="glyphicon glyphicon-plus"> ${add}</span>
				</button>
			</form>	
		</div>
		
		<div class="clear"></div>
		
		<!-- Room administration -->
		<div class="pull-center">
		<p style="font-size: 150%; margin-bottom: 0%;">${administration}</p>

		</div>
			<table class="table table-striped"  style="align:center">
				<tr class="info" id = "table-header">	
					<td>${room_number}</td>
					<td>${category}</td>
					<td>${positions}</td>
					<td>${cost}, $</td>			
					<td>${edit}</td>
					<td>${delete}</td>
				</tr>
				
				<c:forEach var="apartment" items="${apartmentList}" >
					<tr  align="center">	
									
						<td><c:out value="${apartment.roomNumber}" /></td>
						<td><c:out value="${apartment.category}" /></td>			
						<td><c:out value="${apartment.positions}" /></td>
						<td><c:out value="${apartment.cost}" /></td>
						
						<td>
								<form action="${pageContext.request.contextPath}/Controller" method="post">
								     <input type="hidden" name="command" value="change_data_apartment" />
									 <input type="hidden" name="id_apartment" value="${apartment.id }" />							
									 <button type="submit" class="btn btn-sm btn-warning">
										  <span class="glyphicon glyphicon-pencil"></span>
									 </button>
								</form>
						</td>
						<td>
													
								<form action="${pageContext.request.contextPath}/Controller" method="post">
									 <input type="hidden" name="id_apartment" value="${apartment.id }" />
									 <input type="hidden" name="command" value="delete_apartment" />
									 <button type="submit" class="btn btn-sm btn-danger">
										  <span class="glyphicon glyphicon-trash"></span>
									 </button>
							   </form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>		
		<div class="clear"></div>
		<div class="clear"></div>
	<footer>
		<jsp:include page="/jsp/common/footer.jsp" flush="true"/>
	</footer>

</body>
</html>