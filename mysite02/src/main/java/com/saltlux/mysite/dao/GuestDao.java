package com.saltlux.mysite.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.saltlux.mysite.vo.GuestVo;

public class GuestDao {
	public boolean Delete(int no, String password) {
		Connection conn = null;
	      PreparedStatement pstmt = null;
	      boolean result = false;

	      try {
	         conn = getConnection();
	         
	         // 3. sql문 준비
	         String sql = "delete from guestbook where no =? and password = ?;";
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setLong(1, no);
	         pstmt.setString(2, password);

	         int count = pstmt.executeUpdate();
	         // 4. 실행
	         result = count == 1;

	      } catch (SQLException e) {
	         // 1. 사과
	         // 2. log
	         System.out.println("error: " + e);
	      } finally {
	         try {
	            // 자원 정리
	            if (pstmt != null) {
	               pstmt.close();
	            }
	            if (conn != null) {
	               conn.close();
	            }
	         } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	      }
		return result;
	}
	
	public boolean Insert(GuestVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "insert into guestbook values (null, ?, ?, ?,now());";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContents());
			
	         int count = pstmt.executeUpdate();

	         // 5. 결과
	         result = count == 1;

	      } catch (SQLException e) {
	         // 1. 사과
	         // 2. log
	         System.out.println("error: " + e);
	      } finally {
	         try {
	            // 자원 정리
	            if (pstmt != null) {
	               pstmt.close();
	            }
	            if (conn != null) {
	               conn.close();
	            }
	         } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	      }
		return result;
	}
	
	 public List<GuestVo> findAll() {
	      List<GuestVo> list = new ArrayList<>();
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;

	      try {
	         conn = getConnection();
	         
	         // 3. sql문 준비
	         String sql = "select no, name, password, contents , date_format(reg_date, '%Y-%m-%d %H:%i:%s')date from guestbook order by no desc;";
	         pstmt = conn.prepareStatement(sql);

	         // 4. 실행
	         rs = pstmt.executeQuery();

	         // 5. 실행한 쿼리문에 대한 결과 데이터 가져오기
	         while (rs.next()) {
	            Long no = rs.getLong(1);
	            String Name = rs.getString(2);
	            String password = rs.getString(3);
	            String contents = rs.getString(4);
	            Date reg_date = rs.getDate(5); 

	            GuestVo vo = new GuestVo();
	            vo.setNo(no);
	            vo.setName(Name);
	            vo.setPassword(password);
	            vo.setContents(contents.replace("\r\n","<br>"));
	            vo.setReg_date(reg_date);

	            list.add(vo);
	         }

	      } catch (SQLException e) {
	         // 1. 사과
	         // 2. log
	         System.out.println("error: " + e);
	      } finally {
	         try {
	            // 자원 정리
	            if (rs != null) {
	               rs.close();
	            }
	            if (pstmt != null) {
	               pstmt.close();
	            }
	            if (conn != null) {
	               conn.close();
	            }
	         } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	      }

	      return list;
	   }

	
	   private Connection getConnection() throws SQLException{
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
