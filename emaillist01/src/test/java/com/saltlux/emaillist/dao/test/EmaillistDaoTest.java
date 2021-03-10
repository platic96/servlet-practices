package com.saltlux.emaillist.dao.test;

import java.util.List;

import com.saltlux.emaillist.dao.EmaillistDao;
import com.saltlux.emaillist.vo.EmaillistVo;

public class EmaillistDaoTest {
	public static void main(String[] args) {
		
		testInsert();
		testFindAll();
	}
	public static void testFindAll() {
		List<EmaillistVo> list = new EmaillistDao().findAll();
		for(EmaillistVo vo : list) {
			System.out.println(vo);
		}
	}
	
	public static void testInsert() {
		EmaillistVo vo = new EmaillistVo();
		
		vo.setEmail("als");
		vo.setFirstName("마");
		vo.setLastName("이콜");
		
		new EmaillistDao().insert(vo);
	}
}
