package com.ssafy.enjoytrip.model.dto;

import java.io.Serializable;

public class User implements Serializable{
	private String id;
	private String pass;
	private String name;
	private String email;
	private String addr1;
	private String addr2;
	

	public User() {
		super();
	}

	public User(String id, String pass, String name, String email, String addr1, String addr2) {
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.email = email;
		this.addr1 = addr1;
		this.addr2 = addr2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpass() {
		return pass;
	}

	public void setpass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", pass=" + pass + ", name=" + name + ", email=" + email + ", addr1=" + addr1
				+ ", addr2=" + addr2 + "]";
	}

}
