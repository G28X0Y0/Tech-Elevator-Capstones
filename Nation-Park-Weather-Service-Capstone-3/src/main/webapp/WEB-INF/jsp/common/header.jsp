<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta charset="UTF-8">
<title>National Park Geek</title>
<c:url value="/css/site.css" var="cssURL" />
<link rel="stylesheet" type="text/css" href="${cssURL}">
</head>
<body>

	<c:url value="/img/logo.png" var="logoURL" />
	<img src="${logoURL}" class="logo"/>

<ul>
	<li>
		<c:url value="/" var="homeURL"/>	
		<a href="${homeURL}">Home</a>
	</li>
	<li>
		<c:url value="/survey" var="surveyURL"/>
		<a href="${surveyURL}">Survey</a>
	</li>	
</ul>



	