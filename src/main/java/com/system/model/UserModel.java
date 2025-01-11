package com.system.model;

import org.springframework.stereotype.Controller;

@Controller
public class UserModel {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
