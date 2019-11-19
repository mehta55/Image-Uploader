package com.nagarro.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.nagarro.dao.UserDao;
import com.nagarro.daoImp.UserDaoImp;
import com.nagarro.dto.User;
import com.nagarro.service.Authenticator;
import com.nagarro.service.Factory;

/**
 * Servlet implementation class SignIn
 */
@WebServlet("/SignIn")
public class SignIn extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uid = request.getParameter("uid").toString();
		String pswd = Factory.getEncrptedPswd(request.getParameter("pswd").toString());

		if (Authenticator.validUser(uid, pswd)) {

			HttpSession session = request.getSession();
			session.setAttribute("user", uid);
			if (uid.equals("admin")) {
				response.sendRedirect("admin.jsp");
			} else {
				response.sendRedirect("welcome.jsp");
			}
		} else {
			request.getSession().setAttribute("alert", "Invalid Username or Password!");
			response.sendRedirect("index.jsp");
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
