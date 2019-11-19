package com.nagarro.daoImp;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nagarro.dao.ImageDao;
import com.nagarro.dto.Image;
import com.nagarro.service.Factory;

public class ImageDaoImp implements ImageDao {

	private static SessionFactory sessionFactory;
	final static Logger LOG = Logger.getLogger(ImageDaoImp.class);

	public ImageDaoImp() {
		sessionFactory = Factory.getSessionFactoy();
	}

	public Image getImage(int imageId) {
		LOG.info("Retrieving Image :" + imageId);
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		Image image = (Image) session.get(Image.class, imageId);

		tx.commit();
		session.close();
		
		if(image != null) {
			LOG.info("Image " + imageId + " retrieval successful");
		} else {
			LOG.warn("Image " + imageId + " retrieval unsuccessful");
		}
		
		return image;
	}

	public void uploadImage(Image image) {
		LOG.info("Uploading image : " + image.getImageId());
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		session.save(image);

		tx.commit();
		session.close();
		
		LOG.info("Image " + image.getImageId() +" Uploaded");
	}

	public void deleteImage(int imageId) {
		LOG.info("Deleting image : " + imageId);
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Image image = session.get(Image.class, imageId);
		
		session.remove(image);
		tx.commit();
		session.close();

		LOG.info("Image " + imageId + "deleted");
	}

	public void updateImage(Image image) {
		LOG.info("Updating image : " + image.getImageId());
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		session.update(image);

		tx.commit();
		session.close();

		LOG.info("Image " + image.getImageId() + " updated");
	}

}
