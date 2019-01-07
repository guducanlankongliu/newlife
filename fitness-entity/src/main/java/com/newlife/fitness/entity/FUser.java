package com.newlife.fitness.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *	前端用户表
 *	FUser -- 一对多 -- Comment		（评论类）
 *	FUser -- 一对多 -- Forum		（帖子类）
 *	FUser -- 一对多 -- UserCourse	（用户课程类）
 *	FUser -- 一对多 -- UserTrain	（用户教练中间类）
 *	用户可能有多个评论，多个贴，多个课程，多个教练。
 */
public class FUser implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String f_address;

	private int fAge;

	private String f_email;

	private String f_imgUrl;

	private String f_isVip;

	private String f_loginName;

	private String fPassword;

	private String f_phone;

	

	public int getfAge() {
		return fAge;
	}

	public void setfAge(int fAge) {
		this.fAge = fAge;
	}

	
	public String getfPassword() {
		return fPassword;
	}

	public void setfPassword(String fPassword) {
		this.fPassword = fPassword;
	}

	

	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String f_sex;

	private String f_userName;

	private Date f_vipBegiTtime;

	private Date f_vipEndTime;

	private List<Comments> comments;

	private List<Forum> forums;

	private List<UserCourse> userCourses;

	private List<UserTrain> userTrains;
	

	public FUser() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public int getFAge() {
		return this.fAge;
	}

	public void setFAge(int fAge) {
		this.fAge = fAge;
	}

	

	public String getF_imgUrl() {
		return this.f_imgUrl;
	}

	public void setF_imgUrl(String f_imgUrl) {
		this.f_imgUrl = f_imgUrl;
	}

	public String getF_isVip() {
		return this.f_isVip;
	}

	public void setF_isVip(String f_isVip) {
		this.f_isVip = f_isVip;
	}

	public String getF_loginName() {
		return this.f_loginName;
	}

	public void setF_loginName(String f_loginName) {
		this.f_loginName = f_loginName;
	}

	public String getFPassword() {
		return this.fPassword;
	}

	public void setFPassword(String fPassword) {
		this.fPassword = fPassword;
	}

	

	
	public String getF_address() {
		return f_address;
	}

	public void setF_address(String f_address) {
		this.f_address = f_address;
	}

	public String getF_email() {
		return f_email;
	}

	public void setF_email(String f_email) {
		this.f_email = f_email;
	}

	public String getF_phone() {
		return f_phone;
	}

	public void setF_phone(String f_phone) {
		this.f_phone = f_phone;
	}

	public String getF_sex() {
		return f_sex;
	}

	public void setF_sex(String f_sex) {
		this.f_sex = f_sex;
	}

	public String getF_userName() {
		return this.f_userName;
	}

	public void setF_userName(String f_userName) {
		this.f_userName = f_userName;
	}

	public Date getF_vipBegiTtime() {
		return this.f_vipBegiTtime;
	}

	public void setF_vipBegiTtime(Date f_vipBegiTtime) {
		this.f_vipBegiTtime = f_vipBegiTtime;
	}

	public Date getF_vipEndTime() {
		return this.f_vipEndTime;
	}

	public void setF_vipEndTime(Date f_vipEndTime) {
		this.f_vipEndTime = f_vipEndTime;
	}

	public List<Comments> getComments() {
		return this.comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public Comments addComment(Comments comment) {
		getComments().add(comment);
		comment.setFUser(this);

		return comment;
	}

	public Comments removeComment(Comments comment) {
		getComments().remove(comment);
		comment.setFUser(null);

		return comment;
	}

	public List<Forum> getForums() {
		return this.forums;
	}

	public void setForums(List<Forum> forums) {
		this.forums = forums;
	}

	public Forum addForum(Forum forum) {
		getForums().add(forum);
		forum.setFUser(this);

		return forum;
	}

	public Forum removeForum(Forum forum) {
		getForums().remove(forum);
		forum.setFUser(null);

		return forum;
	}

	public List<UserCourse> getUserCourses() {
		return this.userCourses;
	}

	public void setUserCourses(List<UserCourse> userCourses) {
		this.userCourses = userCourses;
	}

	public UserCourse addUserCours(UserCourse userCours) {
		getUserCourses().add(userCours);
		userCours.setFUser(this);

		return userCours;
	}

	public UserCourse removeUserCours(UserCourse userCours) {
		getUserCourses().remove(userCours);
		userCours.setFUser(null);

		return userCours;
	}

	public List<UserTrain> getUserTrains() {
		return this.userTrains;
	}

	public void setUserTrains(List<UserTrain> userTrains) {
		this.userTrains = userTrains;
	}

	public UserTrain addUserTrain(UserTrain userTrain) {
		getUserTrains().add(userTrain);
		userTrain.setFUser(this);

		return userTrain;
	}

	public UserTrain removeUserTrain(UserTrain userTrain) {
		getUserTrains().remove(userTrain);
		userTrain.setFUser(null);

		return userTrain;
	}

}