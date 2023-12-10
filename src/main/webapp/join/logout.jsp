<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
           <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃 처리</title>
</head>
<body>
	<% session.invalidate(); %>                        
	<script>
		alert("로그아웃 되었습니다.");
		       // 로그아웃 페이지로 이동
	</script>
<%response.sendRedirect("BoardServlet?command=board_list");%>
</body>
</html>