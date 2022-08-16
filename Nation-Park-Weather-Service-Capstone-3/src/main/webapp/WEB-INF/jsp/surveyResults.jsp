<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="common/header.jsp" %>

<div class="main-content">


<div class="results">
		<h1 class="surveyHeadline">Survey Results</h1>
	<div class="resultsLabel">
		<div class="nameLabel">Park</div>
		<div class="voteLabel">Favorite Votes</div>
	</div>
<c:forEach var="result" items="${surveyResults}">
	<div class="resultsData">
		<div class="resultsName">${result.name}</div>
		<div class="resultsVotes">${result.favorite}</div>
	</div>
</c:forEach>





</div>




</div>

<%@include file="common/footer.jsp" %>