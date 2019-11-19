package com.nagarro.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nagarro.daoImp.UserDaoImp;
import com.nagarro.dto.User;
import com.nagarro.service.Constants;
import com.nagarro.service.Factory;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uid = request.getParameter("uid");
		String pswd = Factory.getEncrptedPswd(request.getParameter("pswd"));
		String cpswd = Factory.getEncrptedPswd(request.getParameter("cpswd"));

		if (pswd.equals(cpswd)) {
			User user = addNewUser(uid, pswd);

			HttpSession session = request.getSession();
			session.setAttribute("user", uid);
			response.sendRedirect("welcome.jsp");
		} else {
			response.sendRedirect("index.jsp");
		}
	}

	private User addNewUser(String uid, String pswd) {
		User user = new User();
		user.setUserId(uid);
		user.setPswd(pswd);
		user.setuPath( getServletContext().getRealPath("") + Constants.PATH + "\\" + uid);

		UserDaoImp userdao = new UserDaoImp();
		userdao.addUser(user);
		createUserDirectory(user.getuPath());

		return user;
	}

	private void createUserDirectory(String uPath) {
		
		File file = new File(uPath);
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("New Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
