package com.nagarro.service;

import org.apache.log4j.Logger;

import com.nagarro.dao.ImageDao;
import com.nagarro.dao.UserDao;
import com.nagarro.daoImp.ImageDaoImp;
import com.nagarro.daoImp.UserDaoImp;
import com.nagarro.dto.Image;
import com.nagarro.dto.User;

public class EditImage {
	
	final static Logger LOG = Logger.getLogger(EditImage.class);

	public static void updateImageName(int imageId, String newName) {
		LOG.info("Updating Image " + imageId + " name.");
		
		ImageDao imagedao = new ImageDaoImp();
		Image image = imagedao.getImage(imageId);
		image.setIname(newName);
		imagedao.updateImage(image);
		
		LOG.info("Image " + imageId + " name updated successfully.");
	}

	public static void updateImageSource(int imageId, Image newImage, String uid) {
		LOG.info("Updating Image " + imageId + " source.");
		
		
		ImageDao imagedao = new ImageDaoImp();
		UserDao userdao = new UserDaoImp();
		
		User user = Factory.getUserObject(uid);

		Image image = imagedao.getImage(imageId);
		userdao.updateUserSpace(user, image.getIsize(), false);

		image.setIpath(newImage.getIpath());
		image.setIsize(newImage.getIsize());

		userdao.updateUserSpace(user, image.getIsize(), true);
		imagedao.updateImage(image);
		
		LOG.info("Image " + imageId + " source updated successfully.");
		
	}
}
