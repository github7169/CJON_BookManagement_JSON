package com.cjon.book.service;

import com.cjon.book.dao.bookDAO;

public class BookService {

	public String getList(String keyword) {
		
		bookDAO dao = new bookDAO();
		String result = dao.select(keyword);
		
		return result;
	}

	public boolean updateBook(String isbn, String title, String author, String price) {
		
		bookDAO dao = new bookDAO();
		boolean result = dao.update(isbn, title, author, price);
		
		return result;
	}

	public boolean insertBook(String isbn, String img,  String title, String author, String price) {

		bookDAO dao = new bookDAO();
		boolean result = dao.insert(isbn, img, title, author, price);
		
		return result;
	}

	public boolean deleteBook(String isbn) {

		bookDAO dao = new bookDAO();
		boolean result = dao.delete(isbn);
		
		return result;
	}

	public String getListDetail(String isbn) {

		bookDAO dao = new bookDAO();
		String result = dao.selectDetail(isbn);
		
		return result;
	}
}
