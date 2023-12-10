package com.team2.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team2.dao.MemberDAO;
import com.team2.dto.MemberVO;

public class MypageAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = "/mypage/setting.jsp";
		MemberDAO mDao = MemberDAO.getInstance();
		
		HttpSession session = request.getSession(); 
		String sessionId = (String)session.getAttribute("id");
		MemberVO member = mDao.selectInfoByID(sessionId);
		
		request.setAttribute("member", member);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
