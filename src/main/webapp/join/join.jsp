<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="css/join.css">
<script src="js/join.js"></script>
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
            <div class="header_right">
                <button  type="button"  class="login" onclick="location.href='http://localhost:8080/JSP_Project_Team2/loginForm'">로그인</button>
            </div>
        </header>
	<section class="register_section">
	<form class="register_form" action="joinDB" name="member" method="post">
			<h1>회원가입</h1>
			${message }
		<fieldset>
			<div class="input_style">
				<input class="input" type="text" name="user-id" minlength="4" maxlength="12" placeholder="아이디"
					required>
				<input class = "btn_doubleck" type="button" value="확인"
					onclick="checkId();">
			</div>
		</fieldset>
		<span id="result1"></span>
		<fieldset>
			<div class="input_style">
				<input class="input" type="password" name="user-pw" minlength="4" maxlength="12"
					placeholder="비밀번호" required>
			</div>
		</fieldset>
		<div></div>
		<fieldset>
			<div class="input_style">
				<input class="input" name="user-pwck" type="password" minlength="4"
					maxlength="12" placeholder="비밀번호 확인" required>
			</div>
		</fieldset>
		<fieldset>
			<div class="input_style">
				<input class="input" type="email" name="user-email" minlength="5"
					placeholder="이메일" required>
			</div>
		</fieldset>
		<fieldset>
			<div class="input_style">
				<input class="input" type="text" name="user-name" minlength="2" placeholder="이름"
					required>
			</div>
		</fieldset>
		<fieldset>
			<div class="input_style">
				<input class="input" type="text" name="user-nickname" minlength="2"
					placeholder="닉네임" required>
				<input  class = "btn_doubleck" type="button"
					value="확인" onclick="checkNickName();">
			</div>
		</fieldset>
		<span id="result2"></span>
		<fieldset>
			<div class="input_style">
				<input class="input" type="tel" name="user-phone" pattern="[0-9]{11}"
					title="숫자로만 이루어진 11자리를 입력해주세요. (하이픈 제외)"
					placeholder="전화번호 - 빼고 11자리 입력" required>
			</div>
		</fieldset>
		<fieldset>
				<input class="reg_btn" type="submit" value="회원가입" onclick="return checkForm(); ">
		</fieldset>
		<fieldset>
			<p>
				이미 가입된 계정이 있나요?
				<a href="loginForm" style="text-decoration: none; color: black; font-weight: bold; margin-left: 10px;">로그인 하기</a>
			</p>
		</fieldset>
	</form>
	</section>
	<footer class="footer">
            <div class="footerleft">
                <img src="images/footer.png" alt="" class="logo" style="margin-bottom: 20px;width: 60px;height: 15px;">
                <p style="font-size: 13px;">Contact team.hola.official@gmail.com</p>
                <p style="font-size: 13px;">Copyright Hola. All rights reserved</p>
            </div>
            <div class="footerright"></div>
        </footer>
        </div>
</body>
</html>