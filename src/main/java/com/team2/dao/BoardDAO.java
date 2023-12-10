 package com.team2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.team2.dto.BoardVO;
import com.team2.dao.BoardDAO;

import util.DBManager;

public class BoardDAO {
	private BoardDAO() {
	}
	
	public static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getInstance() {
		return instance;	
	}
	
	// 게시글 업데이트
		public void updateBoard(BoardVO bVo) {
			String sql = "UPDATE BOARD SET CONTENT = ? "
					+ "WHERE board_num = ?";
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = DBManager.getConnection();
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1,  bVo.getContent());
				pstmt.setInt(2,  bVo.getBoard_num());
				
				pstmt.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	
	//메인 페이지에 게시글들 불러오기
	
	public List<BoardVO> selectAllBoards() {
		String sql = "SELECT * FROM BOARD ORDER BY board_num DESC";
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				BoardVO bVo = new BoardVO();
				
				bVo.setBoard_num(rs.getInt("board_num"));
				bVo.setWriter(rs.getString("writer"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setStartdate(rs.getString("startdate"));
				bVo.setContact(rs.getString("contact"));
				bVo.setReadCount(rs.getInt("readCount"));
				bVo.setRecruit_num(rs.getInt("recruit_num"));
				bVo.setCategory(rs.getString("category"));
				bVo.setRegdate(rs.getTimestamp("regdate"));
				bVo.setLike(rs.getInt("like"));
				
				
				list.add(bVo);
			}
		} catch (Exception e) {
			e.printStackTrace();		
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		return list;
	}
	// 게시글 페이지 리스트 조회
		public List<BoardVO> selectBoardsPaging(int offset, int pageSize, String searchType, String searchText) {
			String sql = "";
			
			List<BoardVO> list = new ArrayList<BoardVO>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = DBManager.getConnection();
				
				if(searchType != null && searchText.length() > 0) {
					if(searchType.equals("all")) {	// 검색타입(제목+내용)
						sql = "SELECT * "
							+ "  FROM BOARD "
							+ " WHERE TITLE LIKE ? OR CONTENT LIKE ? "
							+ "ORDER BY board_num DESC "
							+ "LIMIT ?, ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, "%" + searchText + "%");
						pstmt.setString(2, "%" + searchText + "%");
						pstmt.setInt(3, offset);
						pstmt.setInt(4, pageSize);
					} else {
						switch(searchType) {
							case "title" :		// 검색타입(제목)
								sql = "SELECT * "
									+ "  FROM BOARD "
									+ " WHERE TITLE LIKE ? "
									+ "ORDER BY board_num DESC "
									+ "LIMIT ?, ?";
								pstmt = conn.prepareStatement(sql);
								pstmt.setString(1, "%" + searchText + "%");
								pstmt.setInt(2, offset);
								pstmt.setInt(3, pageSize);
								break;
							case "content" :	// 검색타입(내용)
								sql = "SELECT * "
									+ "  FROM BOARD "
									+ " WHERE CONTENT LIKE ? "
									+ "ORDER BY board_num DESC "
									+ "LIMIT ?, ?";
								pstmt = conn.prepareStatement(sql);
								pstmt.setString(1, "%" + searchText + "%");
								pstmt.setInt(2, offset);
								pstmt.setInt(3, pageSize);
								break;
							case "author" :		// 검색타입(작성자)
								sql = "SELECT * "
									+ "  FROM BOARD "
									+ " WHERE NAME LIKE ? "
									+ "ORDER BY board_num DESC "
									+ "LIMIT ?, ?";
								pstmt = conn.prepareStatement(sql);
								pstmt.setString(1, "%" + searchText + "%");
								pstmt.setInt(2, offset);
								pstmt.setInt(3, pageSize);
								break;
						}
					}
				} else {	// 검색어를 입력하지 않았을 때(전체 목록)
					sql = "SELECT * "
						+ "  FROM BOARD "
						+ "ORDER BY board_num DESC "
						+ "LIMIT ?, ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, offset);
					pstmt.setInt(2, pageSize);
				}
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					BoardVO bVo = new BoardVO();
					
					bVo.setBoard_num(rs.getInt("board_num"));
					bVo.setWriter(rs.getString("writer"));
					bVo.setTitle(rs.getString("title"));
					bVo.setContent(rs.getString("content"));
					bVo.setStartdate(rs.getString("startdate"));
					bVo.setContact(rs.getString("contact"));
					bVo.setReadCount(rs.getInt("readCount"));
					bVo.setRecruit_num(rs.getInt("recruit_num"));
					bVo.setCategory(rs.getString("category"));
					bVo.setRegdate(rs.getTimestamp("regdate"));
					bVo.setLike(rs.getInt("like"));
					
					list.add(bVo);
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			
			return list;
		}
	// 조회수 업데이트
		public void updateReadCount(String num) {
			String sql = "UPDATE BOARD SET READCOUNT = READCOUNT + 1 WHERE board_num = ?";
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = DBManager.getConnection();
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, num);
				
				pstmt.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
		
		
		// 게시판 글 상세 내용 보기 :글번호로 찾아옴. :실패 null
		public BoardVO selectOneBoardByNum(String num) {
			String sql = "SELECT * FROM BOARD WHERE board_num = ?";
			
			BoardVO bVo = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = DBManager.getConnection();
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, num);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					bVo = new BoardVO();
					
					bVo.setBoard_num(rs.getInt("board_num"));
					bVo.setWriter(rs.getString("writer"));
					bVo.setTitle(rs.getString("title"));
					bVo.setContent(rs.getString("content"));
					bVo.setStartdate(rs.getString("startdate"));
					bVo.setContact(rs.getString("contact"));
					bVo.setReadCount(rs.getInt("readCount"));
					bVo.setRecruit_num(rs.getInt("recruit_num"));
					bVo.setCategory(rs.getString("category"));
					bVo.setRegdate(rs.getTimestamp("regdate"));
					bVo.setLike(rs.getInt("like"));
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			
			return bVo;
		}
		
		
		public int selectAllBoardsCount(String searchType, String searchText) {
			String sql = "";
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int boardCnt = 0;
			
			try {
				conn = DBManager.getConnection();
				
				if(searchType != null && searchText.length() > 0) {
					if(searchType.equals("all")) {	// 검색타입(제목+내용)
						sql = "SELECT COUNT(*) FROM BOARD WHERE TITLE LIKE ? OR CONTENT LIKE ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, "%" + searchText + "%");
						pstmt.setString(2, "%" + searchText + "%");
					} else {
						switch(searchType) {
							case "title" :		// 검색타입(제목)
								sql = "SELECT COUNT(*) FROM BOARD WHERE TITLE LIKE ?";
								break;
							case "content" :	// 검색타입(내용)
								sql = "SELECT COUNT(*) FROM BOARD WHERE CONTENT LIKE ?";
								break;
							case "author" :		// 검색타입(작성자)
								sql = "SELECT COUNT(*) FROM BOARD WHERE writer LIKE ?";
								break;
						}
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, "%" + searchText + "%");
					}
				} else {	// 검색어를 입력하지 않았을 때(전체 목록)
					sql = "SELECT COUNT(*) FROM BOARD";
					pstmt = conn.prepareStatement(sql);
				}
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					boardCnt = rs.getInt(1);
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			return boardCnt;
		}
		
		// 게시글 삭제
		public void deleteBoard(String num) {
			String sql = "DELETE FROM BOARD WHERE board_num = ?";
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, num);
				
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 게시글 등록
		public void insertBoard(BoardVO bVo) {
			String sql = "INSERT INTO BOARD(board_num, category, recruit_num, startdate, contact, title, content) " 
						+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1,  bVo.getBoard_num());
				pstmt.setString(2,  bVo.getCategory());
				pstmt.setInt(3,  bVo.getRecruit_num());
				pstmt.setString(4,  bVo.getStartdate());
				pstmt.setString(5,  bVo.getContact());
				pstmt.setString(6,  bVo.getTitle());
				pstmt.setString(7,  bVo.getContent());
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
		//닉네임 변경
		public void updateNicknameInDB(String memberId, String newNickname) {
	        Connection conn = null;
	        PreparedStatement pstmt = null;

	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "0000");

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
		
		//카테고리 분류
		public List<BoardVO> selectCategory(String category) {
			String sql = "";
			
			List<BoardVO> list = new ArrayList<BoardVO>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = DBManager.getConnection();
						sql = "SELECT * "
							+ "  FROM BOARD ";
						if(!category.equals("전체")) {
							sql += " WHERE category = ? ";
								}
							sql+= "ORDER BY board_num DESC ";
							System.out.println(sql);
						pstmt = conn.prepareStatement(sql);
						if(!category.equals("전체")) {
							pstmt.setString(1,category);
								}
					
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					BoardVO bVo = new BoardVO();
					
					bVo.setBoard_num(rs.getInt("board_num"));
					bVo.setWriter(rs.getString("writer"));
					bVo.setTitle(rs.getString("title"));
					bVo.setContent(rs.getString("content"));
					bVo.setStartdate(rs.getString("startdate"));
					bVo.setContact(rs.getString("contact"));
					bVo.setReadCount(rs.getInt("readCount"));
					bVo.setRecruit_num(rs.getInt("recruit_num"));
					bVo.setCategory(rs.getString("category"));
					bVo.setRegdate(rs.getTimestamp("regdate"));
					bVo.setLike(rs.getInt("like"));
					
					list.add(bVo);
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			
			return list;
		}
}
