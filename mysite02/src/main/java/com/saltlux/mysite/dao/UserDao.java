package com.saltlux.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.saltlux.mysite.vo.UserVo;

public class UserDao {
	public UserVo findByEmailAndPassword(UserVo vo) {
		UserVo userVo = null;
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			conn = getConnection();
			
			String sql = "select no, name from user where email=? and password=?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getPassword());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				userVo = new UserVo();
				userVo.setNo(no);
				userVo.setName(name);
			}
			
		}catch(SQLException e) {
			System.out.println("error"+e.getLocalizedMessage());
		}
		finally {
			try {
				if(conn!=null)
					conn.close();
				if(pstmt != null)
					pstmt.close();
				if(rs != null)
					rs.close();
			}
			catch(Exception e) {
				System.out.println("error"+e.getLocalizedMessage());
			}
		}
		
		return userVo;
	}

	public boolean insert(UserVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "insert into user values(null,?,?,?,?,now());";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			
			int count =pstmt.executeUpdate();
			result = count== 1;
			
		}catch(SQLException e) {
			System.out.println("error"+e.getLocalizedMessage());
		}
		finally {
			try {
				if(conn!=null)
					conn.close();
				pstmt.close();
			}
			catch(Exception e) {
				System.out.println("error"+e.getLocalizedMessage());
			}
		}
		
		
		return result;
	}
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		// 1. JDBC 드라이버 객체 생성, 로딩
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://localhost:3306/webdb?characterEncoding=utf8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}
}
