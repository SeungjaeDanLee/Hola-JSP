<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="css/login.css">

<script src="https://code.jquery.com/jquery-3.7.0.js"
	integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM="
	crossorigin="anonymous"></script>
</head>
<body>
	<div class="root">
		<header class="header">
			<a href="http://localhost:8080/JSP_Project_Team2/BoardServlet?command=board_list" class="logo">
                <img src="images/hola_logo_y.png" alt="logo" class="logo">
            </a>
		</header>
		
		
		<section class="login_section">
		<header class="auth-board__head"><h2 class="auth-board__h">로그인</h2></header>
			<form class="login_form" action="loginDB"
				method="post">
				<p>
					<input id="id" type="text" name="id" class="input" minlength="4" placeholder="아이디" required="">
				</p>
				<p>
					<input id="pw" type="password" name="pw" class="input" minlength="4" placeholder="비밀번호" required="">
				</p>
				<p>
					<input class="log_btn" type="submit" value="로그인">
				</p>
				<p>
					처음이신가요?<a href="join"  style="text-decoration: none;color: black;font-weight: bold; margin-left: 10px;
									">회원가입</a>
				</p>
			</form>
		</section>
		
		
		<footer class="footer" style="
    margin-top: 179px;
">
			<div class="footerleft">
				<img src="images/footer.png" alt="" class="logo"
					style="margin-bottom: 20px; width: 60px; height: 15px;">
				<p style="font-size: 13px;">Contact team.hola.official@gmail.com</p>
				<p style="font-size: 13px;">Copyright Hola. All rights reserved</p>
			</div>
			<div class="footerright"></div>
		</footer>
	</div>
</body>
</html>