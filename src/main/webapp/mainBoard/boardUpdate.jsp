<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="css/hola_page_write_stylesheet.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" href="css/main_after.css">
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>

</head>



<body>

<input type="hidden" name="command" value="board_write">						
<div id="root">


    <header class="header">
            <a href="http://localhost:8080/JSP_Project_Team2/BoardServlet?command=board_list" class="logo">
                <img src="images/hola_logo_y.png" alt="logo" class="logo">
            </a>
        <c:choose>
            	<c:when test = "${not empty sessionScope.id}">
            <div class="header_right">
                <button class="write" onclick="location.href='http://localhost:8080/JSP_Project_Team2/BoardServlet?command=board_writeForm'">새 글 쓰기</button>
    
                <div class="meun_dropdown">
                    <button onclick="meunFunction()" class="meun_dropbtn">
                        <img src="images/mark.png" alt="bell" style="width: 28px;">
                    </button>
                    <div id="meun_myDropdown" class="meun_dropdown-content">
                        <a href="MypageServlet?command=member_list" style="font-weight: bold">설정</a>
                      <a href="join/logout.jsp" id="logout" style="font-weight: bold">로그아웃</a>
                        <!-- <button onclick="location.href='join/logout.jsp';" class="logoutbtn" style="border: none;background: white;font-weight: 900;font-size: 15px;">로그아웃</button> -->
                    </div>
                </div>
                
                </div>
            </c:when>
            <c:otherwise>
            	<div class="header_right">
                <button class="write">새 글 쓰기</button>
               <button  type="button"  class="login" onclick="location.href='join/login.jsp'">로그인</button>
            </div>
            </c:otherwise>
            </c:choose>
            </header>

</div>
	<form name="frm" method="post" action="BoardServlet">
		<input type="hidden" name="command" value="board_update">
		<input type="hidden" name="board_num" value="${board.board_num }">
    <div class="Select_all">
      <section style="
    margin-top: 100px;
">
        <div class="select_text_v01">
          <span class="select_text_span">1</span>
          <h2 class="select_text">프로젝트 기본 정보를 입력 해주세요.</h2>
    </div>
    <ul>
        <li>
            <h3 style="
    margin-bottom: 20px;
">모집구분</h3>
                  <select id="pop" name="pop" disabled>
                    <option value="">전체</option>
                    <option value="문화.예술">문화.예술</option>
                    <option value="운동.액티비티">운동.액티비티</option>
                    <option value="푸드.드링크">푸드.드링크</option>
                    <option value="성장.자기개발">성장.자기개발</option>
                  </select>
        </li>
          
        <li>
          <h3 style="
    margin-bottom: 20px;
">모집인원</h3>
          <input type="text" id="population" name="recruit_num" placeholder="모집인원 수" value="${board.recruit_num }"disabled>
      </li>
    </ul>

    <ul>
      <li>
          <h3 style="
    margin-bottom: 20px;
">시작예정</h3>
                  	<input type="date" name="startdate" value="${board.startdate }"disabled>
      </li>
        
      <li>
        <h3 style="
    margin-bottom: 20px;
">연락방법</h3>
        <input type="text" id="population" name="contact" placeholder="ex) 오픈채팅,구글폼,등" value="${board.contact }" disabled>
    </li>
  </ul>
  <ul>
</ul>
<ul>
</ul>

<div class="select_text_v02">
  <span class="select_text_span">2</span>
  <h2 class="select_text">프로젝트에 대해 소개해주세요</h2>
</div>

<ul>
  <li>
      <h3>제목</h3>
        <input type="text" name="title" placeholder="제목을 입력해주세요" class="title" value="${board.title }" disabled>
        
  </li>
 </ul>

 <ul>
  <li>
        <input type="text" name="content" placeholder="내용을 입력해주세요" class="content_box" value="${board.content }" >
  </li>
 </ul>

 <ul>
  <li style="
    margin-top: 10px;">
        <input type="submit" value="등록"  style="width: 800px;" >

			
       
  </li>
 </ul> 
 
<!-- "return boardCheck()" -->
</section>
</div>
</form>
</body>
</html>







