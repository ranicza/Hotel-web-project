<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib uri="/WEB-INF/tld/hotel.tld" prefix="ht"%>


<fmt:setLocale value = "${sessionScope.local}"/>
<fmt:setBundle basename="localization.locale" var="loc"/>
<fmt:message bundle="${loc }" key = "local.home" var="home" />
<fmt:message bundle="${loc }" key = "local.index.logout.exit" var="logout" />
<fmt:message bundle="${loc }" key = "local.order.administration.title" var="title" />
<fmt:message bundle="${loc }" key = "local.client.page.return" var="back_client_page" />
<fmt:message bundle="${loc }" key = "local.order.administration.orders" var="orders" />
<fmt:message bundle="${loc }" key = "local.order.administration.surname.user" var="surname" />
<fmt:message bundle="${loc }" key = "local.order.administration.number.room" var="number_room" />
<fmt:message bundle="${loc }" key = "createorder.room.category" var="category" />
<fmt:message bundle="${loc }" key = "local.order.administration.order.date" var="order_date" />
<fmt:message bundle="${loc }" key = "local.order.administration.date.arrival" var="date_arrival" />
<fmt:message bundle="${loc }" key = "local.order.administration.date.departure" var="date_departure" />
<fmt:message bundle="${loc }" key = "local.order.administration.total.cost" var="cost" />
<fmt:message bundle="${loc }" key = "local.order.administration.status.order" var="status_order" />
<fmt:message bundle="${loc }" key = "local.order.administration.change.status.order" var="change_status" />
<fmt:message bundle="${loc }" key = "local.order.administration.empty" var="choose" />
<fmt:message bundle="${loc }" key = "local.order.administration.confirmed" var="confirmed" />
<fmt:message bundle="${loc }" key = "local.order.administration.awaiting" var="awaiting" />
<fmt:message bundle="${loc }" key = "local.order.administration.denied" var="denied" />


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/disable-f5.js"></script>
<title>${title}</title>
</head>
<body  onload="JavaScript:document.body.focus();" onkeydown="return showKeyCode(event)">
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
	
		<!-- Order administration -->
			<p style="font-size: 150%">${orders}</p>
			<table class="table table-striped" style="text-align:center">		
				<tr class="info" id = "table-header">
					<td>${surname}</td>
					<td>${number_room}</td>
					<td>${category}</td>
					<td>${order_date}</td>
					<td>${date_arrival}</td>
					<td>${date_departure}</td>
					<td>${cost}, $</td>
					<td>${status_order}</td>
					<td>${change_status}</td>
				</tr>
				<c:forEach var="order" items="${orderList}">
					<jsp:useBean id="order" class="com.epam.te.hotel.model.entity.Order" />
					<tr align="center">
						<ht:delta dateDeparture="${order.dateDeparture}" dateArrival="${order.dateArrival}" />
						<td><c:out value="${order.user.surname}" /></td>
						<td><c:out value="${order.apartment.roomNumber}" /></td>
						<td><c:out value="${order.apartment.category}" /></td>
						<td><c:out value="${order.dateOrder}" /></td>
						<td><c:out value="${order.dateArrival}" /></td>
						<td><c:out value="${order.dateDeparture}" /></td>
						<td><ht:totalcost delta="${delta}"
								costPerDay="${order.apartment.cost}" /></td>
						<td><c:out value="${order.status}" /></td>
						<td>
							<form action="${pageContext.request.contextPath}/Controller" method="post">
								<div class="col-md-10 col-xs-10">
									<select name="statusOrder" class="form-control">
										<option value="empty">
											${choose}
										</option>
										<option value="CONFIRMED">${confirmed}</option>
										<option value="AWAITING">${awaiting }</option>
										<option value="DENIED">${denied}</option>
									</select>
								</div>
								<div class="col-md-2 col-xs-2">
									<input type="hidden" name="command" value="set_status_order" /> 
									<input type="hidden" name="id_order" value="${order.id }" />
									<button class="btn  btn-sm  btn-success" type="submit">
										<span class="glyphicon glyphicon-ok"></span>
									</button>
								</div>
						   </form>
						</td>
					</tr>
				</c:forEach>
			</table>
	</div>
	
	<div class="footer" style="clear:both"></div>
	<footer>
		<jsp:include page="/jsp/common/footer.jsp" flush="true"/>
	</footer>
</body>
</html>