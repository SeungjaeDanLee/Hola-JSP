package com.team2.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team2.dao.BoardDAO;
import com.team2.dto.BoardVO;

public class BoardUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardVO bVo = new BoardVO();
		
		bVo.setBoard_num(Integer.parseInt(request.getParameter("board_num")));
		
//		bVo.setRecruit_num(Integer.parseInt(request.getParameter("recruit_num")));
//		bVo.setStartdate(request.getParameter("startdate"));
//		bVo.setContact(request.getParameter("contact"));
//		bVo.setTitle(request.getParameter("title"));
		bVo.setContent(request.getParameter("content"));
		
		BoardDAO bDao = BoardDAO.getInstance();
		bDao.updateBoard(bVo);
		
		// boardList.jsp로 이동
		new BoardListAction().execute(request, response);
	}
}
