<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value = "${sessionScope.local}"/>
<fmt:setBundle basename="localization.locale" var="loc"/>
<fmt:message bundle="${loc }" key = "local.locbutton.name.ru" var="ru_button" />
<fmt:message bundle="${loc }" key = "local.locbutton.name.en" var="en_button" />
<fmt:message bundle="${loc }" key = "local.hotel.name" var="hotel_name" />
<fmt:message bundle="${loc }" key = "local.home" var="home" />
<fmt:message bundle="${loc }" key = "local.room" var="room" />
<fmt:message bundle="${loc }" key = "local.contacts" var="contact" />
<fmt:message bundle="${loc }" key = "local.log.in" var="log_in" />
<fmt:message bundle="${loc }" key = "local.registration" var="registr" />
<fmt:message bundle="${loc }" key = "local.index.hotelname" var="hotelname" />
<fmt:message bundle="${loc }" key = "local.index.article.text.header" var="article_header" />
<fmt:message bundle="${loc }" key = "local.index.article.text.context_1" var="article_content_1" />
<fmt:message bundle="${loc }" key = "local.index.article.text.context_2" var="article_content_2" />
<fmt:message bundle="${loc }" key = "local.index.article.text.context_3" var="article_content_3" />
<fmt:message bundle="${loc }" key = "local.index.article.text.context_4" var="article_content_4" />
<fmt:message bundle="${loc }" key = "local.index.cabinet" var="cabinet" />
<fmt:message bundle="${loc }" key = "local.index.logout" var="logout" />

<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/disable-f5.js"></script>
	
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${hotel_name}</title>

</head>
<body onload="JavaScript:document.body.focus();" onkeydown="return showKeyCode(event)">
	<div class="navbar navbar-default ">
	  	<div class="container-fluid">
	  		<div class="col-md-10 col-xs-10">
		  		<div class="navbar-header">
			     	 <a class="navbar-brand" href="#"><img src="${pageContext.request.contextPath}/img/logo.png" width="98" height="34"></a>
			    </div>
			    <div>
				      <ul class="nav navbar-nav" id="bar-bottom">
					        <li><a href="${pageContext.request.contextPath}/index.jsp">${home}</a></li>
					        <li><a href="${pageContext.request.contextPath}/jsp/common/room.jsp">${room}</a></li>
					        <li><a href="${pageContext.request.contextPath}/jsp/common/contacts.jsp">${contact}</a></li> 
					        <li>		
						        <form class="navbar-form pull-right" action="${pageContext.request.contextPath}/Controller" method="post">
										<input type="hidden" name="command" value="language">
										<input type="hidden" name="local" value="ru">
										<input class="btn btn-info btn-sm" id="btn-lang-ru" type="submit" name="rus" value = "${ru_button }"/>
								</form>
								<form class="navbar-form pull-right" action="${pageContext.request.contextPath}/Controller" method="post">
										<input type="hidden" name="command" value="language">
										<input type="hidden" name="local" value="en">
										<input class="btn  btn-info btn-sm" id="btn-lang-en" type="submit" name="eng" value = "${en_button }"/>						
								</form>
							</li>
					   </ul>
				    </div>		
				</div>
			    <div class="col-md-2 col-xs-2" style="border: none;">
				     <div id= "choose" style="width: 100% ;">
					    	<c:choose>
					    		<c:when test="${not empty user}" >
					    		<div id="active_user">
									<ol class="breadcrumb">
										<c:if test="${user.role eq 'ADMIN'}">
											<li><a href="${pageContext.request.contextPath}/jsp/admin/admin.jsp">${cabinet}</a></li>
										</c:if>
										<c:if test="${user.role eq 'CLIENT'}">
											<li><a href="${pageContext.request.contextPath}/jsp/client/client_cabinet.jsp">${cabinet}</a></li>
										</c:if>
										<li><a href="${pageContext.request.contextPath}/jsp/common/logout.jsp">${logout}</a></li>
									</ol>
								</div>
								</c:when>
								
								<c:otherwise>
									<div id="active_guest">
										<ol class="breadcrumb">
											<li><a href="${pageContext.request.contextPath}/jsp/common/login.jsp" >	${log_in}</a></li>
											<li><a href="${pageContext.request.contextPath}/jsp/common/registration.jsp">${registr}</a></li>
										</ol>
									</div>	
								</c:otherwise>
					    	</c:choose>
		    	    </div>
			   </div>
   		 </div>
	</div>

	<div class="container" style="height: 100%">
	
			<c:if test="${not empty errorMessage }">
				<div class="alert alert-danger">${errorMessage}</div>
			</c:if>
			<c:if test="${not empty actionMessage }">
				<div class="alert alert-success">${actionMessage }</div>
			</c:if>
			
			
		<h2 id="hotelname">${hotelname}</h2>
		
		<div id="article">
			<div class="carousel slide" id="myCarousel">
				<ol class="carousel-indicators">
					<li class="active" id="indicator1"></li>
					<li id="indicator2"></li>
					<li id="indicator3"></li>
					<li id="indicator4"></li>
					<li id="indicator5"></li>
					<li id="indicator5"></li>
				</ol>
				<div class="carousel-inner" role="listbox">
					<div class="item active">
						<img src="${pageContext.request.contextPath}/img/view/view_1.jpg">
					</div>
					<div class="item">
						<img src="${pageContext.request.contextPath}/img/view/view_2.jpg">
					</div>

					<div class="item">
						<img src="${pageContext.request.contextPath}/img/view/view_3.jpg">
					</div>
					<div class="item">
						<img src="${pageContext.request.contextPath}/img/view/view_4.jpg">
					</div>
					<div class="item">
						<img src="${pageContext.request.contextPath}/img/view/view_5.jpg">
					</div>
					<div class="item">
						<img src="${pageContext.request.contextPath}/img/view/view_6.jpg">
					</div>
					<div class="item">
						<img src="${pageContext.request.contextPath}/img/view/view_7.jpg">
					</div>
				</div>
				
				<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
				    <span class="glyphicon glyphicon-chevron-left"></span>
				    <span class="sr-only">Previous</span>
			   </a>
			   <a class="right carousel-control" href="#myCarousel"  role="button" data-slide="next">
				    <span class="glyphicon glyphicon-chevron-right"></span>
				    <span class="sr-only">Next</span>
			   </a>
			</div>

			<div class="container" id="article">
				<h3 id ="article_h3">${article_header}</h3>
				<p class="txt" align="justify" > ${article_content_1}</p>
				<p class="txt" align="justify"> ${article_content_2}</p>
				<p class="txt" align="justify"> ${article_content_3}</p>
				<p class="txt" align="justify"> ${article_content_4}</p>			
			</div>
	</div>

	</div>
	
	<div class="clear"></div>
    <footer>
   		 <jsp:include page="/jsp/common/footer.jsp" flush="true" />
    </footer>
</body>
	
</body>
</html>