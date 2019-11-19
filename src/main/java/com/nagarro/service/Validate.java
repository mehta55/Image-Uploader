package com.nagarro.service; 

import java.util.List;

import org.apache.log4j.Logger;

import com.nagarro.dto.Image;
import com.nagarro.dto.User;

public class Validate {
	
	final static Logger LOG = Logger.getLogger(Validate.class);
	
	public static boolean validImageSize(Image image) {
		LOG.info("Validating Image " + image.getImageId() + "'s size.");
		
		boolean isValid = true;
		
		if (image.getIsize() > Constants.MAX_IMAGE_SIZE) {
			isValid = false;
		}
		
		
		if(isValid) {
			LOG.info("Image " + image.getImageId() + "has valid size.");
		} else {
			LOG.info("Image " + image.getImageId() + "has invalid size.");
		}
		return isValid;
	}

	public static boolean validUpload(User user, Image image) {
		LOG.info("Validating " + user.getUserId() + " has enough space.");
		
		boolean isValid = true;

		if (user.getTotalSize() + image.getIsize() > Constants.USER_CAPACITY) {
			isValid = false;
		}

		if(isValid) {
			LOG.info("user " + user.getUserId() + " has enough sapce.");
		} else {
			LOG.info("user " + user.getUserId() + " does not have enough sapce.");
		}
		
		return isValid;
	}

	public static boolean alreadyUploaded(User user, Image image) {
		LOG.info("Validating " + user.getUserId() + " already has "+ image.getImageId() + ".");
		
		boolean alreadyUploaded = false;

		List<Image> images = user.getUploadedImages();
		if (images.contains(image))
			alreadyUploaded = true;

		if(alreadyUploaded) {
			LOG.info("user " + user.getUserId() + " already has " + image.getImageId() + " image.");
		} else {
			LOG.info("user " + user.getUserId() + " does not has " + image.getImageId() + " image.");
		}
		
		return alreadyUploaded;

	}
}
