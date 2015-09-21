<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<fmt:setLocale value = "${sessionScope.local}"/>
<fmt:setBundle basename="localization.locale" var="loc"/>

<fmt:message bundle="${loc }" key = "local.room" var="room" />
<fmt:message bundle="${loc }" key = "local.room.lux.view" var="lux_view" />
<fmt:message bundle="${loc }" key = "local.room.lux.data" var="lux_data" />
<fmt:message bundle="${loc }" key = "local.room.standart.view" var="standart_view" />
<fmt:message bundle="${loc }" key = "local.room.h3" var="h3" />
<fmt:message bundle="${loc }" key = "local.room.info1" var="article_1" />
<fmt:message bundle="${loc }" key = "local.room.info2" var="article_2" />
<fmt:message bundle="${loc }" key = "local.room.info3" var="article_3" />

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<title>${room}</title>
</head>
<body>
	<jsp:include page="/jsp/common/header_common.jsp" flush="true" />
	
	<div class="container">
		<h2 style="text-align:center; margin-bottom: 2%; margin-top: 0%">${room}</h2>
		<div class="carousel slide" id="myCarousel" data-ride="carousel">
				<ol class="carousel-indicators">
					<li class="active" data-target="#myCarousel" data-slide-to="0"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
					<li data-target="#myCarousel" data-slide-to="3"></li>
					<li data-target="#myCarousel" data-slide-to="4"></li>
					<li data-target="#myCarousel" data-slide-to="5"></li>
				</ol>
				<div class="carousel-inner" role="listbox">
					<div class="item active">
						<img src="${pageContext.request.contextPath}/img/room/room_1.jpg" alt="LUX">
						<div class="carousel-caption">
					        <h3>Lux</h3>
					        <p>${lux_view}</p>
				     	 </div>				     	
					</div>
					<div class="item">
						<img src="${pageContext.request.contextPath}/img/room/room_2.jpg">
						  <div class="carousel-caption">
						        <h3>Lux</h3>
						        <p>${lux_view}</p>
				     	 </div>
					</div>
					
					<div class="item">
						<img src="${pageContext.request.contextPath}/img/room/room_7.jpg">
						<div class="carousel-caption">
						        <h3>Lux</h3>
						        <p>${lux_view}</p>
				     	 </div>
					</div>

					<div class="item">
						<img src="${pageContext.request.contextPath}/img/room/room_3.jpg">
						<div class="carousel-caption">
						        <h3>Business</h3>
						        <p>${lux_view}</p>
				     	 </div>
					</div>
					<div class="item">
						<img src="${pageContext.request.contextPath}/img/room/room_4.jpg">
						 <div class="carousel-caption">
						        <h3>Standart</h3>
						        <p>${lux_view}</p>
				     	 </div>
					</div>
					
					<div class="item">
						<img src="${pageContext.request.contextPath}/img/room/room_6.jpg">
						<div class="carousel-caption">
						        <h3>Standart</h3>
						        <p>${standart_view}</p>
				     	 </div>
					</div>

					<div class="item">
						<img src="${pageContext.request.contextPath}/img/room/room_dmv_1.jpg">
						<div class="carousel-caption">
						        <h3>Standart</h3>
						        <p>${standart_view}</p>
				     	 </div>
					</div>
					
					<div class="item">
						<img src="${pageContext.request.contextPath}/img/room/room_5.jpg">
						<div class="carousel-caption">
						        <h3>Econom</h3>
						        <p>${standart_view}</p>
				     	 </div>
					</div>

					<div class="item">
						<img src="${pageContext.request.contextPath}/img/room/room_dmv_2.jpg">
						<div class="carousel-caption">
						        <h3>Econom</h3>
						        <p>${standart_view}</p>
				     	 </div>
					</div>
					<div class="item">
						<img src="${pageContext.request.contextPath}/img/room/room_dmv_3.jpg">
						  <div class="carousel-caption">
						        <h3>Econom</h3>
						        <p>${standart_view}</p>
				     	 </div>
					</div>
				</div>
				
				<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
				    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				    <span class="sr-only">Previous</span>
			  </a>
			  <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
				    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				    <span class="sr-only">Next</span>
			  </a>
				
			</div>
			
			<div class="container" id="article">
				<h3 id ="article_h3">${h3}</h3>
				<p class="txt" align="justify" > ${article_1}</p>	
				<p class="txt" align="justify" > ${article_2}</p>	
				<p class="txt" align="justify" > ${article_3}</p>		
			</div>
	</div>
	
	<div class="clear"></div>
    <footer >
   		 <jsp:include page="/jsp/common/footer.jsp" flush="true" />
    </footer>
	
</body>
</html>