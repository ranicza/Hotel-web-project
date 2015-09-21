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
<fmt:message bundle="${loc }" key = "local.client.bill.title" var="title" />
<fmt:message bundle="${loc }" key = "local.client.bill.bills" var="bills" />
<fmt:message bundle="${loc }" key = "local.client.bill.status" var="status" />
<fmt:message bundle="${loc }" key = "local.client.bill.billDate" var="billDate" />
<fmt:message bundle="${loc }" key = "local.client.bill.totalprice" var="totalprice" />
<fmt:message bundle="${loc }" key = "local.client.bill.nroom" var="nroom" />
<fmt:message bundle="${loc }" key = "local.client.bill.dateArrival" var="dateArrival" />
<fmt:message bundle="${loc }" key = "local.client.bill.dateDeparture" var="dateDeparture" />
<fmt:message bundle="${loc }" key = "local.client.bill.payment" var="payment" />
<fmt:message bundle="${loc }" key = "local.room.administration.action" var="action" />


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/disable-f5.js"></script>

<title>${title}</title>
</head>
<body onload="JavaScript:document.body.focus();" onkeydown="return showKeyCode(event)">
	<jsp:include page="/jsp/common/header_for_user.jsp" flush="true" />

	<!-- Bill info -->
	<div class="container" >
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
	
		<p style="font-size: 150%">${bills}</p>
		<table class="table table-striped" style="align:center">		
			<tr class="info" id = "table-header" align="center">
				<td>${status}</td>
				<td>${totalprice}, $</td>
				<td>${billDate}</td>
				<td>${nroom}</td>
				<td>${dateArrival}</td>
				<td>${dateDeparture}</td>
				<td>${action}</td>
			</tr>
			<c:forEach var="bill" items="${clientBill}">
				<jsp:useBean id="bill" class="com.epam.te.hotel.model.entity.Bill" />
				<tr align="center">
				
				  <c:choose> 
				    <c:when test="${bill.status eq 'UNPAID'}">
				       <td style="font-weight: bold"><c:out value="${bill.status}"  /></td>
				    </c:when>
				    <c:otherwise>
				       <td ><c:out value="${bill.status}"  /></td>
				    </c:otherwise>
				  </c:choose>

					<td><c:out value="${bill.totalPrice}" /></td>
					<td><c:out value="${bill.dateBill}" /></td>
					<td><c:out value="${bill.order.apartment.roomNumber }" /></td>
					<td><c:out value="${bill.order.dateArrival}" /></td>
					<td><c:out value="${bill.order.dateDeparture}" /></td>
					
				<c:choose> 
					  <c:when test="${bill.status eq 'UNPAID'}">
					    <td>
							<form action="${pageContext.request.contextPath}/Controller" method="post">
								<input type="hidden" name="command" value="payment" />
								 <input	type="hidden" name="id_bill" value="${bill.id }" />
								<button class="btn btn-sm btn-warning" type="submit">
									${payment}
								</button>
							</form>
						</td>
					  </c:when>
					  <c:otherwise>
					    <td></td>
					  </c:otherwise>
				</c:choose>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<div class="clear"></div>
    <footer >
   		 <jsp:include page="/jsp/common/footer.jsp" flush="true" />
    </footer>
</body>
</html>