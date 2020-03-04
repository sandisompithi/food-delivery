<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
>
<title>New Customer</title>
<link href="${contextPath}/resources/css/style.css" rel="stylesheet">

</head>
<body>
	<form:form method="POST" modelAttribute="user">
		<h2>Create an account</h2>
		<spring:bind path="firstname">
			<div>
				<form:input type="text" path="firstname" class="form-control"
					placeholder="FirsName" autofocus="true"></form:input>
			</div>
		</spring:bind>
		<spring:bind path="surname">
			<div>
				<form:input type="text" path="surname" class="form-control"
					placeholder="Surname" autofocus="true"></form:input>
			</div>
		</spring:bind>
		<spring:bind path="username">
			<div>
				<form:input type="text" path="username" class="form-control"
					placeholder="Username" autofocus="true"></form:input>
			</div>
		</spring:bind>
		<spring:bind path="password">
			<div>
				<form:input type="password" path="password" class="form-control"
					placeholder="Password"></form:input>
			</div>
		</spring:bind>
		<spring:bind path="cellphone">
			<div>
				<form:input type="cellphone" path="cellphone" class="form-control"
					placeholder="Cellphone"></form:input>
			</div>
		</spring:bind>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
	</form:form>
</body>
</html>