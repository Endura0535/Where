package com.ssafy.enjoytrip.model.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User implements Serializable{
	private String id;
	private String pass;
	private String name;
	private String email;
	private String addr1;
	private String addr2;
}
