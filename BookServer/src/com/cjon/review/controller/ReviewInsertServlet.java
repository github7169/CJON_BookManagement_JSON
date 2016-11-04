package com.cjon.review.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cjon.review.service.ReviewService;

@WebServlet("/reviewInsert")
public class ReviewInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public ReviewInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String grade = request.getParameter("grade");
		String content = request.getParameter("content");
		String email = request.getParameter("email");
		String isbn = request.getParameter("isbn");
		String callback = request.getParameter("callback");
		
		// 로직 처리는 Service 객체 담당
		ReviewService service = new ReviewService();
		boolean result = service.insertReview(grade, content, email, isbn);
		
		response.setContentType("text/plain; charset=utf8");
		
		PrintWriter out = response.getWriter();
		out.println(callback + "(" + result + ")");
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
