package com.nagarro.dao;

import com.nagarro.dto.Image;

public interface ImageDao {

	public Image getImage(int imageId);
	public void uploadImage(Image image);
	public void deleteImage(int imageId);
	public void updateImage(Image image);
	
}
