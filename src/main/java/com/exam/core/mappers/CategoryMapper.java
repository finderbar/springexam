package com.exam.core.mappers;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.exam.core.domain.CategoryBean;
import com.exam.core.exception.BusinessException;

/**
 * @author thein
 * @createdAt Feb 21, 2019
 */

@Mapper
public interface CategoryMapper {
	@Select("select * from category limit #{offset}, #{limit}")
    List<CategoryBean> findAllByUserDefinedWithLimit(final Long limit, final Long offset) throws BusinessException;
	
	@Select("select * from category where categorySeq =#{categorySeq}")
	CategoryBean getByCategoryId(final Long categorySeq) throws BusinessException;
	
	@Insert("insert into category(userSeq, taskName, taskTitle) values(#{userSeq}, #{taskName}, #{taskTitle})")
	@Options(useGeneratedKeys = true, keyProperty = "categorySeq", keyColumn = "categorySeq")
	int saveCategory(final CategoryBean bean) throws SQLException;
	
	@Update("update category set taskName=#{taskName}, taskTitle=#{taskTitle} where categorySeq =#{categorySeq}")
	int updateCategory(final CategoryBean bean) throws SQLException;
	
	@Delete("delete from category where categorySeq =#{categorySeq}")
	int deleteCategory(final Long categorySeq) throws SQLException;
}
