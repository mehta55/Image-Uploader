package com.nagarro.service;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import com.nagarro.dao.ImageDao;
import com.nagarro.dao.UserDao;
import com.nagarro.daoImp.ImageDaoImp;
import com.nagarro.daoImp.UserDaoImp;
import com.nagarro.dto.Image;
import com.nagarro.dto.User;

public class Delete {
	
	final static Logger LOG = Logger.getLogger(Delete.class);

	public static void deleteImage(String uid, int imageId) {
		LOG.info(uid +" deleting image :" + imageId);
		
		User user = Factory.getUserObject(uid);
		ImageDao imagedao = new ImageDaoImp();
		UserDao userdao = new UserDaoImp();
		Image image = imagedao.getImage(imageId);

		imagedao.deleteImage(imageId);
		userdao.updateUserSpace(user, image.getIsize(), false);
//		deleteImageDirectory(user, image);
	}

	private static void deleteImageDirectory(User user, Image image) {
		LOG.info("Deleting image directory");
		try {
			File directory = new File(user.getuPath());
			if (!directory.exists()) {
				System.out.println("Directory does not exist!");
			} else {
				File[] files = directory.listFiles();
				System.out.println("length : " + files.length);
				for (File file : files) {
					if (file.getName().equals(image.getIname())) {
						file.delete();
					}
				}
			}
		} catch (Exception e) {
			LOG.warn("Image directory could not be deleted.");
		}

	}

	public static boolean deleteUser(String userId) {
		
		boolean userDeleted = false;

		UserDaoImp userdao = new UserDaoImp();
		User deleteUser = userdao.getUser(userId);
		if(deleteUser != null) {
			deleteImages(deleteUser.getUploadedImages());
			userDeleted = userdao.deleteUser(userId);
			if (userDeleted) {	
//				deleteUserDirectory(deleteUser.getuPath());
			}
		}
		

		return userDeleted;
	}

	private static void deleteImages(List<Image> uploadedImages) {
		LOG.info("Deleting all images of user.");
		
		ImageDao imagedao = new ImageDaoImp();
		for (Image image : uploadedImages) {
			imagedao.deleteImage(image.getImageId());
		}

	}

	private static void deleteUserDirectory(String uPath) {
		File directory = new File(uPath);
		if (!directory.exists()) {
			LOG.warn("User directory does not exist.");
		} else {
			File[] files = directory.listFiles();
			if (files.length != 0) {
				for (File file : files) {
					file.delete();
				}
			}
			
			LOG.info("User directory Deleted.");
		}
	}
}
