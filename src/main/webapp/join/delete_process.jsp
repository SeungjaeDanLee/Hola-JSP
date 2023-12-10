	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DB update</title>
</head>
<body>
	<%@ include file="dbconn.jsp" %>
	<%
		String id = request.getParameter("id");
		String passwd = request.getParameter("pw");
		
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try{
			String sql = "select id, pw from member where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				String rsId = rs.getString("id");
				String rsPasswd = rs.getString("pw");
				
				// 폼에서 받아온 id, passwd 값과 DB에서 가져온 id, passwd 값과 일치 여부 확인
				if(id.equals(rsId) && passwd.equals(rsPasswd)) {
					sql = "DELETE FROM member" + 
						  " WHERE id= ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					
	%>				
					
				<script>
					alert("탈퇴 완료");
					location.href="main.jsp";
				</script>
					
	<%	
				} else {
					out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
				}
			} else {
				out.println("Member 테이블에 일치하는 아이디가 없습니다.");
			}
				
		} catch (SQLException ex) {
			out.println("SQLException : " + ex.getMessage());
		} finally {
			if (rs != null){
				rs.close();
			}
			if (pstmt != null){
				pstmt.close();
			}
			if (conn != null){
				conn.close();
			}
		}
	%>
</body>
</html>