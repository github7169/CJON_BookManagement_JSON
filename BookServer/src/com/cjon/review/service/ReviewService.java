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

	public boolean insertReview(String grade, String content, String email, String isbn) {

		reviewDAO dao = new reviewDAO();
		boolean result = dao.insert(grade, content, email, isbn);
		
		return result;
	}

	public String getListKeyword(String keyword) {

		reviewDAO dao = new reviewDAO();
		String result = dao.selectKeyword(keyword);
		
		return result;
	}

}
