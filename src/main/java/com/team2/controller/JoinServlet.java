package com.team2.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team2.dao.MemberDAO;
import com.team2.dto.MemberVO;
import com.mysql.cj.exceptions.MysqlErrorNumbers;

import util.DBManager;

@WebServlet("/joinDB")
public class JoinServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("user-id");
        String pw = request.getParameter("user-pw");
        String phone = request.getParameter("user-phone");
        String nickname = request.getParameter("user-nickname");
        String email = request.getParameter("user-email");
        String name = request.getParameter("user-name");

        MemberVO mVo = new MemberVO();
        mVo.setId(id);
        mVo.setPw(pw);
        mVo.setPhone(phone);
        mVo.setNickname(nickname);
        mVo.setEmail(email);
        mVo.setName(name);

        MemberDAO mDao = MemberDAO.getInstance();
        int result = mDao.insertMember(mVo);

        String resultUrl = "join/join_process.jsp";
        if (result != 1) {
            request.setAttribute("message", "회원가입이 실패했습니다.");
            resultUrl = "join/join.jsp";
        }

        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        MemberVO selectMember = new MemberVO();

        try {
        	conn = MemberDAO.getConnection();

            String selectSql = "SELECT * FROM member WHERE id = ?";
            pstmt = conn.prepareStatement(selectSql);
            pstmt.setString(1, mVo.getId());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                selectMember.setId(rs.getString("id"));
                selectMember.setPw(rs.getString("pw"));
                selectMember.setRegdate(rs.getDate("regdate"));
                selectMember.setPhone(rs.getString("phone"));
                selectMember.setNickname(rs.getString("nickname"));
                selectMember.setEmail(rs.getString("email"));
                selectMember.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == MysqlErrorNumbers.ER_DUP_ENTRY) {
                System.out.println("DuplicateKeyException : " + e.getMessage());
                request.setAttribute("message", "회원가입이 실패했습니다.");
                resultUrl = "join/join.jsp";
            } else {
                System.out.println("Member 테이블 조회 실패했습니다.");
                System.out.println("Exception : " + e.getMessage());
                request.setAttribute("message", "회원가입이 실패했습니다.");
                resultUrl = "join/join.jsp";
            }
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            DBManager.close(conn, pstmt, rs);
        }

        request.setAttribute("selectMember", selectMember);

        RequestDispatcher dispatcher = request.getRequestDispatcher(resultUrl);
        dispatcher.forward(request, response);
    }
}
