<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<title>setting</title>
</head>
<link rel="stylesheet" href="css/setting.css">
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>

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
                      </div>
				</div>
				</div>
		</header>
		<div class="setting_main">
			<h1>내 정보 수정</h1>
			<form id="form1" action="UploadActionServlet" method="post" enctype="multipart/form-data">
				<div class="userImgUpload">
				<c:choose>
					<c:when test="${member.file_name == null  || member.file_name eq ''}">
						<img id="userImg" src="images/mark.png" alt="user avatar" />
					</c:when>
					<c:otherwise>
						<img id="userImg" src="uploads/${member.file_name }" alt="user avatar" />
					</c:otherwise>		
				</c:choose>
					
					<div class="userImgUpload_Control">
						<label class="userImgUpload_label">이미지 선택
						 <input id="photoFile" name="photoFile" type="file" onchange="readURL(this);">
						</label> 
			</form>	 
						<form id="form2" action="RemoveImageServlet">
						
						<label class="userImgUpload_label" onclick="removeImage()">이미지
							제거
						</label>
			</form>

					</div>
				</div>
			
			<div class="setting_title">
				<h3>닉네임</h3>
				<form id="form3" action="UpdateNickNameServlet" method="post">
					<input type="text" name="nickNameInput" value="${member.nickname }">
				</form>	
			</div>
			<p class="setting_name">Hola에서 사용되는 이름입니다.</p>
			<hr>

			<button class="setting_complete setting_button" name="complete" 
				onclick="submitForms()">
					완료
			</button>
			

			<button class="setting_singout setting_button" onclick="showMsg()">회원탈퇴</button>
			<div>
				<!-- 아래 dialog 태그 영역이 메시지 창 -->
				<dialog id="myMsgDialog">
				
				<h4>Hola에서 계정을 삭제하시겠어요?</h4>
				<input type="button" id="mButton1" onclick="closeMsg()" value=" 아니요 "> 
				<a href="deleteForm"> 
					<input	type="button" id="mButton2" onclick="closeMsg()" value=" 네, 삭제할게요. ">
				</a>
				</dialog>
			</div>


		</div>
		


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
	<script src="js/result.js"></script>
	<script src="js/meun.js"></script>
	<script src="js/signout.js"></script>
	<script src="js/imgupload.js"></script>
	<script src="js/imgremove.js"></script>
	<script src="js/withdrawal.js"></script>
	<script type="text/javascript" src="js/form.js"></script>
</body>
</html>