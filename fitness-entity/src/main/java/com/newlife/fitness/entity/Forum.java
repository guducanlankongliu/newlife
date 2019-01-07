package com.newlife.fitness.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *	发帖类
 *	Forum -- 一对多 -- Comment			（评论类）
 *	Forum -- 多对一 -- AuditingType		（审核类型）
 *	Forum -- 多对一 -- FUser			（用户表）
 */
public class Forum implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String content;

	private String isAdopt;

	private Date postingTime;

	private String title;
     private String content_img;
	public String getContent_img() {
		return content_img;
	}

	public void setContent_img(String content_img) {
		this.content_img = content_img;
	}

	private List<Comments> comments;

	private AuditingType auditingType;

	private FUser FUser;

	public Forum() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIsAdopt() {
		return this.isAdopt;
	}

	public void setIsAdopt(String isAdopt) {
		this.isAdopt = isAdopt;
	}

	public Date getPostingTime() {
		return this.postingTime;
	}

	public void setPostingTime(Date postingTime) {
		this.postingTime = postingTime;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Comments> getComments() {
		return this.comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public Comments addComment(Comments comment) {
		getComments().add(comment);
		comment.setForum(this);

		return comment;
	}

	public Comments removeComment(Comments comment) {
		getComments().remove(comment);
		comment.setForum(null);

		return comment;
	}

	public AuditingType getAuditingType() {
		return this.auditingType;
	}

	public void setAuditingType(AuditingType auditingType) {
		this.auditingType = auditingType;
	}

	public FUser getFUser() {
		return this.FUser;
	}

	public void setFUser(FUser FUser) {
		this.FUser = FUser;
	}

}