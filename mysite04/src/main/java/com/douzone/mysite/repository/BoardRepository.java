package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.GuestbookVo;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(BoardVo vo) {		
		return sqlSession.insert("board.insert", vo) == 1;		
	}
	
	public boolean insertReply(BoardVo vo, int gNo, int oNo, int depth) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "insert into board values (null, ?, ?, 0, now(), ?, ?+1, ?+1, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setInt(3, gNo);
			pstmt.setInt(4, oNo);
			pstmt.setInt(5, depth);
			pstmt.setLong(6, vo.getUserNo());

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;

	}
	
	public boolean delete(long no) {
		return sqlSession.delete("board.delete", no) == 1;		
	}
	
	public int update(BoardVo vo) {
		return sqlSession.update("board.modify", vo);
	}
	
	
	public int updateOno(int gNo, int oNo) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = getConnection();

			sql = "update board "
					+ "set o_no = o_no + 1 "
					+ "where g_no = ? and o_no > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gNo);
			pstmt.setInt(2, oNo);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			// 자원 정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;		
	}
		
	
	public int updateHit(Long no) {
		return sqlSession.update("board.updateHit", no);
	}


	public BoardVo findNo(long no) {
		return sqlSession.selectOne("board.findNo", no);
	}

	public List<BoardVo> findAll(String kwd) {		
		List<BoardVo> list = sqlSession.selectList("board.findAll", kwd);
		return list;			
	}

	

	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			//1. 드라이버 로딩
			Class.forName( "org.mariadb.jdbc.Driver" );

			//2. 연결하기
			String url="jdbc:mysql://192.168.1.109:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} 

		return conn;
	}


}
