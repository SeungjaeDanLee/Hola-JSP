<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/main_after.css">
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script src="js/meun.js"></script>
<script src="js/main.js"></script>
<title>메인페이지</title>
</head>
<body>
<body>
    <div class="root">
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
                      <a href="logout" id="logout" style="font-weight: bold">로그아웃</a>
                        <!-- <button onclick="location.href='join/logout.jsp';" class="logoutbtn" style="border: none;background: white;font-weight: 900;font-size: 15px;">로그아웃</button> -->
                    </div>
                </div>
                
                </div>
            </c:when>
            <c:otherwise>
            	<div class="header_right">
               <button  type="button"  class="login" onclick="location.href='http://localhost:8080/JSP_Project_Team2/loginForm'">로그인</button>
            </div>
            </c:otherwise>
            </c:choose>
            </header>
        <div class="slidebox">
            <input type="radio" name="slide" id="slide01" checked>
            <input type="radio" name="slide" id="slide02">
            <input type="radio" name="slide" id="slide03">
            <input type="radio" name="slide" id="slide04">
            <ul class="slidelist" style="height: 314px;">
                <li class="slideitem" style="bottom: 175px;">
                    <a><img src="images/banner1.png" ></a>
                </li>
                <li class="slideitem" style="bottom: 175px;">
                    <a><img src="images/banner2.png"></a>
                </li>
                <li class="slideitem" style="bottom: 175px;">
                    <a><img src="images/banner3.png"></a>
                </li>
                <li class="slideitem" style="bottom: 175px;">
                    <a><img src="images/slide04.jpg"></a>
                </li>
            </ul>
            <div class="slide-control">
                <div class="control01">
                    <label for="slide04" class="prev"></label>
                    <label for="slide02" class="next"></label>
                </div>
                <div class="control02">
                    <label for="slide01" class="prev"></label>
                    <label for="slide03" class="next"></label>
                </div>
                <div class="control03">
                    <label for="slide02" class="prev"></label>
                    <label for="slide04" class="next"></label>
                </div>
                <div class="control04">
                    <label for="slide03" class="prev"></label>
                    <label for="slide01" class="next"></label>
                </div>
            </div>
        </div>
        <section class="tabContainer" style="
    margin-bottom: 0px;
">
            <ul class="tab1">
                <li class="one clicked"  id="all">전체</li>
                <li class="one" id="culture" style = "color : black"; >문화.예술</li>
                <li class="one" id="sport"  style = "color : black"; >운동.액티비티</li>
                <li class="one" id="food"  style = "color : black"; >푸드.드링크</li>
                <li class="one" id="trip" style = "color : black"; >여행.동행</li>
                <li class="one" id="study"  style = "color : black"; >성장.자기계발</li>
            </ul>
                <div class="linecolor" style="
                background-color: gold;
                height: 5px;
                width: 85px;
                position: relative;
                bottom: 37px;
                "></div>
            
        </section>
        <main class="main">
            <div class="mainWrapper">
                <section class="mainWrapperL">
                    <div class="mainTitle">
                        <img src="images/a.png" alt="" class="mainTitleLogo">
                        <span class="mainTitle">나와 같은 관심사인 친구들과 함께해요!</span>
                    </div>
                </section>
                <div class="mainWrapperR">
                    <article class="cont-select">
                        <ul class="list-member" style="
                        background-color: white;
                    ">
                            <li><button type="button">백엔드</button></li>
                            <li><button type="button">프론트엔드</button></li>
                            <li><button type="button">IOS</button></li>
                            <li><button type="button">안드로이드</button></li>
                            <li><button type="button">디자인</button></li>
                        </ul>
                    </article>
                    
                </div>
            </div>
            <div class="mainContainer">
                <ul class="mainList">
                <c:forEach var ="board" items="${boardList }">
                    <a href="BoardServlet?command=board_view&board_num=${board.board_num }" class="mainList">
                        <li>
                             <div class="mainListHead">
                                  <div class="head1">
                                    <div class="head1-1">
                                        ✏️ 스터디
                                    </div>
                                  </div>
                                   <div class="head2">
                                    <div class="head2-1">
                                        🍞 따끈따끈 새 글
                                    </div>
                                   </div>
                                <div class="like" style="margin-left: 10px; margin-top: 2px;" >
                                    <img src="images/like.png" alt=""  id ="like" onclick="changeHeartImage()" class="like" style="
                                    width: 25px; ;
                                ">
                                </div>
                             </div>
                             <div class="mainListDate">
                                <p class="date">시작일 |</p>
                                <p class="date">${board.startdate }</p>
                             </div>
                             <h1 class="mainListTitle" style="
                             font-size: 18px;
                             color: black; margin-bottom: 30px">
                             ${board.title }
                             </h1>
                             <ul class="category">
                                 <li class="category">
                                    ${board.category }
                                 </li>
                             </ul>
                             <ul class="cateMark">
                                <li class="cateMark" style="margin-bottom: 20px;">
                                    <img src="images/java.svg" alt="" style="width: 35px;">
                                </li>
                             </ul>
                             <div class="line"></div>
                             <section class="user">
                                <div class="userName">
                                    <div class="userMark">
                                        <img src="images/mark.png" alt="" class="userMark" style="width: 30px; height: 30px;">
                                    </div>
                                    <div><p style="color: black;margin-top: 6px;"> ${board.writer}</p></div>
                                </div>
                                <div class="contentinfo">
                                    <div class="contentcount">
                                        <img src="images/count.png" alt=""  style="width: 25px; height: 25px;">
                                        <p>${board.readCount }</p>
                                    </div>
                                    <div class="contentcomment">
                                        <img src="images/comment.png" alt=""  style="width: 25px; height: 25px;">
                                        <p>0</p>
                                    </div>
                                </div>
                             </section>
                        </li>
                    </a>
                    </c:forEach>
                </ul>
            </div>
        </main>
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