package com.cjon.book.service;

import com.cjon.book.dao.bookDAO;

public class BookService {

	public String getList(String keyword) {
		
		bookDAO dao = new bookDAO();
		String result = dao.select(keyword);
		
		return result;
	}

	public boolean updateBook(String isbn, String price) {
		
		bookDAO dao = new bookDAO();
		boolean result = dao.update(isbn, price);
		
		return result;
	}
}
