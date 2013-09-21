<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>


<link rel="stylesheet" type="text/css" href="../css/style1.css">

<script type="text/javascript">
	var Pic = new Array()
	Pic[0] = 'http://www.hdwallpapers.in/walls/balloon_flight-wide.jpg'
	Pic[1] = 'http://www.hdwallpapers.in/walls/great_landscape-HD.jpg'
	Pic[2] = 'http://www.hdwallpapers.in/walls/pinky_night-wide.jpg'
	Pic[3] = 'http://www.hdwallpapers.in/walls/creation-HD.jpg'
	Pic[4] = 'http://www.hdwallpapers.in/walls/full_moon-wide.jpg'
	Pic[5] = 'http://www.hdwallpapers.in/walls/san_francisco_skyline-wide.jpg'
	Pic[6] = 'http://www.hdwallpapers.in/walls/twilight_cook_inlet_alaska-wide.jpg'

	var p = Pic.length
	var imgLoad = new Array()

	for (i = 0; i < p; i++) {
		imgLoad[i] = new Image()
		imgLoad[i].src = Pic[i]
	}

	window.onload = function setImg() {
			var j = Math.floor(Math.random() * 8)
			document.body.background = Pic[j]
	}
</script>
</head>

<body>
	
	
			
			<form action="login.do" width=0 method="post" id="slick-login">
	<table border="1">
		<tr>
			<td class="tab_center">
				<table width="200" height="150" class="loginform cf">
					<tr>
						<td align="center" style="padding-top:10px; padding-left:10px; padding-bottom:10px; padding-right:10px;">
							 <table style=opacity:0.65 width="250" height="0" border="1" class="tb">
							<div id = "a">
							<p>ZERO</p>
							<form action="login.do" method="post">
								<table>
							 		<tr>
										 <td align="center">
											<input type="email" name="email" 
											value=''
											placeholder="Email">
											
											<input type="password" name="password" placeholder="Password">
											<input type="submit" name="login" value="Log in" style='cursor:pointer;'>
											<h7>　</h7>
											<center>
											<input type="checkbox" name="saveId"><font color="white">Remember me?</font>　<br>
											<a href="#">Forgot password?</a>
											</center>
											<h>　</h>
										</td>
									</tr>
								</table>
							</form>	
							</div>
							<form action="../member/signinForm.do">
							
								 <table>
										<tr>
											<td>
												<input type="submit" value="Sign up" style='cursor:pointer;'>
											</td>
										</tr>
									</table>
							</form>
							</table>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>	
			
			
		
</body>





</html>







