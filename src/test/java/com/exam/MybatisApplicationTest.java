package com.exam;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import com.exam.core.domain.CategoryBean;
import com.exam.core.domain.TodoBean;
import com.exam.core.domain.UserBean;
import com.exam.core.exception.BusinessException;
import com.exam.core.mappers.CategoryMapper;
import com.exam.core.mappers.TodoMapper;
import com.exam.core.mappers.UserMapper;

/**
 * @author thein
 * @createdAt Feb 21, 2019
 */
@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MybatisApplicationTest {
	
	@Autowired private UserMapper userMapper;
	@Autowired private TodoMapper todoMapper;
	@Autowired private CategoryMapper categoryMapper;
	
	@Test
	public void userCRUDTest() throws SQLException, BusinessException {
		System.out.println("<======================== Testing saveUsers  ========================>");
		UserBean bean = new UserBean();
		bean.setUserSeq(1L);
		bean.setUserName("theinlwin");
		bean.setEmail("theinlwin@gmail.com");
		bean.setStatus("online");
		bean.setAvatar("thumbUrl");
		bean.setRoles("user");
		int userSeq = userMapper.saveUser(bean);
		assertThat(userSeq).isEqualTo(1);
		
		System.out.println("<======================== Testing updateUsers  ========================>");
		
		bean.setUserName("aungaung");
		bean.setStatus("offline");
		int ret = userMapper.updateUser(bean);
		assertThat(ret).isEqualTo(1);
		
		System.out.println("<======================== Testing getUser ========================>");
        UserBean user = userMapper.getByUserId(bean.getUserSeq());
        assertNotNull(user);
        
    	System.out.println("<======================== Testing getAllusers ========================>");
		List<UserBean> users = userMapper.findAllByUserDefinedWithLimit(10L, 0L);
		assertNotNull(users);
		assertTrue(!users.isEmpty());
	
		System.out.println("<======================== Testing saveCategory  ========================>");
		CategoryBean cbean = new CategoryBean();
		cbean.setCategorySeq(1l);
		cbean.setUserSeq(bean.getUserSeq());
		cbean.setTaskName("Wallet");
		cbean.setTaskTitle("Wallet Transations");
		int cteSeq = categoryMapper.saveCategory(cbean);
		assertThat(cteSeq).isEqualTo(1);
		
		System.out.println("<======================== Testing updateCategory  ========================>");
		cbean.setTaskTitle("Wallet Transfer");
		int ret1 = categoryMapper.updateCategory(cbean);
		assertThat(ret1).isEqualTo(1);
		
		System.out.println("<======================== Testing getCategory ========================>");
        CategoryBean category = categoryMapper.getByCategoryId(cbean.getCategorySeq());
        assertNotNull(category);
        
    	System.out.println("<======================== Testing getAllCategory ========================>");
		List<CategoryBean> categories = categoryMapper.findAllByUserDefinedWithLimit(10L, 0L);
		assertNotNull(categories);
		assertTrue(!categories.isEmpty());
		
		System.out.println("<======================== Testing saveTodo  ========================>");
		TodoBean tbean = new TodoBean();
		tbean.setTodoSeq(1L);
		tbean.setCategorySeq(cbean.getCategorySeq());
		tbean.setTodoProgress(50);
		int todoSeq = todoMapper.saveTodo(tbean);
		assertThat(todoSeq).isEqualTo(1);
		
		System.out.println("<======================== Testing updateTodo  ========================>");
		tbean.setTodoProgress(40);
		int upd1 = todoMapper.updateTodo(tbean);
		assertThat(ret1).isEqualTo(1);
		
		System.out.println("<======================== Testing getTodo ========================>");
        TodoBean todo = todoMapper.getByTodoId(tbean.getTodoSeq());
        assertNotNull(todo);
        
    	System.out.println("<======================== Testing getAllTodo ========================>");
		List<TodoBean> todos = todoMapper.findAllByUserDefinedWithLimit(10L, 0L);
		assertNotNull(todos);
		assertTrue(!todos.isEmpty());
		
		
		
		
		
		
		System.out.println("<======================== Testing removeTodo  ========================>");
		int del = todoMapper.deleteTodo(tbean.getTodoSeq());
		assertThat(del).isEqualTo(del);
		
		System.out.println("<======================== Testing removeCategory  ========================>");
		int del1 = categoryMapper.deleteCategory(cbean.getCategorySeq());
		assertThat(del1).isEqualTo(1);
		
		System.out.println("<======================== Testing removeUsers  ========================>");
		int del2 = userMapper.deleteUser(bean.getUserSeq());
		assertThat(del2).isEqualTo(1);
		
	}
	
}
