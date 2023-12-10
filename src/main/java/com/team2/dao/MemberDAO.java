package com.team2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.team2.dto.MemberVO;

import util.DBManager;

public class MemberDAO {
	
	private MemberDAO() {
	}
	
	public static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;	
	}
	
	
	public static Connection getConnection() throws Exception {
		return DBManager.getConnection();
	}
	
	
	//마이페이지 회원정보 select
	public MemberVO selectInfoByID(String id) {
		String sql = "SELECT * FROM MEMBER WHERE id =?";
		
		MemberVO mVo = null;
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mVo = new MemberVO();
				
				mVo.setId(rs.getString("id"));
				mVo.setPw(rs.getString("pw"));
				mVo.setRegdate(rs.getDate("regdate"));
				mVo.setPhone(rs.getString("phone"));
				mVo.setNickname(rs.getString("nickname"));
				mVo.setEmail(rs.getString("email"));
				mVo.setName(rs.getString("name"));
				mVo.setFile_name(rs.getString("file_name"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return mVo;
	}
	
	// 회원가입
	public int insertMember(MemberVO mVo) {
		int result = -1;
		
		String sql = "INSERT INTO member(id, pw, regdate, phone, nickname, email, name) " 
					+ "VALUES(?, ?, now(), ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,  mVo.getId());
			pstmt.setString(2,  mVo.getPw());
			pstmt.setString(3,  mVo.getPhone());
			pstmt.setString(4,  mVo.getNickname());
			pstmt.setString(5,  mVo.getName());
			pstmt.setString(6,  mVo.getEmail());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
	
	
	// 회원탈퇴
	public void deleteMember(String id) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		String sql = "DELETE FROM member WHERE id= ?";
		
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	public void updateNicknameInDB(String memberId, String newNickname) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBManager.getConnection();

            String sql = "UPDATE member SET nickname = ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newNickname);
            pstmt.setString(2, memberId);
            pstmt.executeUpdate();
            System.out.println("닉네임변경 완료");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("닉네임변경 실패");
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