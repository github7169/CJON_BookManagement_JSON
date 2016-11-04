package com.cjon.user.service;

import com.cjon.user.dao.userDAO;

public class UserService {

	public boolean insertUser(String id, String email, String pw) {

		userDAO dao = new userDAO();
		boolean result = dao.insert(id, email, pw);
		
		return result;
	}

	public boolean getUser(String email, String pw) {

		userDAO dao = new userDAO();
		boolean result = dao.select(email, pw);
		
		return result;
	}
	
}