package mysite02;

import java.util.List;

import com.saltlux.mysite.dao.BoardDao;
import com.saltlux.mysite.vo.BoardVo;

public class TestBoard {
//	public static void test1() {
//		List<BoardVo> list = new BoardDao().findAll();
//		for(BoardVo vo : list) {
//			System.out.println(vo);
//		}
//	}
	
	public static void test2() {
		BoardVo vo=  new BoardVo();
		vo.setNo(1);
		vo.setTitle("수정 완.");
		vo.setContext("내용도 수정 완.");
		new BoardDao().UpdateData(vo);
	}
	
	public static void test3() {
		BoardVo vo = new BoardVo();
		vo = new BoardDao().find(3);
		System.out.println(vo);
	}
	public static void test4() {
		BoardVo vo = new BoardVo();
		vo.setTitle("답글이당");
		vo.setContext("답글 내용이당");
		vo.setNo(19);
		vo.setUser_no(2);
		
		new BoardDao().InsertData(vo, true);
	}
	
	public static void test1() {
		BoardVo vo = new BoardVo();
		vo.setNo(86);
		vo.setGroup_no(2);
		vo.setOrder_no(2);
		vo.setDepth(2);
		
		new BoardDao().Delete(vo, 2);
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//test4();
		//test3();
		test1();
	}

}
