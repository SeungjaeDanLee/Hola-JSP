<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 성공</title>
	<link rel="stylesheet" href="css/main.css">
</head>
<body>
<div class="root">
<header class="header">
            <a href="main.html" class="logo">
                <img src="images/hola_logo_y.png" alt="logo" class="logo">
            </a>
            <div class="header_right">
                <button class="write">새 글 쓰기</button>
                <button class="login">로그인</button>
            </div>
        </header>
	<jsp:useBean id="member" class="com.team2.dto.MemberVO" scope="session" />
	<jsp:setProperty name="member" property="*" />
	<div class="register_success">
	<%response.sendRedirect("loginForm");%>
	
	<h3>회원가입에 성공하셨습니다.</h3>
		<p> 아 이 디 : ${selectMember.id } </p>
		<p> 비밀번호 : ${selectMember.pw }</p>
		<p> 가입시간 : ${selectMember.regdate }</p>
		<p> 전화번호 : ${selectMember.phone }</p>
		<p> 닉네임 : ${selectMember.nickname }</p>
		<p> 이메일 : ${selectMember.email }</p>
		<p> 이름 : ${selectMember.name }</p>
		<button onclick="location.href='login.jsp';">로그인 하기</button>
		</div>
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