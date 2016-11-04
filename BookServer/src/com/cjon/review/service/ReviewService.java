package com.cjon.review.service;

import com.cjon.review.dao.reviewDAO;

public class ReviewService {

	public String getList(String isbn) {
		
		reviewDAO dao = new reviewDAO();
		String result = dao.select(isbn);
		
		return result;
	}

	public boolean deleteReview(String seq) {

		reviewDAO dao = new reviewDAO();
		boolean result = dao.delete(seq);
		
		return result;
	}

	/*
	public boolean insertBook(String isbn, String img,  String title, String author, String price) {

		reviewDAO dao = new reviewDAO();
		boolean result = dao.insert(isbn, img, title, author, price);
		
		return result;
	}
	*/
}
