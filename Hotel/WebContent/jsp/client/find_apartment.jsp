<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tld/hotel.tld" prefix="ht"%>


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
<fmt:message bundle="${loc }" key = "local.find" var="find" />

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

		<!-- Creating order -->
		<p style="font-size: 150%; text-align: center">${title}</p>	
		<form action="${pageContext.request.contextPath}/Controller" method="post">
			<div class = "col-md-3 col-xs-3" ></div>
			<div class = "col-md-3 col-xs-3" >
				
			 	 	 	<div class ="form-group">
			 	 	 		<label for = "date_arrival">${date_arrival}</label>
			 	 	 		<input class="form-control" type="date" name="date_arrival" size="40">
			 	 	 	</div>
	
						<div class ="form-group">
			 	 	 		<label>${positions}</label>
			 	 	 		<select class="form-control" name = "positions">
								 <option>1</option>
								 <option>2</option>
								 <option>3</option>
								 <option>4</option>
								 <option>5</option>
							</select>
			 	 	 	</div>
			</div>
			<div class = "col-md-3 col-xs-3" >
				<div class ="form-group">
			 	 	 	<label	for="date_departure">${date_departure}</label>
			 	 	 	<input class="form-control" type="date" name="date_departure"  >
			 	 	 	
			 	</div >
			 	
			 	<div class ="form-group">
			 	 	 		<label>${category}</label>
			 	 	 		<select class="form-control" name="category">
			 	 	 			 <option>ANY</option>
								 <option>LUX</option>
								 <option>BUSINESS</option>
								 <option>STANDART</option>
								 <option>ECONOM</option>
							</select>
			 	 </div>
			 </div>
			 	 <div class = "col-md-3 col-xs-3" ></div>
			 	 <hr />
			<div style="clear: both" id = "center">
				<input type="hidden" name="command" value="find_apartment"/>
			 	 <button class="btn btn-sm btn-primary" type="submit" >
				 	 <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
				 	 ${find}
			 	 </button>
			</div>
		</form>
	</div>


	
	
	
	
	<div class="clear"></div>
    <footer >
   		 <jsp:include page="/jsp/common/footer.jsp" flush="true" />
    </footer>

</body>
</html>