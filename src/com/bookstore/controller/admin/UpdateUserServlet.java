package com.bookstore.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.entity.Users;
import com.bookstore.service.UsersService;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/admin/update_user")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String success_msg = "The user has been updated successfully";
	String error_msg = "There is already the user with this email. Please choose another email!";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//If equals to 1, then user has been updated successfully
		HttpSession session;
		if (updateUser(request) == 1) {
			//Create session
			session = request.getSession();
			session.setAttribute("update_user_success", success_msg);
			
			//Send back to list user page
			String url = request.getContextPath();
			response.sendRedirect(url + "/admin/list_users");
		} else {
			//Create Session
			session = request.getSession();
			session.setAttribute("update_user_error", error_msg);
			
			//Refresh the page
			response.sendRedirect(request.getHeader("Referer"));
		}
	}
	
	private int updateUser(HttpServletRequest request) {
		int flag = 0;
		try {
			String email = request.getParameter("email");
			String fullName = request.getParameter("fullname");
			String password = request.getParameter("password");
			int userId = Integer.parseInt(request.getParameter("userId"));
			
			Users user = new Users();
			user.setEmail(email);
			user.setFullName(fullName);
			user.setPassword(password);
			
			
			UsersService userService = new UsersService();
			//Check if this email already exists. If flag is zero then there is not registered this email
			int emailExists = userService.findByEmail(email);
			if (emailExists == 1) {
				Users chkUser = userService.get(userId);
				//checks if the email belongs to this user
				if (email == chkUser.getEmail()) {
					//Update User
					userService.update(user);
					flag = 1;
				}
			} else {
				//Update User
				userService.update(user);
				flag = 1;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return flag;
	}

}
