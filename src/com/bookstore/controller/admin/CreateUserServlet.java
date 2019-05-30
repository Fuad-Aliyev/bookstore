package com.bookstore.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.entity.Users;
import com.bookstore.service.UsersService;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/admin/create_user")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UsersService userService = new UsersService();
	
	String successMessage = "User has been created successfully";
	String errorMessage = "A user has already registered with this email! Please choose another email";

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Create new User
		int flag = createUser(request);
		
		//Check if this session exists while refreshing page, if exists remove it.  
		//This session attribute is set when there is duplicate email while adding it
		if (null == request.getSession().getAttribute("user_error")) {
			request.getSession().removeAttribute("user_error");
		}
		
		//If flag is 1 then user has been created successfully
		if (flag == 1) {
			//Create Session
			HttpSession session  = request.getSession();
			session.setAttribute("user_success", successMessage);
			
			//Send back to list user page
			String url = request.getContextPath();
			response.sendRedirect(url + "/admin/list_users");
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("user_error", errorMessage);
			
			//Refresh the page
			response.sendRedirect(request.getHeader("Referer"));
		}
	}
	
	private int createUser(HttpServletRequest request) {
		String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		int userExists = 0;
		
		//Create new user
		Users user = new Users();
		user.setEmail(email);
		user.setPassword(password);
		user.setFullName(fullname);
		
		//Check if this email already exists. If flag is zero then there is not registered this email
		int flag = userService.findByEmail(email);
		if (flag == 0) {
			userExists = 1;
			userService.create(user);
		}
		
		return userExists;
	}

}
