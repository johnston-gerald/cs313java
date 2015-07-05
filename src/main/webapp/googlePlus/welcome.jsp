<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Google Plus Login Using OAuth 2.0 in Java Servlets</title>
	<style type="text/css">
		.container {
			width:100%;
			background-color:white;
			text-align:center;
		}
		#auth-display-img {
			height:30px; 
			float:left;
		}
		
		#auth-display-name {
			height:30px; 
			float:left; 
			width:120px; 
			background-color:rgb(241,241,241);
			color:rgb(109,109,109); 
			font-family:tahoma; 
			font-weight:bold;
			font-size:12px; 
			line-height:30px;		
		}
		
		#auth-info {
			text-align:left;
			font-family:tahoma; 
			font-size:14px;
		}
		
		b {
			font-weight:normal;
			color:blue;
		}
		
		i {
			font-weight:bold;
			font-size:20px;
			text-transform:capitalize;
		}

		#logout {
			margin: 0px auto;
			width:80px;
			height:23px;
			background-color: blue; 
			color:white;
		}		
	</style>
</head>
<body>
	<div class="container">
		<h1>Google Plus Login Using OAuth 2.0 in Java Servlets</h1>
		
		<div id="auth-status">
			<div id="auth-loggedin" style="width:250px; margin:0px auto;">
				<div id="auth-display-img">
					<img style='height:100%;' src='${googlePlusUser.profilePicLink}'/>
				</div>
				<div id="auth-display-name">${googlePlusUser.fullname}</div><br/><br/>
				<div id="auth-info">
					<br/><br/>
					<b>Id: </b>${googlePlusUser.id}<br/>
					<b>Email: </b>${googlePlusUser.email}<br/>				
					<b>First Name: </b>${googlePlusUser.firstname}<br/>
					<b>Last Name: </b>${googlePlusUser.lastname}<br/>
					<b>Full Name: </b>${instagramUser.fullname}<br/>				
					<b>Google Plus Link: </b>${googlePlusUser.googlePlusLink}<br/>
					<b>Profile Pic Link: </b>${googlePlusUser.profilePicLink}<br/>
					<b>Gender: </b>${googlePlusUser.gender}<br/>
					<b>Birthday: </b>${googlePlusUser.birthday}<br/>
					<b>Language: </b>${googlePlusUser.language}<br/>
					<br/><br/>					
				</div>
				<br/>
				<a href="./logout">
					<div id="logout">
						Logout
					</div>
				</a>				
			</div>
		</div>
	</div>
</body>
</html>