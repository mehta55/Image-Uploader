<html>
	<head>
		<script type="text/javascript">
			var msg = '<%= session.getAttribute("alert") %>'
			if(msg != "null") {
				function alertName() {
					alert(msg);
				}
			}
			<% session.setAttribute("alert", "null"); %>
		</script>
		<title>Image Uploader</title>
	</head>
	<body>
		<fieldset>
		<legend> < Sign In > </legend>
			<form action="SignIn" method = "post"> <br>
				<table>
					<tr>
						<td>User ID  :</td>
						<td><input type = "text" name = "uid"></td>
					</tr>
					<tr>
						<td>Password :</td>
						<td><input type = "password" name = "pswd"> <br></td>
					</tr>
					<tr>
						<th></th>
						<td><input type = "submit" value = "Sign In" align = "right"></td>
				</table>
		
		</form>
		</fieldset>
		<br>
		<br>
		<fieldset>
		<legend> < Sign Up > </legend>
			<form action="SignUp" method = "post"> <br>
			<table>
					<tr>
						<td>Set User ID  :</td>
						<td><input type = "text" name = "uid"></td>
					</tr>
					<tr>
						<td>Set Password :</td>
						<td><input type = "password" name = "pswd"> <br></td>
					</tr>
					<tr>
						<td>Confirm Password :</td>
						<td><input type = "password" name = "cpswd"> <br></td>
					</tr>
					<tr>
						<th></th>
						<td><input type = "submit" value = "Sign Up" align = "right"></td>
				</table>
		</form>
		</fieldset>
	</body>
	<script type="text/javascript"> window.onload = alertName </script>
</html>
