<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="common/header.jsp" %>

<div class="main-content">

	<c:forEach var="park" items="${parks}">
	
		<div class="park">
			<div class="parkIMG">
				<c:url value="/parkDetail" var="parkDetailURL">
					<c:param name="code" value="${park.code}"/>
				</c:url>	
				<a href="${parkDetailURL}">
					<c:url value="/img/parks/${park.imgCode}.jpg" var="parkURL" />
					<img src="${parkURL}" />
				</a>
			</div>
			<div class="parkDESC">
				<a href="${parkDetailURL}"><h1><c:out value="${park.name}"/></h1></a>
				<h3><c:out value="${park.state}"/></h3>
				<span class="regular-text">${park.description}</span>
			</div>	
		</div>
	</c:forEach>
</div>


<%@include file="common/footer.jsp" %>
