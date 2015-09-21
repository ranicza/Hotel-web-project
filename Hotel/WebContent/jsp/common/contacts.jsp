<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<fmt:setLocale value = "${sessionScope.local}"/>
<fmt:setBundle basename="localization.locale" var="loc"/>

<fmt:message bundle="${loc }" key = "local.contacts" var="contacts" />
<fmt:message bundle="${loc }" key = "local.contacts.header" var="contacts_header" />
<fmt:message bundle="${loc }" key = "local.contacts.contact" var="contact" />
<fmt:message bundle="${loc }" key = "local.contacts.tel" var="tel" />
<fmt:message bundle="${loc }" key = "local.contacts.email" var="email" />
 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />

<title>${contacts}</title>
</head>
<body>

	<jsp:include page="/jsp/common/header_common.jsp" flush="true" />
	
	<div class="container">
		<div class="container"  style="aling: center">
			 <div class = "row" >		 	
			 	 	 <h3 style="text-align: center">${contacts_header}</h3>
	 			 	 <p> Calle Belgica s/n</p>
					 <p> 07108 Port de Sóller</p>
					 <p> Mallorca, Spain</p>
			 </div>
			 
			  <div class = "row">
			  		<p id="contact" >${contact}</p>
			  		<p >${tel} +34 971 637 888</p>
			  		<p>${email} jps@jumeirah.com ,  jpsreservations@jumeirah.com</p>
			  </div>
			
		</div>
	</div>
	
	<div class="clear"></div>
    <footer >
   		 <jsp:include page="/jsp/common/footer.jsp" flush="true" />
    </footer>
</body>
</html>