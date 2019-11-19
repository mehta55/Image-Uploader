package com.nagarro.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.dao.ImageDao;
import com.nagarro.daoImp.ImageDaoImp;
import com.nagarro.daoImp.UserDaoImp;
import com.nagarro.dto.Image;
import com.nagarro.dto.User;
import com.nagarro.service.Delete;

/**
 * Servlet implementation class DeleteUser
 */
@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uid = request.getParameter("uid").toString();
		boolean deleted = Delete.deleteUser(uid);
		if(deleted) {
			request.getSession().setAttribute("alert", "User "   + uid + " deleted!");
		} else {
			request.getSession().setAttribute("alert", "User "   + uid + " not found!");
		}
		response.sendRedirect("admin.jsp");
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
