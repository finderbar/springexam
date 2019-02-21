package com.exam.core.mappers;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.exam.core.domain.UserBean;
import com.exam.core.exception.BusinessException;

/**
 * @author thein
 * @createdAt Feb 21, 2019
 */
@Mapper
public interface UserMapper {
	
	@Select("select * from user limit #{offset}, #{limit}")
    List<UserBean> findAllByUserDefinedWithLimit(final Long limit, final Long offset) throws BusinessException;
	
	@Select("select * from user where userSeq = #{userSeq}")
	UserBean getByUserId(final Long userSeq) throws BusinessException;
	
	@Insert("insert into user(userName, email, avatar, status, roles) values(#{userName}, #{email}, #{avatar}, #{status}, #{roles})")
	@Options(useGeneratedKeys = true, keyProperty = "userSeq", keyColumn = "userSeq")
	int saveUser(final UserBean user) throws SQLException;
	
	@Update("update user set userName=#{userName}, email=#{email}, avatar=#{avatar} where userSeq =#{userSeq}")
	int updateUser(final UserBean user) throws SQLException;
	
	@Delete("delete from user where userSeq =#{userSeq}")
	int deleteUser(final Long userSeq) throws SQLException;
}
