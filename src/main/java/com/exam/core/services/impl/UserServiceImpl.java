package com.exam.core.services.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.core.domain.UserBean;
import com.exam.core.exception.BusinessException;
import com.exam.core.mappers.UserMapper;
import com.exam.core.services.UserService;

/**
 * @author thein
 * @createdAt Feb 21, 2019
 */

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired private UserMapper userMapper;

	@Override
	public List<UserBean> getAllUserByDefinedWithLimit(final Long limit, final Long offset) throws BusinessException {
		try {
			return userMapper.findAllByUserDefinedWithLimit(limit, offset);
		} catch (BusinessException e) {
			throw new BusinessException("Err, when getting record by getAllUserByDefinedWithLimit ===>:" + e);
		}
	}

	@Override
	public UserBean getUserById(final Long userSeq) throws BusinessException {
		try {
			return userMapper.getByUserId(userSeq);
		} catch (BusinessException e) {
			throw new BusinessException("Err, when when getting record by getUserById ===>:" + e);
		}
	}

	@Override
	public Long saveUser(final UserBean bean) throws BusinessException {
		try {
			int userSeq =  userMapper.saveUser(bean);
			System.out.println("userSeq ============>"+ userSeq);
			return bean.getUserSeq();
		} catch (SQLException e) {
			throw new BusinessException("Err, when save user ===>:" + e);
		}
	}
	
	
	@Override
	public int modifyUser(final UserBean bean) throws BusinessException {
		try {
			return userMapper.updateUser(bean);
		} catch (SQLException e) {
			throw new BusinessException("Err, when update user ===>:" + e);
		}
	}


	@Override
	public int removeUser(final Long userSeq) throws BusinessException {
		try {
			return userMapper.deleteUser(userSeq);
		} catch (SQLException e) {
			throw new BusinessException("Err, when update user ===>:" + e);
		}
	}

}
