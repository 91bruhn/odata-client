<%@ page import="mynamespace.web.service.Checker" %>
<%--<html>--%>
<%--<body>--%>
<%--<h2>Hello World!</h2>--%>
<%--<a href="http://localhost:8080/flightDataManagement.svc/$metadata">OData Flight Search - MetaData</a>--%>

<%--<a href="http://localhost:8080/flightDataManagement.svc">OData Flight Search - Resources</a>--%>

<%--<a href="http://localhost:8080/flightDataManagement.svc/Flights">OData Flight Search - all Flights</a>--%>

<%--&lt;%&ndash;To see more use following postman access: &ndash;%&gt;--%>
<%--&lt;%&ndash;For a quick test - a few links, that might not work with chrome&ndash;%&gt;--%>
<%--</body>--%>
<%--</html>--%>
<%--<html>--%>
<%--<head>--%>
<%--<title>Expression language example2</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<form action="display.jsp">--%>
<%--Student Name: <input type="text" name="stuname"/><br>--%>
<%--Student RollNum:<input type="text" name="rollno"/><br>--%>
<%--<input type="submit" value="Submit Details!!"/>--%>
<%--pageContext.setAttribute("checker", new Checker());--%>
<%--Amount: ${checker.amount(7)}--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>
<html>
<head>
<title> User Login Page – Enter details</title>
</head>
<body>
<form action="validation.jsp">
Enter User-Id: <input type="text" name="uid"><br>
Enter Password: <input type="text" name="upass"><br>
<input type="submit" value="Login">
</form>
<%
pageContext.setAttribute("checker", new Checker());
%>
<%--SETZE dies im Servlet--%>
Amount: ${checker.getAmount(7)}

<a href="neu.jsp">Click here </a>
</body>
</html>