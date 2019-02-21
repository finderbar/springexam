package com.exam.core.domain;

/**
 * @author thein
 * @createdAt Feb 21, 2019
 */
public class UserBean extends BaseBean {
	private static final long serialVersionUID = 1L;
	private Long userSeq;
	private String userName;
	private String avatar;
	private String email;
	private String status;
	private String roles;

	public Long getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(Long userSeq) {
		this.userSeq = userSeq;
	}

	public final String getUserName() {
		return userName;
	}

	public final void setUserName(String userName) {
		this.userName = userName;
	}

	public final String getAvatar() {
		return avatar;
	}

	public final void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public final String getEmail() {
		return email;
	}

	public final void setEmail(String email) {
		this.email = email;
	}

	public final String getStatus() {
		return status;
	}

	public final void setStatus(String status) {
		this.status = status;
	}

	public final String getRoles() {
		return roles;
	}

	public final void setRoles(String roles) {
		this.roles = roles;
	}

}
