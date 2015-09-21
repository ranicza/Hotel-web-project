<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib uri="/WEB-INF/tld/hotel.tld" prefix="ht"%>

<fmt:setLocale value = "${sessionScope.local}"/>
<fmt:setBundle basename="localization.locale" var="loc"/>
<fmt:message bundle="${loc }" key = "local.home" var="home" />
<fmt:message bundle="${loc }" key = "local.index.logout.exit" var="logout" />
<fmt:message bundle="${loc }" key = "local.user.administration.title" var="title" />
<fmt:message bundle="${loc }" key = "local.client.page.return" var="back_client_page" />
<fmt:message bundle="${loc }" key = "local.user.administration.page" var="administration" />
<fmt:message bundle="${loc }" key = "local.name" var="name" />
<fmt:message bundle="${loc }" key = "local.surname" var="surname" />
<fmt:message bundle="${loc }" key = "local.patronymic" var="patronymic" />
<fmt:message bundle="${loc }" key = "local.passport" var="passport" />
<fmt:message bundle="${loc }" key = "local.login" var="login" />
<fmt:message bundle="${loc }" key = "local.email" var="email" />
<fmt:message bundle="${loc }" key = "local.user.administration.role" var="role" />
<fmt:message bundle="${loc }" key = "local.room.administration.action" var="action" />
<fmt:message bundle="${loc }" key = "local.room.administration.empty" var="choose" />
<fmt:message bundle="${loc }" key = "local.user.administration.change.role" var="change_role" />

<!DOCTYPE html >
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
		
		<!-- Room administration -->
		<div class="pull-center">
			<p style="font-size: 150%">${administration}</p>
			<table class="table table-striped" style="align:center">		
				<tr class="info" id = "table-header">
					<td>${name}</td>
					<td>${surname}</td>
					<td>${patronymic}</td>
					<td>${passport}</td>
					<td>${login}</td>
					<td>${email}</td>
					<td>${role}</td>
					<td>${change_role}</td>
				</tr>
				<c:forEach var="user" items="${userList}">
					<jsp:useBean id="user" class="com.epam.te.hotel.model.entity.User" />
					<tr align="center">
						<td><c:out value="${user.name}" /></td>
						<td><c:out value="${user.surname}" /></td>
						<td><c:out value="${user.patronymic}" /></td>
						<td><c:out value="${user.passport}" /></td>
						<td><c:out value="${user.login}" /></td>
						<td><c:out value="${user.email}" /></td>
						<td><c:out value="${user.role}" /></td>
						<td><form action="${pageContext.request.contextPath}/Controller" method="post">
							<div class="col-md-10 col-xs-10">
								<select name="role" class="form-control"  style="width: 78%">
									<option value="empty">
										${choose}
									</option>
									<option value="GUEST">GUEST</option>
									<option value="CLIENT">CLIENT</option>
									<option value="ADMIN">ADMIN</option>
								</select> <input type="hidden" name="command" value="change_role" />
							</div>
							<div class="col-md-2 col-xs-2">
								<input type="hidden" name="id_user" value="${user.id }" />
								<button type="submit" class="btn  btn-sm btn-success">							
								<span class="glyphicon glyphicon-ok"></span>
								</button>
							</div>
						</form></td>
					</tr>
				</c:forEach>
			</table>
	</div>
	</div>

	<div class="footer" style="clear:both"></div>
	<footer>
		<jsp:include page="/jsp/common/footer.jsp" flush="true"/>
	</footer>
</body>
</html>