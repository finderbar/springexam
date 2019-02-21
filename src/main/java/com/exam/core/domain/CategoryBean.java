package com.exam.core.domain;

/**
 * @author thein
 * @createdAt Feb 21, 2019
 */
public class CategoryBean extends BaseBean {
	private static final long serialVersionUID = 1L;
	private Long userSeq;
	private Long categorySeq;
	private String taskName;
	private String taskTitle;

	public Long getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(Long userSeq) {
		this.userSeq = userSeq;
	}

	public Long getCategorySeq() {
		return categorySeq;
	}

	public void setCategorySeq(Long categorySeq) {
		this.categorySeq = categorySeq;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

}
