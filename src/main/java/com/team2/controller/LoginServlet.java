package com.team2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team2.dao.MemberDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginDB")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paramId = request.getParameter("id");
        String paramPw = request.getParameter("pw");

        MemberDAO memberDAO = MemberDAO.getInstance();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        // 로그인 성공 여부 check(true - 로그인 성공)
        boolean isLogin = false;
		
        try {
        	conn = MemberDAO.getConnection();

            // SQL 쿼리 실행
            String sql = "SELECT id, pw FROM member WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, paramId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
                String dbId = rs.getString("id");
                String dbPw = rs.getString("pw");
                if (paramId.equals(dbId) && paramPw.equals(dbPw)) {
                    isLogin = true;
                } else {
                    isLogin = false;
                }
            } else {
                isLogin = false;
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
            
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append("<!doctype html><html><head><title>Login</title>");
		out.append("</head><body>");
		if(isLogin) {
			out.append("<p>로그인에 성공했습니다.</p>");
			HttpSession session = request.getSession();
			session.setAttribute("id", paramId);
			response.sendRedirect("http://localhost:8080/JSP_Project_Team2/BoardServlet?command=board_list");
		} else {
			out.append("<p>로그인에 실패했습니다.</p>");
			out.append("<p><button onclick =\"location.href='loginForm'\">" + "로그인</button></p>");
		}
		out.append("</body></html>");
		
		//String resultUrl = "/servlet/loginForm_process02.jsp";
		
	}

}
