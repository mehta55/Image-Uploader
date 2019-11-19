package com.nagarro.service;

import org.apache.log4j.Logger;

import com.nagarro.daoImp.UserDaoImp;
import com.nagarro.dto.User;

public class Authenticator {

	final static Logger LOG = Logger.getLogger(Authenticator.class);
	
	private Authenticator() {
		
	}
	
	public static boolean validUser(String uid, String pswd) {
		LOG.info("Validating User :" + uid);
		
		UserDaoImp userdao = new UserDaoImp();
		User user = userdao.getUser(uid);

		if (user == null) {
			return false;
		} else if (!user.getPswd().equals(pswd)) {
			LOG.warn("Incorrect Password, Validation Unsuccessful");
			return false;
		}

		LOG.info("Validation Successful");
		return true;
	}
}
