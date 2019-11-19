package com.nagarro.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.nagarro.dao.ImageDao;
import com.nagarro.dao.UserDao;
import com.nagarro.daoImp.ImageDaoImp;
import com.nagarro.daoImp.UserDaoImp;
import com.nagarro.dto.Image;
import com.nagarro.dto.User;
import com.nagarro.service.Constants;
import com.nagarro.service.Validate;

/**
 * Servlet implementation class ImageUploader
 */
@WebServlet("/ImageUploader")
public class ImageUploader extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("-------------------------- CAME HERE");
		
		HttpSession session = request.getSession();
		String uid = session.getAttribute("user").toString();

		UserDao userdao = new UserDaoImp();
		ImageDao imagedao = new ImageDaoImp();

		User user = userdao.getUser(uid);

		try {
			ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
			List<FileItem> files = sf.parseRequest(request);

			for (FileItem file : files) {

				Image image = new Image();
				image.setIname(file.getName().substring(0, file.getName().length() - 4));

				
				String viewPath = Constants.PATH + "\\" + user.getUserId() + "\\" + file.getName();
				String savePath = getServletContext().getRealPath("") + Constants.PATH + "\\" + uid + "\\"
						+ file.getName();

				image.setIpath(viewPath);
				image.setIsize(getMegaBytes(file.getSize()));
				image.setUser(user);

				if (!Validate.validImageSize(image)) {
					request.getSession().setAttribute("alert", "Image size more than limit!");
					response.sendRedirect("welcome.jsp");
				} else if (!Validate.validUpload(user, image)) {
					request.getSession().setAttribute("alert", "Maximum upload limit reached!");
					response.sendRedirect("welcome.jsp");
				} else if (Validate.alreadyUploaded(user, image)) {
					request.getSession().setAttribute("alert", "Already uploaded!");
					response.sendRedirect("welcome.jsp");
				} else {
					
					File newFile = new File(savePath);
					file.write(newFile);

					userdao.updateUserSpace(user, image.getIsize(), true);
					imagedao.uploadImage(image);
					
					response.sendRedirect("welcome.jsp");
				}

			}
		} catch (Exception exception) {
			System.out.println(exception);
		}

	}

	private long getMegaBytes(long sizeBytes) {
		return ((sizeBytes / 1024) / 1024);
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
