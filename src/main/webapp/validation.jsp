<html>
<head><title> Validation JSP Page</title>
</head>
<body>
<%
String flightDate = request.getParameter("uid");
String pass = request.getParameter("upass");
out.println("hello " + flightDate);
pageContext.setAttribute("UName", flightDate, PageContext.SESSION_SCOPE);
pageContext.setAttribute("UPassword", pass, PageContext.SESSION_SCOPE);
%>
<a href="display.jsp">Click here to see what you have entered </a>
</body>
</html>