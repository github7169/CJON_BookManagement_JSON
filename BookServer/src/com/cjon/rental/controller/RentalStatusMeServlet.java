package com.cjon.rental.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cjon.rental.service.RentalService;

@WebServlet("/rentalStatusMe")
public class RentalStatusMeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RentalStatusMeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 단순히 입력을 받고 출력에 대한 지정을 담당
		String email = request.getParameter("email");
		String callback = request.getParameter("callback");
		
		// 로직 처리는 Service 객체 담당
		RentalService service = new RentalService();
		String result = service.getStatusMe(email);
		
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
