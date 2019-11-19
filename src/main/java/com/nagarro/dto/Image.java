package com.nagarro.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Images")
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int imageId;
	private String iname;
	private String ipath;
	private long isize;

	@ManyToOne(targetEntity = User.class)
	private User user;

	public String getIname() {
		return iname;
	}

	public void setIname(String iname) {
		this.iname = iname;
	}

	public String getIpath() {
		return ipath;
	}

	public void setIpath(String ipath) {
		this.ipath = ipath;
	}

	public long getIsize() {
		return isize;
	}

	public void setIsize(long isize) {
		this.isize = isize;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Image [imageId=" + imageId + ", iname=" + iname + ", ipath=" + ipath + ", isize=" + isize + ", user="
				+ user.getUserId() + "]";
	}

	public boolean equals(Object o) {
		Image image = (Image) o;
		return this.ipath.equals(image.ipath);
	}

}
