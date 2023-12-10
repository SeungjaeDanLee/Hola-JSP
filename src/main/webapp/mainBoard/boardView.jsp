<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="css/hola_page_view_Style.css">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/main_after.css">
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<!--  <script type="text/javascript" src="js/board.js"></script>-->
<script>
//팝업창 열기
function open_win(url, name) {
	window.open(url, name, "width=500, heiht=230");
}
	// 댓글 수정
	function fnCommentUpdate(commentNum) {
		var $tr = $(".comment_list").find(".commentTr" + commentNum);
		var name = $tr.children("td").eq(0).text().trim();
		var content = $tr.children("td").eq(1).text().trim();
		
		$form = $("form[name=commentFrm]");
		$form.find("#commentName").val(name);
		$form.find("[name=commentContent]").val(content);
		$form.find("[name=command]").val("comment_update");
		$form.find("[name=commentNum]").val(commentNum);
		$form.find("#commentBtn").text("[댓글 수정]");
	}
	
	// 삭제 버튼 클릭 시 비밀번호 입력 창 toggle
	function showPassword(commentNum) {
		$("#inputPassword" + commentNum).toggle();
		$("#deletePass" + commentNum).focus();
	}
	
	// 댓글 삭제
	function fnCommentDelete(commentNum) {
		if($("deletePass" + commentNum) == "" || $("#deletePass" + commentNum).val().length <= 0) {
			alert("비밀번호를 입력해주세요.");
			$("#deletePass" + commentNum).focus();
			return false;
		}
		
		if(confirm("정말 삭제하시겠습니까?")) {
			var $form = $("form[name=commentDeleteFrm" + commentNum + "]");
			$form.find("[name=command]").val("comment_delete");
			
			$.ajax({
				type: "post",
				url: "BoardServlet?command=comment_check_pass",
				data: {"commentNum": $form.find("[name=commentNum]").val(),
					   "deletePass": $form.find("#deletePass" + commentNum).val()	
				},
				success: function(data) {
					var retData = data.trim();
					if(retData == "true") {		// 비밀번호가 맞는 경우
						// 삭제 진행
						$form.submit();
					} else {					// 비밀번호가 맞지 않는 경우
						alert("비밀번호가 맞지 않습니다.");
					}
				},
				error: function() {
					console.log("서버 요청실패");
				}
			});
		} else {
			return false;
		}
	}
	
	// 댓글 등록
	function fnRegComment() {
		if($("#commentName").val() == "" || $("#commentName").val().length <= 0) {
			alert("이름을 입력해주세요.");
			$("#commentName").focus();
			return false;
		}
		
		if($("#commentPass").val() == "" || $("#commentPass").val().length <= 0) {
			alert("비밀번호를 입력해주세요.");
			$("#commentPass").focus();
			return false;
		}
		
		var command = $("form[name=commentFrm]").find("[name=command]").val();
		if(command == "comment_write") {
			return true;
		} else if (command == "comment_update") {
			var commentNum = $("form[name=commentFrm]").find("[name=commentNum]").val();
			$.ajax({
				type: "post",
				url: "BoardServlet?command=comment_check_pass",
				data: {"commentNum": commentNum,
					   "deletePass": $("#commentPass").val()	
				},
				success: function(data) {
					var retData = data.trim();
					if(retData == "true") {		// 비밀번호가 맞는 경우
						// 수정 진행
						return true;
					} else {					// 비밀번호가 맞지 않는 경우
						alert("비밀번호가 맞지 않습니다.");
						return false;
					}
				},
				error: function() {
					console.log("서버 요청실패");
				}
			});
		} else {
			return false;
		}		
	}
</script>
</head>
<body>
<body>
<div id="root">
     <header class="header">
            <a href="http://localhost:8080/JSP_Project_Team2/BoardServlet?command=board_list" class="logo">
                <img src="images/hola_logo_y.png" alt="logo" class="logo">
            </a>
        <c:choose>
            	<c:when test = "${not empty sessionScope.id}">
            <div class="header_right">
                <button class="write">새 글 쓰기</button>
    
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


    <div class="Select_all">
      <section>
        <div class="select_text_v01">
          <h2 class="select_text">${board.title }</h2>
        </div>

        <div class="studyContent_title_info">
          <div class="studyContent_user_ifo_all">
            <!-- <img class="studyContent_userImg_info" src="images/default.PNG" alt="userImg"> -->
            <div class="studyContent_userName_info">${board.writer }</div>
           
            <div class="studyContent_registeredDate"><fmt:formatDate value="${board.regdate }"/></div>
          </div>
        </div>
      </section>
    </div>

    <section class="info_board">
      <ul class="studyInfo_studyGrid_list">
        <!-- 모집구분 -->
        <li class="studyInfo_contentWrapper_list">
          <span class="studyInfo_title_text1">모집 구분</span>
          <span class="studyInfo_title_text2">${board.category }</span>
        </li>

        <!-- 진행방식 -->
        <li class="studyInfo_contentWrapper_list">
          <span class="studyInfo_title_text1">모집 인원</span>
          <span class="studyInfo_title_text2">${board.recruit_num}</span>
        </li>

        <!-- 모집인원 -->
        <li class="studyInfo_contentWrapper_list">
          <span class="studyInfo_title_text1">시작예정</span>
          <span class="studyInfo_title_text2">${board.startdate }</span>
        </li>

        <!-- 시작예정 -->
        <li class="studyInfo_contentWrapper_list">
          <span class="studyInfo_title_text1">연락 방법</span>
          <span class="studyInfo_title_text2">${board.contact }</span>
        </li>

        <!-- 연락방법 -->
        

        
      </ul>
    </section>

    <div class="Select_all">
      <section>
        <div class="select_text_v02">
          <h2 class="select_text">프로젝트 소개</h2>
        </div>

        <div class="studyContent_div_2">
        </div>

        <!-- 게시판 내용 -->
        <div class="postContent">
          <p><pre>${board.content }</pre></p>
        </div>
      </section>



      <!-- 조회수/북마크 -->
      
        
        <!-- 게시물 수정삭제 -->
			
       <section class="commentButtons_buttonWrappe">
      		<button class="commentButtons_buttons"  value="수정" 
	      		onclick="location.href='BoardServlet?command=board_updateForm&board_num=${board.board_num}'">수정
	      		</button>
			
            <button class="commentButtons_buttons"  value="삭제" 
	            onclick= "location.href='BoardServlet?command=board_delete', 'delete'">
	            	삭제</button>
	           </section>
			
          </section> 
          
          
          
          

        

        
        </li>
      </section>
    </div>

</div>
</body>
</html>













