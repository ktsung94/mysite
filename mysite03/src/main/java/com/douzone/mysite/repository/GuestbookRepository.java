package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.exception.GuestbookRepositoryException;
import com.douzone.mysite.vo.GuestbookVo;

@Repository
public class GuestbookRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(GuestbookVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "insert into guestbook values (null, ?, ?, ?, now())";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getContents());
			pstmt.setString(3, vo.getPassword());

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			throw new GuestbookRepositoryException(e.getMessage());
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

	public Boolean delete(Long no, String password) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = " delete from guestbook " + 
					" where no=? && password=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);
			pstmt.setString(2, password);

			int count = pstmt.executeUpdate();
			result = count == 1; 

		} catch (SQLException e) {
			throw new GuestbookRepositoryException(e.getMessage());
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

	public List<GuestbookVo> findAll() {
		List<GuestbookVo> list = sqlSession.selectList("guestbook.findAll");
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
			throw new GuestbookRepositoryException("드라이버 로딩 실패:" + e);
		} 

		return conn;
	}
}
