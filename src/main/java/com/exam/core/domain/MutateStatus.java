package com.exam.core.domain;

import java.io.Serializable;

/**
 * @author thein
 * @createdAt Feb 21, 2019
 */
public class MutateStatus implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long seq;

	public MutateStatus(Long userSeq) {
		super();
		this.seq = userSeq;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	@Override
	public String toString() {
		return "MutateStatus [seq=" + seq + "]";
	}
}
