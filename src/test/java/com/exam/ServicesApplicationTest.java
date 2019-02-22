package com.exam;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.exam.core.domain.UserBean;
import com.exam.core.exception.BusinessException;
import com.exam.core.services.UserService;
import com.exam.front.restful.CategoryApiController;

/**
 * @author thein
 * @createdAt Feb 22, 2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServicesApplicationTest {
	public static final Logger logger = LoggerFactory.getLogger(CategoryApiController.class);
	
	@Autowired private UserService userService;
	
	/*
	 * @summary - create user service test
	 * 
	 * @param { Object } to put UserBean
	 * 
	 * @return { Long } you will get userSeq
	 */
	@Test
	public void saveUserTest() throws BusinessException {
		UserBean bean = new UserBean();
		bean.setUserName("theinlwin");
		bean.setEmail("theinlwin@gmail.com");
		bean.setStatus("online");
		bean.setAvatar("https://i.imgur.com/GkO21CF.png");
		bean.setRoles("admin");
		Long userSeq = userService.saveUser(bean);
		bean = userService.getUserById(userSeq);
		assertThat(userSeq).isEqualTo(bean.getUserSeq());
		
		System.out.println("<========================>");
		System.out.println("success create user");
		System.out.println("<========================>");
	}
	
	/*
	 * @summary - update user service test
	 * 
	 * @param { Object } to put UserBean
	 * 
	 * @return { Integer } you will get update status (0.fail, 1.success)
	 */
	@Test
	public void modifyUserTest() throws BusinessException {
		UserBean bean = new UserBean();
		bean.setUserSeq(1L);;
		bean.setUserName("theinlwin");
		bean.setEmail("theinlwin@gmail.com");
		bean.setStatus("online");
		bean.setAvatar("https://i.imgur.com/GkO21CF.png");
		bean.setRoles("admin");
		int result = userService.modifyUser(bean);
		assertThat(result).isEqualTo(1);
		
		System.out.println("<========================>");
		System.out.println("success update user");
		System.out.println("<========================>");
	}
	
	
	/*
	 * @summary - get user service test
	 * 
	 * @param { Long } to put userSeq
	 * 
	 * @return { Object } you will getting user
	 */
	@Test
	public void getByUserTest() throws BusinessException {
		UserBean bean = userService.getUserById(1L);
		assertThat(bean.getUserName()).isNotNull().isNotEmpty().isEqualTo("theinlwin");
		assertThat(bean.getEmail()).isNotNull().isNotEmpty().isEqualTo("theinlwin@gmail.com");
		assertThat(bean.getAvatar()).isNotNull().isNotEmpty().isEqualTo("https://i.imgur.com/GkO21CF.png");
		assertThat(bean.getRoles()).isNotNull().isNotEmpty().isEqualTo("admin");
		
		System.out.println("<========================>");
		System.out.println("success get user");
		System.out.println("<========================>");
	}
	
	/*
	 * @summary - get all user service test
	 * 
	 * @param { Long } to put limit
	 * 
	 * @param { Long } to put offset
	 * 
	 * @return { Array } you will getting List
	 */
	@Test
	public void getAllUserTest() throws BusinessException {
		List<UserBean> users = userService.getAllUserByDefinedWithLimit(10L, 0L);		
		assertNotNull(users);
		assertTrue(!users.isEmpty());
		
		System.out.println("<========================>");
		System.out.println("success get alll user");
		System.out.println("<========================>");
	}
	
	/*
	 * @summary - remove user service test
	 * 
	 * @param { Long } to put userSeq
	 * 
	 * @return { Integer } you will get update status (0.fail, 1.success)
	 */
	@Test
	public void removeUserTest() throws BusinessException {
		int result = userService.removeUser(1L);
		assertThat(result).isEqualTo(1);
		
		System.out.println("<========================>");
		System.out.println("success remove user");
		System.out.println("<========================>");
	}


}
