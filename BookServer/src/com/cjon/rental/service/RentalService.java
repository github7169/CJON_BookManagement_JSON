package com.cjon.rental.service;

import com.cjon.rental.dao.rentalDAO;

public class RentalService {
/*
	public String getList(String keyword) {
		
		bookDAO dao = new bookDAO();
		String result = dao.select(keyword);
		
		return result;
	}


	public boolean insertBook(String isbn, String img,  String title, String author, String price) {

		bookDAO dao = new bookDAO();
		boolean result = dao.insert(isbn, img, title, author, price);
		
		return result;
	}
*/

	public boolean getStatus(String isbn) {

		rentalDAO dao = new rentalDAO();
		boolean result = dao.selectStatus(isbn);
		
		return result;
	}
}
