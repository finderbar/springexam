package com.exam.core.domain;

import java.io.Serializable;

/**
 * @author thein
 * @createdAt Feb 20, 2019
 */

public class ApiBean<T>  implements Serializable {
	private static final long serialVersionUID = 1L;
	private Boolean status;
	private String message;
	private int statusCode;
	private T data;
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

}

