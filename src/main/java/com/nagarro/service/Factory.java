package com.nagarro.service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nagarro.dao.UserDao;
import com.nagarro.daoImp.UserDaoImp;
import com.nagarro.dto.User;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class Factory {

	private static Configuration con = new Configuration().configure();
	private static SessionFactory sessionFactory = con.buildSessionFactory();

	public static SessionFactory getSessionFactoy() {
		return sessionFactory;
	}

	public static User getUserObject(String uid) {
		UserDao userdao = new UserDaoImp();
		return userdao.getUser(uid);
	}

	public static String getEncrptedPswd(String pswd) {

		try {
			IvParameterSpec iv = new IvParameterSpec(Constants.INIT_VECTOR.getBytes("UTF-8"));
			SecretKeySpec sKeySpec = new SecretKeySpec(Constants.KEY.getBytes("UTF-8"), "AES");
			
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE , sKeySpec, iv);
			
			byte[] encrypted = cipher.doFinal(pswd.getBytes());
			return Base64.encode(encrypted);
		} catch (Exception e) {

		}
		return pswd;
	}

}
