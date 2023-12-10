package com.team2.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RemoveImageServlet")
public class RemoveImageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(); 
		String memberId = (String)session.getAttribute("id");
        String savePath = "C:\\sjl\\workspaceJSP\\JSP_Project_Team2\\src\\main\\webapp\\uploads";         

        String originalFileName = getOriginalFileNameFromDB(memberId);

        if (originalFileName != null && !originalFileName.isEmpty()) {
            
            File file = new File(savePath + File.separator + originalFileName);
            if (file.exists()) {
                file.delete();
            }
            removeImageFromDB(memberId);
        }
//        response.sendRedirect("setting.jsp"); 
        String url = "/mypage/setting.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
    }
    
    private String getOriginalFileNameFromDB(String memberId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null; 
        String originalFileName = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "0000");

            String sql = "SELECT file_name FROM member WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery(); 

            if (rs.next()) {
                originalFileName = rs.getString("file_name");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close(); 
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return originalFileName;
    }

    private void removeImageFromDB(String memberId) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "0000");

            String sql = "UPDATE member SET file_name = NULL WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);
            pstmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}