<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<%@include file="common/header.jsp" %>


<c:url value="/img/parks/${park.imgCode}.jpg" var="bigParkIMG"/>
<img src="${bigParkIMG}" class="bigParkIMG" />
<span class="parkName"><c:out value="${park.name}" /></span>
<div class="parkDetail">
	<div class="parkData">
		<h3>State</h3><span class="regular-text"><c:out value="${park.state}" /></span>
		<h3>Acreage</h3><span class="regular-text"><c:out value="${park.acreage}" /></span>
		<h3>Elevations</h3><span class="regular-text"><c:out value="${park.elevation}" /></span>
		<h3>Miles of Trials</h3><span class="regular-text"><c:out value="${park.milesOfTrail}" /></span>
		<h3>Number of Campsites</h3><span class="regular-text"><c:out value="${park.campsites}" /></span>
		<h3>Climate</h3><span class="regular-text"><c:out value="${park.climate}" /></span>
	</div>
	<div class="parkDescription">
		<h3>Description</h3><span class="regular-text"><c:out value="${park.description}" /></span><br /><br />
		<em>"<c:out value="${park.quote}" />"</em>&nbsp;&nbsp;&nbsp; <span class="regular-text">- <c:out value="${park.quoteSource}" /></span><br />
		<h3>Year Founded</h3><span class="regular-text"><c:out value="${park.yearFounded}" /></span>
		<h3>Visitor Count</h3><span class="regular-text"><c:out value="${park.visitorCount}" /></span>
		<h3>Fee</h3><span class="regular-text"><c:out value="${park.fee}" /></span>
		<h3>Number of Species</h3><span class="regular-text"><c:out value="${park.species}" /></span>
	</div>

</div>
<div class="weather">
<c:forEach var="day" items="${forecast}">
	<div class="weatherday">
	<c:if test="${day.forecastDay == 1}">
		<h1>Today</h1>
		
	<c:url value="/parkDetail?code=${param.code}" var="celsiusURL">
		<c:param name="temp" value="C"/>
	</c:url>
	<c:url value="/parkDetail?code=${param.code}" var="fahrenheitURL">
		<c:param name="temp" value="F"/>
	</c:url>
	Change Temperature Type:<a href="${celsiusURL}">C</a> | <a href="${fahrenheitURL}">F</a>
	</c:if>
	<br />
	<br />
	<c:choose>
		<c:when test="${day.forecast == 'partly cloudy'}">
			<c:url value="/img/weather/partlyCloudy.png" var="forecastIMG" />
		</c:when>
		<c:otherwise>
			<c:url value="/img/weather/${day.forecast}.png" var="forecastIMG" /> 
		</c:otherwise>
	</c:choose>	
	<img src="${forecastIMG}" />
	
	<br />
	<h3>High </h3><c:out value="${day.high}" /> <c:out value="${day.temp}" />
	<h3>Low </h3><c:out value="${day.low}" /> <c:out value="${day.temp}" />
	<br />
	<br />
	<span class="regular-text"><c:out value="${day.advisory}" /></span>
	<br />
	<br />
	</div>
</c:forEach>
</div>

<%@include file="common/footer.jsp" %>