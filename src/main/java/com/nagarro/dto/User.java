package com.nagarro.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "Users")
public class User {

	@Id
	private String userId;
	private String pswd;
	private long totalSize;
	private String uPath;

	@OneToMany(mappedBy = "user" , fetch = FetchType.EAGER)
	private List<Image> images = new ArrayList<Image>();

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public List<Image> getUploadedImages() {
		return images;
	}

	public void setUploadedImages(List<Image> uploadedImages) {
		this.images = uploadedImages;
	}

	public long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	public String getuPath() {
		return uPath;
	}

	public void setuPath(String uPath) {
		this.uPath = uPath;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", pswd=" + pswd + ", totalSize=" + totalSize + ", uPath=" + uPath
				+ ", images=" + images + "]";
	}

}
