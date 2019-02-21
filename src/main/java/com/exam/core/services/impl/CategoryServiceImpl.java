package com.exam.core.services.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.core.domain.CategoryBean;
import com.exam.core.exception.BusinessException;
import com.exam.core.mappers.CategoryMapper;
import com.exam.core.services.CategoryService;

/**
 * @author thein
 * @createdAt Feb 21, 2019
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired private CategoryMapper categoryMapper;

	@Override
	public List<CategoryBean> getAllCategoryByUserDefinedWithLimit(final Long limit, final Long offset)
			throws BusinessException {
		try {
			return categoryMapper.findAllByUserDefinedWithLimit(limit, offset);
		} catch (BusinessException e) {
			throw new BusinessException("Err, when getting record by getAllCategoryByUserDefinedWithLimit ===>:" + e);
		}
	}

	@Override
	public CategoryBean getCategoryById(final Long categorySeq) throws BusinessException {
		try {
			return categoryMapper.getByCategoryId(categorySeq);
		} catch (BusinessException e) {
			throw new BusinessException("Err, when when getting record by getCategoryById ===>:" + e);
		}
	}

	@Override
	public Long saveCategory(final CategoryBean bean) throws BusinessException {
		try {
			int ret = categoryMapper.saveCategory(bean);
			System.out.println("result ============>" + ret);
			return bean.getCategorySeq();
		} catch (SQLException e) {
			throw new BusinessException("Err, when save category ===>:" + e);
		}
	}

	@Override
	public int modifyCategory(final CategoryBean bean) throws BusinessException {
		try {
			return categoryMapper.updateCategory(bean);
		} catch (SQLException e) {
			throw new BusinessException("Err, when update category ===>:" + e);
		}
	}

	@Override
	public int removeCategory(final Long catgorySeq) throws BusinessException {
		try {
			return categoryMapper.deleteCategory(catgorySeq);
		} catch (SQLException e) {
			throw new BusinessException("Err, when delete category ===>:" + e);
		}
	}

}
