<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.nagarro.dto.*" %>
<%@ page import="com.nagarro.service.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
			var msg = '<%=session.getAttribute("alert")%>
	'
	if (msg != "null") {
		function alertName() {
			alert(msg);
		}
	}
<%session.setAttribute("alert", "null");%>
	
</script>
<meta charset="ISO-8859-1">
<title>edit image</title>
</head>
<body>

	<%
		response.setHeader("Cache-Control", "no-cache , no-store , must-revalidate");
		int imageId = Integer.parseInt(request.getParameter("imageId"));
		request.getSession().setAttribute("imageId", imageId);
	%>

	<c:if test="${user != null}">

		<br>
		<br>
		<br>

		<fieldset>
			<legend> < Edit Image > </legend>
			<br>

			<fieldset>
				<legend> < Change Image Name > </legend>
				<br>

				<form action="EditImageServlet" method="get">

					<table>
						<tr>
							<td>Enter new name :</td>
							<td><input type="text" name="newName"> <input
								type="hidden" name="actionType" value="1"></td>
						</tr>
						<tr>
							<td><input type="submit" value="change"></td>
						</tr>
					</table>

				</form>
				<br>

			</fieldset>

			<br> <br>

			<fieldset>
				<legend> < Change Image Source > </legend>
				<br>

				<form action="EditImageServlet" method="post"
					enctype="multipart/form-data">
					Please select an image file to upload (Max size 5 MB) : <br> <br>
					<Input type="file" name="ifile" accept="image/png, image/jpeg">
					<br> <br> <Input type="submit" value="change">

				</form>
				<br> <br>

			</fieldset>
			<br>
		</fieldset>
	</c:if>

	<c:if test="${ user == null }">
		<c:redirect url="index.jsp" />

	</c:if>
</body>
<script type="text/javascript">
	window.onload = alertName
</script>
</html>