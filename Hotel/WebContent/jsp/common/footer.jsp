<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value = "${sessionScope.local}"/>
<fmt:setBundle basename="localization.locale" var="loc"/>
<fmt:message bundle="${loc }" key = "local.footer.back" var="back" />
<fmt:message bundle="${loc }" key = "local.footer.copyright" var="copyright" />

<!DOCTYPE html >

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/footer.css" />
</head>
<body>
	<div>
		<div>
			<p class="text-muted" align="center"><a href="#">${back}</a></p>
			<p class="text-muted" align="center"> ${copyright}</p>
		</div>
	</div>
</body>
</html>