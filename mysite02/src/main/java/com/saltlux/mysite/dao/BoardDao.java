package com.saltlux.mysite.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;
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

	public BoardVo find(int no_o) {
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = "select no,user_no,title,group_no,order_no,depth,contexts contexts,count from board where no = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no_o);

			rs = pstmt.executeQuery();

			// pstmt.close();
			if (rs.next()) {
				int no = rs.getInt(1);
				int user_no = rs.getInt(2);
				String title = rs.getString(3);
				int group_no = rs.getInt(4);
				int order_no = rs.getInt(5);
				int depth = rs.getInt(6);
				String context = rs.getString(7);
				int count = rs.getInt(8);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setUser_no(user_no);
				vo.setTitle(title);
				vo.setGroup_no(group_no);
				vo.setOrder_no(order_no);
				vo.setDepth(depth);
				vo.setContext(context);
				vo.setCount(count + 1);

				return vo;

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
	public int findAll() {
		List<BoardVo> list = new ArrayList<BoardVo>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = "select count(no) from board";// 
																																																		// 
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if(rs.next())
			{
				int no = rs.getInt(1);
				return no;
			}
			return 0;
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
		return 0;
	}

	public List<BoardVo> findAll(int number) {
		List<BoardVo> list = new ArrayList<BoardVo>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = "select b.no,u.name,b.title,b.group_no,b.order_no,b.depth,b.reg_date,b.contexts, count from board b, user u where b.user_no = u.no order by b.group_no desc, b.order_no asc limit ?,4;";// 
																																																		// 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (number-1)*4);
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

	/**
	 * @param vo
	 * @param answer
	 * @return
	 */
	public boolean InsertData(BoardVo vo, boolean answer) {
		PreparedStatement pstmt = null;
		String sql = "";
		PreparedStatement pstmt_safe = null;

		boolean result = false;
		try {
			conn = getConnection();

			sql = "set sql_safe_updates=0;";
			pstmt_safe = conn.prepareStatement(sql);

			if (answer == false) {
				sql = "insert into board values(null,?,?,(select ifnull(max(group_no),0) from board a)+1,1,1,now(),?,0);";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, vo.getUser_no());
				pstmt.setString(2, vo.getTitle());
				pstmt.setString(3, vo.getContext());

				int c = pstmt.executeUpdate();
				result = c == 1;
				return result;
			} else {

				sql = "select group_no,order_no,depth from board where no=?;";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, vo.getNo());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					int group_no = rs.getInt(1);
					int order_no = rs.getInt(2);
					int depth = rs.getInt(3);

					order_no = order_no + 1;
					depth = depth + 1;
					sql = "update board set order_no = order_no+1 where group_no=? and order_no>=?;";
					PreparedStatement pstmt1 = conn.prepareStatement(sql);
					pstmt1.setInt(1, group_no);
					pstmt1.setInt(2, order_no);

					int c = pstmt1.executeUpdate();

					if (c >= 0) {
						sql = "insert into board values(null,?,?,?,?,?,now(),?,0);";
						PreparedStatement pstmt2 = conn.prepareStatement(sql);
						pstmt2.setInt(1, vo.getUser_no());
						pstmt2.setString(2, vo.getTitle());
						pstmt2.setInt(3, group_no);
						pstmt2.setInt(4, order_no);
						pstmt2.setInt(5, depth);
						pstmt2.setString(6, vo.getContext());

						c = pstmt2.executeUpdate();

						result = c == 1;
						pstmt2.close();
						pstmt1.close();
						rs.close();
						return result;
					} else {
						System.out.println("pstmt를 실패했습니다.");
					}
					return false;
				}
				return false;
			}
		} catch (SQLException e) {
			System.out.println("error" + e.getLocalizedMessage());
		} finally {
			try {
				// 자원 정리
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (pstmt_safe != null) {
					pstmt_safe.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public List<BoardVo> findSection(BoardVo vo_o) {
		List<BoardVo> list = new ArrayList<BoardVo>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = "select b.no,u.name,b.title,b.group_no,b.order_no,b.depth,b.reg_date,b.contexts, "
					+ "count from board b, user u where b.user_no = u.no and order_no>=? and b.group_no=? order by b.group_no desc, b.order_no asc;";// limit
																																						// ?,4
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo_o.getOrder_no());
			pstmt.setInt(2, vo_o.getGroup_no());
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

	public boolean Delete(BoardVo vo_o, int count) {
		String sql = "";
		PreparedStatement pstmt = null;
		boolean result= false;
		try {
			conn = getConnection();

			sql = "delete from board where order_no >= ? and order_no<? and group_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,vo_o.getOrder_no());
			pstmt.setInt(2,vo_o.getOrder_no()+count);
			pstmt.setInt(3,vo_o.getGroup_no());
			
			int c = pstmt.executeUpdate();
			result = c>=1;
			if(result)
			{
				sql = "update board set order_no = order_no-? where group_no = ? and order_no>=?;";
				PreparedStatement pstmt1 = conn.prepareStatement(sql);
				pstmt1.setInt(1, count);
				pstmt1.setInt(2, vo_o.getGroup_no());
				pstmt1.setInt(3, vo_o.getOrder_no());
				
				c=pstmt1.executeUpdate();
				result=c>=0;
				return result;
			}
			return result;
		} catch (SQLException e) {
			System.out.println("error" + e.getLocalizedMessage());
		} finally {
			try {
				// 자원 정리
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}
