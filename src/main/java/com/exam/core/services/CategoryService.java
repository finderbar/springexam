package com.exam.core.services;

import java.util.List;

import com.exam.core.domain.CategoryBean;
import com.exam.core.exception.BusinessException;

/**
 * @author thein
 * @createdAt Feb 21, 2019
 */
public interface CategoryService {
	List<CategoryBean> getAllCategoryByUserDefinedWithLimit(final Long limit, final Long offset) throws BusinessException;
	CategoryBean getCategoryById(final Long categorySeq) throws BusinessException;
	
	Long saveCategory(final CategoryBean bean) throws BusinessException;
	int modifyCategory(final CategoryBean bean) throws BusinessException;
	int removeCategory(final Long categorySeq) throws BusinessException;
}
