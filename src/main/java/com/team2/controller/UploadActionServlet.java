package com.team2.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.team2.dao.MemberDAO;
import com.team2.dto.MemberVO;

@WebServlet("/UploadActionServlet")
public class UploadActionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String savePath = "C:\\sjl\\workspaceJSP\\JSP_Project_Team2\\src\\main\\webapp\\uploads"; 
        String photoFile = ""; 

        try {
            Part part = request.getPart("photoFile"); 


            String originalFileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();


            String fileName = System.currentTimeMillis() + "_" + originalFileName;
            part.write(savePath + File.separator + fileName);
            System.out.println("!!!!! " + fileName);
            
            MemberVO fileDTO = new MemberVO();
//            fileDTO.setFile_name(originalFileName);
            fileDTO.setFile_name(fileName);            
            fileDTO.setRegdate(getCurrentDate());
            
            Connection conn = null;
            PreparedStatement pstmt = null;

            HttpSession session = request.getSession(); 
    		String sessionId = (String)session.getAttribute("id");
            
    		System.out.println("@@@@" + sessionId);
    		
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "0000");

               
                //String sql = "INSERT INTO member (file_name) VALUES (?)";
                String sql = "UPDATE member SET file_name = ? WHERE id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, fileDTO.getFile_name());	
                pstmt.setString(2, sessionId);
                System.out.println("%%%%% DB 업데이트 - " + fileDTO.getFile_name());
                System.out.println("******테스트 " + pstmt.toString());
                pstmt.executeUpdate();
                
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
//                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("업로드 성공");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("업로드 실패");
        }

       
//        response.sendRedirect("setting.jsp?message=" + photoFile);
        
        String url = "/mypage/setting.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
    }

    private String getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime()).toString();
    }
}



