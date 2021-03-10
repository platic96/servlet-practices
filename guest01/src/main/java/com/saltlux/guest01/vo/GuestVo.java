package com.saltlux.guest01.vo;

import java.sql.Date;

public class GuestVo {
	private long no;
	private String name;
	private String contents;
	private String password;
	private Date reg_date;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	@Override
	public String toString() {
		return "GuestVo [no=" + no + ", name=" + name + ", contents=" + contents + ", password=" + password
				+ ", reg_date=" + reg_date + "]";
	}
	
}
