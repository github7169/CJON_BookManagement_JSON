package com.cjon.rental.service;

import com.cjon.rental.dao.rentalDAO;

public class RentalService {

	public boolean getStatus(String isbn) {

		rentalDAO dao = new rentalDAO();
		boolean result = dao.selectStatus(isbn);
		
		return result;
	}

	public boolean insertRental(String email, String isbn) {

		rentalDAO dao = new rentalDAO();
		boolean result = dao.insert(email, isbn);
		
		return result;
	}

	public String getStatusMe(String email) {

		rentalDAO dao = new rentalDAO();
		String result = dao.selectStatusMe(email);
		
		return result;
	}

	public boolean deleteRental(String isbn) {
		
		rentalDAO dao = new rentalDAO();
		boolean result = dao.delete(isbn);
		
		return result;
	}

	public String getList(String keyword) {

		rentalDAO dao = new rentalDAO();
		String result = dao.select(keyword);
		
		return result;
	}
}
