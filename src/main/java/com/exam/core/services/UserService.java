package com.exam.core.services;

import java.util.List;

import com.exam.core.domain.UserBean;
import com.exam.core.exception.BusinessException;

/**
 * @author thein
 * @createdAt Feb 21, 2019
 */
public interface UserService {
	
	List<UserBean> getAllUserByDefinedWithLimit(final Long limit, final Long offset) throws BusinessException;
	UserBean getUserById(final Long userSeq) throws BusinessException;
	
	Long saveUser(final UserBean bean) throws BusinessException;
	int modifyUser(final UserBean bean) throws BusinessException;
	int removeUser(final Long userSeq) throws BusinessException;
}


