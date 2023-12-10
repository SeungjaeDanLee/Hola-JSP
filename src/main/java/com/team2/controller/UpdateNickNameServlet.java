package com.team2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team2.dao.MemberDAO;

@WebServlet("/UpdateNickNameServlet")
public class UpdateNickNameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession();
		
        String memberId = (String) session.getAttribute("id");; 
        String newNickname = request.getParameter("nickNameInput");

        MemberDAO memberDAO = MemberDAO.getInstance();
        memberDAO.updateNicknameInDB(memberId, newNickname);

        String url = "mypage/setting.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
    }
}