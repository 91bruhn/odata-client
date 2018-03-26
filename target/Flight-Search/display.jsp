<%--<html>--%>
<%--<head>--%>
<%--<title>Display Page</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--Student name is ${ param.stuname } <br>--%>
<%--Student Roll No is ${ param.rollno }--%>
<%--</body>--%>
<%--</html>--%>
<html>
<head>
<title>Displaying User Details</title>
</head>
<body>
<%
String username = (String) pageContext.getAttribute("UName", PageContext.SESSION_SCOPE);
String userpassword = (String) pageContext.getAttribute("UPassword", PageContext.SESSION_SCOPE);
out.println("Hi " + username);
out.println("Your Password is: " + userpassword);
%>
</body>
</html>