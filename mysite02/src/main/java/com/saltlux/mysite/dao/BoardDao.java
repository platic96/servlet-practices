package com.saltlux.mysite.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.saltlux.mysite.vo.BoardVo;

public class BoardDao {
	Connection conn = null;

	public boolean UpdateData(BoardVo vo) {
		boolean result = false;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = "update board set title=?,contexts=?  where no=?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContext());
			pstmt.setInt(3, vo.getNo());

			int count = pstmt.executeUpdate();
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

	public BoardVo find(int no) {
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = "select title, contexts,count from board where no = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			// pstmt.close();
			if (rs.next()) {

				sql = "update board set count = count+1  where no=?;";
				PreparedStatement pstmt1 = conn.prepareStatement(sql);
				pstmt1.setInt(1, no);

				int result = pstmt1.executeUpdate();

				pstmt1.close();
				if (result == 1) {
					String title = rs.getString(1);
					String context = rs.getString(2);
					int count = rs.getInt(3);

					BoardVo vo = new BoardVo();
					vo.setNo(no);
					vo.setTitle(title);
					vo.setContext(context);
					vo.setCount(count + 1);

					return vo;
				}
			} else {
				return null;
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

		return null;
	}

	public List<BoardVo> findAll() {
		List<BoardVo> list = new ArrayList<BoardVo>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = "select b.no,u.name,b.title,b.group_no,b.order_no,b.depth,b.reg_date,b.contexts, count from board b, user u where b.user_no = u.no;";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVo vo = new BoardVo();
				int no = rs.getInt(1);
				String user_name = rs.getString(2);
				String title = rs.getString(3);
				int group_no = rs.getInt(4);
				int order_no = rs.getInt(5);
				int depth = rs.getInt(6);
				Date reg_date = rs.getDate(7);
				String context = rs.getString(8);
				int count = rs.getInt(9);

				vo.setNo(no);
				vo.setUser_name(user_name);
				vo.setTitle(title);
				vo.setGroup_no(group_no);
				vo.setOrder_no(order_no);
				vo.setDepth(depth);
				vo.setReg_date(reg_date);
				vo.setContext(context);
				vo.setCount(count);

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

	public boolean UpdateCount(int no) {
		boolean result = false;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = "update board set count = count+1  where no=?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			int count = pstmt.executeUpdate();
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

	public boolean InsertData(BoardVo vo, boolean answer) {
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			conn = getConnection();

			String sql = "";
			if (answer == false) {
				sql = "insert into board values(null,?,?,(select max(group_no) from board a)+1,1,1,now(),?,0);";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, vo.getUser_no());
				pstmt.setString(2, vo.getTitle());
				pstmt.setString(3, vo.getContext());
				
				int c = pstmt.executeUpdate();
				result = c==1;
				return result;
			}
			else {
				sql = "select group_no,order_no,depth from board where no=?;";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, vo.getNo());
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					int group_no = rs.getInt(1);
					int order_no = rs.getInt(2);
					int depth = rs.getInt(3);
					
					order_no=order_no+1;
					depth=depth+1;
					sql = "update board set order_no = order_no+1 where group_no=? and order_no>=?;";
					PreparedStatement pstmt1 = conn.prepareStatement(sql);
					pstmt1.setInt(1, group_no);
					pstmt1.setInt(2,order_no);
				}
			}
		} catch (SQLException e) {
			System.out.println("error" + e.getLocalizedMessage());
		}
		 finally {
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
		return false;
	}
}
