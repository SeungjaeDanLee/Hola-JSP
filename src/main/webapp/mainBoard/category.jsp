<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach var ="board" items="${boardList }">
                    <a href="BoardSerlvet?commnad=main_view&num=${board.board_num }&currPage=${pageHandler.currPage }&searchType=${searchVO.searchType}&searchText=${searchVO.searchText}" class="mainList">
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

</body>
</html>