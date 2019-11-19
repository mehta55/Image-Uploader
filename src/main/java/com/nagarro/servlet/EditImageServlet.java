package com.nagarro.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.nagarro.daoImp.UserDaoImp;
import com.nagarro.dto.Image;
import com.nagarro.dto.User;
import com.nagarro.service.Constants;
import com.nagarro.service.EditImage;
import com.nagarro.service.Validate;

/**
 * Servlet implementation class EditImageServlet
 */
@WebServlet("/EditImageServlet")
public class EditImageServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String uid = request.getSession().getAttribute("user").toString();
		int imageId = Integer.parseInt(request.getSession().getAttribute("imageId").toString());
		
		Object actionObject = request.getParameter("actionType");
		int actionType;

		if (actionObject != null) {
			actionType = 1;
		} else {
			actionType = 2;
		}

		if (actionType == 1) {
			String newName = request.getParameter("newName");
			EditImage.updateImageName(imageId, newName);
		} else if (actionType == 2) {
			Image newImage = uploadImage(request, response, uid);
			if(newImage != null)
				EditImage.updateImageSource(imageId, newImage, uid);
		}
		response.sendRedirect("welcome.jsp");
	}

	private Image uploadImage(HttpServletRequest request , HttpServletResponse response, String uid) {
		ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
		List<FileItem> files = new ArrayList<FileItem>();
		Image image = new Image();
		try {
			files = sf.parseRequest(request);
			User user = new UserDaoImp().getUser(uid);

			for (FileItem file : files) {

				String viewPath = Constants.PATH + "\\" + user.getUserId() + "\\" + file.getName();
				String savePath = getServletContext().getRealPath("") + Constants.PATH + "\\" + uid + "\\"
						+ file.getName();

				
				image.setIpath(viewPath);
				image.setIsize(getMegaBytes(file.getSize()));

				if (!Validate.validImageSize(image)) {
					request.getSession().setAttribute("alert", "Image size more than limit!");
					return null;
				} else if (!Validate.validUpload(user, image)) {
					request.getSession().setAttribute("alert", "Maximum upload limit reached!");
					return null;
				} else if (Validate.alreadyUploaded(user, image)) {
					request.getSession().setAttribute("alert", "Already uploaded!");
					return null;
				} else {
					File newFile = new File(savePath);
					file.write(newFile);
				} 
			}
			
		} catch (Exception e) {
			System.out.println("<-- Exception :" + e);
		}
		
		return image;
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
