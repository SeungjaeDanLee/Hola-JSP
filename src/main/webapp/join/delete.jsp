<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<link rel="stylesheet" href="css/delete.css">

<script src="https://code.jquery.com/jquery-3.7.0.js"
	integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM="
	crossorigin="anonymous"></script>
</head>
<body>
 <div id="root">
        <header class="header">
            <a href="http://localhost:8080/JSP_Project_Team2/BoardServlet?command=board_list" class="logo">
                <img src="images/hola_logo_y.png" alt="logo" class="logo">
            </a>
            <div class="header_right">
                <button class="write" onclick="location.href='http://localhost:8080/JSP_Project_Team2/BoardServlet?command=board_writeForm'">새 글 쓰기</button>
    		
    		
                <div class="meun_dropdown">
                    <button onclick="meunFunction()" class="meun_dropbtn">
                        <img src="images/mark.png" alt="bell" style="width: 28px;">
                    </button>
                    <div id="meun_myDropdown" class="meun_dropdown-content">
                        <a href="MypageServlet?command=member_list" style="font-weight: bold">설정</a>
                      <a href="logout" id="logout" style="font-weight: bold">로그아웃</a>
                        <!-- <button onclick="location.href='join/logout.jsp';" class="logoutbtn" style="border: none;background: white;font-weight: 900;font-size: 15px;">로그아웃</button> -->
                    </div>
                </div>
                </div>
        </header>
		
		<section class="login_section">
		<header class="auth-board__head"><h2 class="auth-board__h">회원탈퇴</h2></header>
			<form action="deleteDB" method="post">
				<p><input id="id" type="text" name="id" class="input"></p>
				<p><input id="pw" type="password" name="pw" class="input"></p>
				<p>
					<input class="log_btn" type="submit" value="회원탈퇴">
				</p>
				</form>
		</section>
<footer class="footer">
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