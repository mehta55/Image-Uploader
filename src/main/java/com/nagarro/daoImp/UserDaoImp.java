package com.nagarro.daoImp;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nagarro.dao.UserDao;
import com.nagarro.dto.Image;
import com.nagarro.dto.User;
import com.nagarro.service.Factory;

public class UserDaoImp implements UserDao {

	final static Logger LOG = Logger.getLogger(UserDaoImp.class);
	private static SessionFactory sessionFactory;

	public UserDaoImp() {
		sessionFactory = Factory.getSessionFactoy();
	}

	public void addUser(User user) {
		LOG.info("Adding User : " + user.getUserId());
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		session.save(user);

		tx.commit();
		session.close();
		
		LOG.info("User " + user.getUserId() + " added successfully.");
	}

	public User getUser(String uid) {
		LOG.info("Finding User :" + uid);
		Session session = sessionFactory.openSession();

		User user = session.get(User.class, uid);

		session.close();

		if (user != null) {
			LOG.info(uid + " found!");
		} else {
			LOG.warn(uid + " not found!");
		}

		return user;
	}

	public void updateUser(User updatedUser) {
		LOG.info("Updating User : " + updatedUser.getUserId());
		
		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();

		session.update(updatedUser);

		tx.commit();
		session.close();

		LOG.info("User " + updatedUser.getUserId() + " updated successfully.");

	}

	public boolean deleteUser(String userId) {
		LOG.info("Deleting User : " + userId);
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		boolean deleted = false;
		User deleteUser = session.get(User.class, userId);
		if (deleteUser != null) {
			session.remove(deleteUser);
			deleted = true;
		}

		tx.commit();
		session.close();

		if(deleted) {
			LOG.info("User " + userId + " deleted successfully.");
		} else {
			LOG.warn("User " + userId + " deletion unsuccessful.");
		}
		return deleted;
	}

	public void updateUserSpace(User user, long imageSize, boolean isUpload) {
		LOG.info("Update User Space : " + user.getUserId() );
		
		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();
		
		User updateUser = (User) session.get(User.class, user.getUserId());

		if (isUpload) {
			updateUser.setTotalSize(updateUser.getTotalSize() + imageSize);
		} else {
			updateUser.setTotalSize(updateUser.getTotalSize() - imageSize);
		}

		tx.commit();
		session.close();

		LOG.info("User " + user.getUserId() + " space updated successfully.");

	}

	public void initializeImages(User user) {
		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();

		User findUser = (User) session.get(User.class, user.getUserId());
		List<Image> images = findUser.getUploadedImages();

		LOG.info(images.size() + " images found for user - " + user.getUserId());
		user.setUploadedImages(images);

		tx.commit();
		session.close();

	}

}
