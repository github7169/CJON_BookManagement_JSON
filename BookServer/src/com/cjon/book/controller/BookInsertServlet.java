package com.cjon.book.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cjon.book.service.BookService;

@WebServlet("/bookInsert")
public class BookInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BookInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String isbn = request.getParameter("isbn");
		String img = request.getParameter("img");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String price = request.getParameter("price");
		String callback = request.getParameter("callback");
		
		// 로직 처리는 Service 객체 담당
		BookService service = new BookService();
		boolean result = service.insertBook(isbn, img, title, author, price);
		
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
