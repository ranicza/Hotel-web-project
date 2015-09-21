<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib uri="/WEB-INF/tld/hotel.tld" prefix="ht"%>

<fmt:setLocale value = "${sessionScope.local}"/>
<fmt:setBundle basename="localization.locale" var="loc"/>
<fmt:message bundle="${loc }" key = "local.home" var="home" />
<fmt:message bundle="${loc }" key = "local.index.cabinet" var="cabinet" />
<fmt:message bundle="${loc }" key = "local.index.logout.exit" var="logout" />
<fmt:message bundle="${loc }" key = "local.room.administration.roomnumber" var="room_number" />
<fmt:message bundle="${loc }" key = "local.room.administration.positions" var="positions" />
<fmt:message bundle="${loc }" key = "local.room.administration.cost" var="cost" />
<fmt:message bundle="${loc }" key = "local.room.administration.category" var="category" />
<fmt:message bundle="${loc }" key = "local.room.administration.status" var="status" />
<fmt:message bundle="${loc }" key = "local.admin.add.title" var="title" />
<fmt:message bundle="${loc }" key = "local.admin.add" var="add" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form-validator.js"></script>
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
			<div class="pull-center">
				<form  action="${pageContext.request.contextPath}/Controller" method = "post" >
					<p  style="font-size: 150%">${title}</p>	
					<table class="table table-striped" style="text-align:center">						
						<tr class="info" id = "table-header">	
							<td>${room_number}</td>
							<td>${category}</td>
							<td>${positions}</td>
							<td>${cost}, $</td>			
						</tr>
						
						<tr align="center">
							<td>
								<div class ="form-group">
									<input class="form-control  required" size="5"  type="text" 
										required name="roomNumber"
										data-validation="custom"
										data-validation-regexp="^[0-9]{1,5}$"
									 	value="${apartment.roomNumber}"/>
				 	 	 		</div>
							</td>
							
							<td>
								<select  name="category" class="form-control"  style="width: 90%" >
									<option >
										<c:out value="${apartment.category}" />
									 </option>
									<c:if test="${apartment.category ne 'LUX'}">
									   <option value="LUX">LUX</option>
									</c:if>
									<c:if test="${apartment.category ne 'BUSINESS'}">
									   <option value="BUSINESS">BUSINESS</option>
									</c:if> 
									 <c:if test="${apartment.category ne 'STANDART'}">
									   <option value="STANDART">STANDART</option>
									</c:if>
									<c:if test="${apartment.category ne 'ECONOM'}">
									   <option value="ECONOM">ECONOM</option>
									</c:if>
								</select> 
							</td>
							
							<td>
								<select name="positions" class="form-control"  style="width: 100%" >
									 <option >
										<c:out value="${apartment.positions}" />
									 </option>
									 <c:if test="${apartment.positions ne '1'}">
									   <option>1</option>
									 </c:if>
									 <c:if test="${apartment.positions ne '2'}">
									   <option>2</option>
									 </c:if>
									 <c:if test="${apartment.positions ne '3'}">
									   <option>3</option>
									 </c:if>
									  <c:if test="${apartment.positions ne '4'}">
									   <option>4</option>
									 </c:if>
									 <c:if test="${apartment.positions ne '5'}">
									   <option>5</option>
									 </c:if>
								</select> 
							</td>
							
							<td>
								<div class ="form-group">
									<input class="form-control required" size="5" type="text" required name="cost" 
										data-validation="custom"
										data-validation-regexp="^[0-9]{1,7}$"
										value="${apartment.cost}"/>
				 	 	 		</div>
							</td>
						</tr>
					</table>
				<div id="center">
					<input type="hidden" name="command" value="add_apartment"/>	
					<input type="submit" id = "btn_apply" class="btn btn-sm  btn-success" value="${add}"/><br/>	
				</div>					
				</form>
		  </div>
	</div>

	<div class="footer" style="clear:both"></div>
	<footer>
		<jsp:include page="/jsp/common/footer.jsp" flush="true"/>
	</footer>
	
	<script>
		$.validate();
	</script>
</body>
</html>