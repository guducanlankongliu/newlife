package com.newlife.fitness.entity;

import java.io.Serializable;

/**
 * 后台管理员类
 * 没啥关联性
 */
public class DUser implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String dEmail;

	private String d_imgUrl;

	private String d_loginName;

	private String d_passWord;

	public String getdEmail() {
		return dEmail;
	}

	public void setdEmail(String dEmail) {
		this.dEmail = dEmail;
	}

	public String getdPhone() {
		return dPhone;
	}

	public void setdPhone(String dPhone) {
		this.dPhone = dPhone;
	}

	public String getdSex() {
		return dSex;
	}

	public void setdSex(String dSex) {
		this.dSex = dSex;
	}

	private String dPhone;

	private String dSex;

	private String d_userName;

	public DUser() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDEmail() {
		return this.dEmail;
	}

	public void setDEmail(String dEmail) {
		this.dEmail = dEmail;
	}

	public String getD_imgUrl() {
		return this.d_imgUrl;
	}

	public void setD_imgUrl(String d_imgUrl) {
		this.d_imgUrl = d_imgUrl;
	}

	public String getD_loginName() {
		return this.d_loginName;
	}

	public void setD_loginName(String d_loginName) {
		this.d_loginName = d_loginName;
	}

	public String getD_passWord() {
		return this.d_passWord;
	}

	public void setD_passWord(String d_passWord) {
		this.d_passWord = d_passWord;
	}

	public String getDPhone() {
		return this.dPhone;
	}

	public void setDPhone(String dPhone) {
		this.dPhone = dPhone;
	}

	public String getDSex() {
		return this.dSex;
	}

	public void setDSex(String dSex) {
		this.dSex = dSex;
	}

	public String getD_userName() {
		return this.d_userName;
	}

	public void setD_userName(String d_userName) {
		this.d_userName = d_userName;
	}

}