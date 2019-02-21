package com.exam.core.services.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.core.domain.TodoBean;
import com.exam.core.exception.BusinessException;
import com.exam.core.mappers.TodoMapper;
import com.exam.core.services.TodoService;

/**
 * @author thein
 * @createdAt Feb 21, 2019
 */
@Service("todoService")
public class TodoServiceImpl implements TodoService {
	
	@Autowired private TodoMapper todoMapper;
	
	@Override
	public List<TodoBean> getAllTodoByUserDefinedWithLimit(final Long limit, final Long offset) throws BusinessException {
		try {
			return todoMapper.findAllByUserDefinedWithLimit(limit, offset);
		} catch (BusinessException e) {
			throw new BusinessException("Err, when getting record by getAllTodoByUserDefinedWithLimit ===>:" + e);
		}
	}


	@Override
	public TodoBean getTodoById(final Long todoSeq) throws BusinessException {
		try {
			return todoMapper.getByTodoId(todoSeq);
		} catch (BusinessException e) {
			throw new BusinessException("Err, when when getting record by getTodoById ===>:" + e);
		}
	}

	@Override
	public Long saveTodo(final TodoBean bean) throws BusinessException {
		try {
			
			System.out.println("bean ============>"+ bean.getCategorySeq());
			int ret = todoMapper.saveTodo(bean);
			System.out.println("result ============>"+ ret);
			
			return bean.getTodoSeq();
		} catch (SQLException e) {
			throw new BusinessException("Err, when save todo ===>:" + e);
		}
	}

	
	@Override
	public int modifyTodo(final TodoBean bean) throws BusinessException {
		try {
			return todoMapper.updateTodo(bean);
		} catch (SQLException e) {
			throw new BusinessException("Err, when update todo ===>:" + e);
		}
	}


	@Override
	public int removeTodo(final Long todoSeq) throws BusinessException {
		try {
			return todoMapper.deleteTodo(todoSeq);
		} catch (SQLException e) {
			throw new BusinessException("Err, when delete todo ===>:" + e);
		}
	}

}
