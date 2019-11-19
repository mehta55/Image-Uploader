<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.nagarro.dto.User"%>
<%@ page import="com.nagarro.dto.Image"%>
<%@ page import="com.nagarro.service.*"%>
<%@ page import="java.util.*"%>
    
<!DOCTYPE html>
<html>
	<head>
		<script type="text/javascript">
		var msg = "<%= session.getAttribute("alert") %>"
		if(msg != "null" ) {
			function alertName() {
				alert(msg);
			}
		}
		<% session.setAttribute("alert", "null"); %>
		</script>
		<meta charset="ISO-8859-1">
		<title>Welcome</title>
	</head>
	
	<body>
	<% response.setHeader("Cache-Control" , "no-cache , no-store , must-revalidate"); %>

	<c:if test="${ user != null }">
	
		<c:set var = "userObject" value = "${Factory.getUserObject(user)}" />

		<table style = "width:100%">
			<tr>
				<td> Welcome ${user} !</td>
				<td>
					<form action = "SignOut" align = "right">
						<input type = "submit" value = "Sign Out" >
					</form>
				</td>
			</tr>
		</table>

		<br>
		<br>
		
		Total Space : 20 Mb <br>
		Used  Space : ${userObject.getTotalSize()} Mb <br>
		
		<br>
		<br>

		<fieldset>
		   <legend> < Upload new Image > </legend> <br>

			<form action = "ImageUploader" method = "post" enctype="multipart/form-data">
				Please select an image file to upload (Max size 5 MB) : <br> <br>
				<Input type = "file" name = "ifile" accept="image/png, image/jpeg"> <br> <br>
					<Input type = "submit" value = "upload">
			</form>

		</fieldset>
		<br>
		<br>
		
		<c:set var = "uploadedImages" value = "${userObject.getUploadedImages()}" /> 
		<c:if test="${uploadedImages.size() > 0 }">

			<fieldset>
			<legend> < Uploaded Images > </legend>
			<br>

			<table style=" width:100%; " cellpadding="15" cellspacing="0" border="1"  >				
				<tr>
					<th align = "center">S.No</th>
					<th align = "center">Name</th>
					<th align = "center">Size</th>
					<th align = "center">Preview</th>
					<th align = "center">Actions</th>
				</tr> 				
				<c:forEach var = "imageNo" begin = "1" end = "${uploadedImages.size()}">
					<c:set var = "image" value = "${uploadedImages.get(imageNo - 1)}"/>					
					<tr>
					<td align = "center" > ${imageNo} </td>
					<td align = "center"> ${image.getIname()} </td>
					<td align = "center">  ${image.getIsize()} Mb</td>
					<td align = "center" style="padding:10px"><img src = ${ image.getIpath()} alt = "not found" height="200" width="300" ></td>
					<td align = "center"> 
						
						<form action = "DeleteImageServlet" method = "get">
							<input type = "hidden" name = "imageId" value = "${image.getImageId() }">
							<input type = "submit" value = "delete" >
						</form>
						
						&nbsp; &nbsp; 
						
						<form action = "edit.jsp" method = "get">
							<input type = "hidden" name = "imageId" value = "${image.getImageId() }">
							<input type = "submit" value = "edit">
						</form>	 
						 <br>
					 </td>
				</tr>
				
				</c:forEach>
			</table>
			</fieldset>
		</c:if>
	</c:if>
	
	<c:if test="${ user == null }">
		<c:redirect url = "index.jsp" /> 
		
	</c:if>
	
	</body>
	
	<script type="text/javascript"> window.onload = alertName </script>

</html>