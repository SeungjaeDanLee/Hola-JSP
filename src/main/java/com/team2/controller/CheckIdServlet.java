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

import util.DBManager;

/**
 * Servlet implementation class CheckIdServlet
 */
@WebServlet("/checkId")
public class CheckIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String inputId = request.getParameter("inputId");

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		try {
			conn = DBManager.getConnection();

			if (inputId.isEmpty()) {
				out.println("빈 칸입니다.");
			} else {

				String sql = "SELECT id FROM member WHERE id = ?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, inputId);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					if (inputId.equals(rs.getString("id"))) {
						out.println("이미 존재하는 아이디입니다.");
					}
				} else {
					out.println("해당 아이디는 사용 가능합니다.");
				}
			}
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}
}
