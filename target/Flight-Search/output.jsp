<%@ page import="mynamespace.web.Checker" %>
<html>
<head>
<title>Ausgabe</title>
</head>
<body>
<%--<form action="validation.jsp">--%>
<%--Enter User-Id: <input type="text" name="uid"><br>--%>
<%--Enter Password: <input type="text" name="upass"><br>--%>
<%--<input type="submit" value="Login">--%>
<%--</form>--%>
<%--<%--%>
<%--pageContext.setAttribute("checker", new Checker());--%>
<%--%>--%>
<%--SETZE dies im Servlet--%>
<%--Amount: ${checker.getAmount(7)}--%>

<%--<a href="neu.jsp">Click here </a>--%>
<%
pageContext.setAttribute("checker", new Checker());
%>
<%--SETZE dies im Servlet--%>
<div class="container">
<table class="table table-hover">
<thead>
<tr>
<th>Firstname</th>
<th>Lastname</th>
<th>Email</th>
</tr>
</thead>
<tbody>
<tr>
<td>John</td>
<td>Doe</td>
<td>john@example.com</td>
</tr>
<tr>
<td>Mary</td>
<td>Moe</td>
<td>mary@example.com</td>
</tr>
<tr>
<td>July</td>
<td>Dooley</td>
<td>july@example.com</td>
</tr>

<tr>
<td>Mary</td>
<td>Moe</td>
<td>mary@example.com</td>
</tr>
<tr>
<td>${checker.flight}</td>
<td>${checker.flight}</td>
<td>${checker.flight}</td>
</tr>

<%--<tr>--%>

<%--<c:forEach items="${checker.flight}" var="col">--%>
<%--<td>Mary</td>--%>
<%--<td>${col}</td>--%>
<%--<td>Mary</td--%>
<%--<td>Mary</td>--%>
<%--<td>Mary</td>--%>
<%--<td>Mary</td>--%>
<%--</c:forEach>--%>
<%--</tr>--%>

<%--<c:forEach items="${list}" var="item">--%>
<%--${item}<br>--%>
<%--</c:forEach>--%>
</tbody>
</table>
</div>


</body>
</html>