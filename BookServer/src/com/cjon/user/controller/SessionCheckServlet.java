package com.cjon.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loginCheck")
public class SessionCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SessionCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String callback = request.getParameter("callback");
		
		HttpSession session = request.getSession();
		String loginEmail = (String) session.getAttribute("email");
		
		String result = null;
		
		// 세션있으면
		if( loginEmail != null ){
			result = "{status: 'true', email:" + "'" + loginEmail + "'" + "}";
		} else {
			result = "{status: 'false'}";
		}			

		System.out.println(result);
		
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
