package com.bookstore.controller.frontend;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public HomeServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
										throws ServletException, IOException {
		String homepage = "frontend/index.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(homepage);
		dispatcher.forward(req, res);
	}
	
}
