package com.nagarro.dao;

import com.nagarro.dto.Image;
import com.nagarro.dto.User;

public interface UserDao {

	public void addUser(User user);

	public User getUser(String uid);

	public void updateUser(User user);

	public boolean deleteUser(String userId);

	public void updateUserSpace(User user, long imageSize, boolean isUpload);
}
