<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tld/hotel.tld" prefix="ht"%>

<fmt:setLocale value = "${sessionScope.local}"/>
<fmt:setBundle basename="localization.locale" var="loc"/>
<fmt:message bundle="${loc }" key = "local.home" var="home" />
<fmt:message bundle="${loc }" key = "local.client.order.title" var="title" />
<fmt:message bundle="${loc }" key = "local.index.logout.exit" var="logout" />
<fmt:message bundle="${loc }" key = "local.index.cabinet" var="cabinet" />
<fmt:message bundle="${loc }" key = "local.client.order.order" var="client_orders" />
<fmt:message bundle="${loc }" key = "local.client.order.number.room" var="number_room" />
<fmt:message bundle="${loc }" key = "local.client.order.category.room" var="category_room" />
<fmt:message bundle="${loc }" key = "local.client.order.positions" var="positions" />
<fmt:message bundle="${loc }" key = "local.client.order.order.date" var="order_date" />
<fmt:message bundle="${loc }" key = "local.client.order.ndays" var="ndays" />
<fmt:message bundle="${loc }" key = "local.client.order.totalcost" var="totalcost" />
<fmt:message bundle="${loc }" key = "local.client.order.status" var="status" />
<fmt:message bundle="${loc }" key = "local.client.order.action" var="action" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
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
		
		<!-- Bill info -->
		<div>
			<p style="font-size: 150%">${client_orders}</p>
			<table class="table table-striped" style="align:center">				
				<tr class="info" id = "table-header" align="center">
					<td>${number_room}</td>
					<td>${category_room}</td>
					<td>${positions}</td>
					<td>${order_date}</td>
					<td>${ndays}</td>
					<td>${totalcost}, $</td>
					<td>${status}</td>
					<td></td>
				</tr>
				<c:forEach var="clientOrder" items="${clientOrder}">
					<jsp:useBean id="clientOrder" class="com.epam.te.hotel.model.entity.Order" />
					<tr align="center">
						<ht:delta dateDeparture="${clientOrder.dateDeparture}"
							dateArrival="${clientOrder.dateArrival}" />
						<td><c:out value="${clientOrder.apartment.roomNumber}" /></td>
						<td><c:out value="${clientOrder.apartment.category}" /></td>
						<td><c:out value="${clientOrder.apartment.positions}" /></td>
						<td><c:out value="${clientOrder.dateOrder}" /></td>
						<td><c:out value="${delta}" /></td>
						<td><ht:totalcost delta="${delta}"
								costPerDay="${clientOrder.apartment.cost}" /></td>
						<td><c:out value="${clientOrder.status}" /></td>
						<c:choose> 
							  <c:when test="${clientOrder.status eq 'AWAITING'|| 'DENIED'}">
								  <td><form action="${pageContext.request.contextPath}/Controller" method="post">
										<input type="hidden" name="command" value="delete_order" /> 
										<input type="hidden" name="id_order" value="${clientOrder.id }" />
										<button type="submit" class="btn btn-danger">
											<span class="glyphicon glyphicon-trash"></span>
										</button>
								  </form></td>
							  </c:when>
							    <c:otherwise>
							    	<td></td>
							   </c:otherwise>
					   </c:choose>	
					</tr>
				</c:forEach>
			</table>
		</div>	
	</div>
	
		<div class="clear"></div>
    <footer >
   		 <jsp:include page="/jsp/common/footer.jsp" flush="true" />
    </footer>

</body>
</html>