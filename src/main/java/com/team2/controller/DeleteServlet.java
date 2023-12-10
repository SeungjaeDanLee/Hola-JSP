package com.team2.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team2.dao.MemberDAO;
import com.team2.dto.MemberVO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/deleteDB")
public class DeleteServlet extends HttpServlet {
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
		
		
		
		String id = request.getParameter("id");
		String passwd = request.getParameter("pw");
		
		MemberDAO mDao = MemberDAO.getInstance();
		MemberVO mVo = mDao.selectInfoByID(id);
		
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!doctype html><html><head><title>Login</title>");
		out.println("</head><body>");
		// 폼에서 받아온 id, passwd 값과 DB에서 가져온 id, passwd 값과 일치 여부 확인
		if(id.equals(mVo.getId()) && passwd.equals(mVo.getPw())) {
			mDao.deleteMember(id);
			out.println("<script>");
			out.println("alert(\"탈퇴 완료\");");
			out.println("location.href=\"BoardServlet?command=board_list\";");
			out.println("</script>");
			HttpSession session = request.getSession();
            session.invalidate();
		}  else {
			out.println("<script>");
			out.println("alert(\"아이디 또는 비밀번호가 일치하지 않습니다.\");");
			out.println("location.href=\"MypageServlet?command=member_list\";");
			out.println("</script>");
		}
		out.println("</body></html>");
	}
}
