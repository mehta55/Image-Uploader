<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.nagarro.dto.*"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
		var msg = "<%=session.getAttribute("alert")%>
	"
	if (msg != "null") {
		function alertName() {
			alert(msg);
		}
	}
<%session.setAttribute("alert", "null");%>
	
</script>
<meta charset="ISO-8859-1">
<title>Admin Space</title>
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache , no-store , must-revalidate");
	%>


	<c:if test="${ user == 'admin' }">
			Welcome ${ user } !
			<br>
		<br>
		<fieldset>
			<legend> Delete Users </legend>
			<br>

			<table width="100%">
				<tr>
					<td>
						<form action="DeleteUser">
							Delete User Id : <input type="text" name="uid"> <input
								type="submit" value="Delete"> <br>
						</form>
					</td>
					<td align="right">
						<form action="SignOut">
							<input type="submit" value="Sign Out">
						</form>
					</td>
				</tr>
			</table>

			<br>
		</fieldset>
	</c:if>

</body>
<script type="text/javascript">
	window.onload = alertName
</script>

</html>