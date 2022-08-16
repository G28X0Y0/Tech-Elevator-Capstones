<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<ul>
       		<jsp:useBean id="now" class="java.util.Date" />
            <li class="footer">&copy; <fmt:formatDate value="${now}" pattern="yyyy"/></li>
        

</ul>
</body>
</html>