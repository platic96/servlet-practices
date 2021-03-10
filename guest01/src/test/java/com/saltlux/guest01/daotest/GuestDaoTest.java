package com.saltlux.guest01.daotest;

import java.util.List;

import com.saltlux.guest01.dao.GuestDao;
import com.saltlux.guest01.vo.GuestVo;

public class GuestDaoTest {
	public static void testPrint() {
		List<GuestVo> list = new GuestDao().findAll();
		for(GuestVo vo : list) {
			System.out.println(vo);
		}
	}
	
	public static void testInsert() {
		GuestVo vo = new GuestVo();
		vo.setName("오미자");
		vo.setPassword("1234");
		vo.setContents("야호 끝나간다.");
		
		new GuestDao().Insert(vo);
	}
	
	public static void testDelete() {
		new GuestDao().Delete(2, "1234");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//testInsert();
		//testDelete();
		testPrint();
	}

}
