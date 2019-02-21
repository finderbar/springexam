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

import com.exam.core.domain.UserBean;
import com.exam.core.exception.BusinessException;
import com.exam.core.mappers.UserMapper;

/**
 * @author thein
 * @createdAt Feb 21, 2019
 */
@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TrelloApplicationTest {
	@Autowired
	private UserMapper userMapper;
	
	public void saveUserTest() throws SQLException {
		System.out.println("<======================== Testing saveUsers  ========================>");
		UserBean bean = new UserBean();
		bean.setUserName("theinlwin");
		bean.setEmail("theinlwin@gmail.com");
		bean.setStatus("online");
		bean.setAvatar("thumbUrl");
		bean.setRoles("user");
		int ret = userMapper.saveUser(bean);
		assertThat(ret).isEqualTo(1);
	}
	
	public void updateUserTest() throws SQLException {
		System.out.println("<======================== Testing updateUsers  ========================>");
		UserBean bean = new UserBean();
		bean.setUserSeq(1L);
		bean.setUserName("aungaung");
		bean.setEmail("theinlwin@gmail.com");
		bean.setStatus("offline");
		bean.setAvatar("thumbUrl");
		bean.setRoles("user");
		int ret = userMapper.updateUser(bean);
		assertThat(ret).isEqualTo(1);
	}
	
	public void removeUserTest() throws SQLException {
		System.out.println("<======================== Testing removeUsers  ========================>");
		int ret = userMapper.deleteUser(1L);
		assertThat(ret).isEqualTo(1);
	}
	
	 @Test
    public void getByUserIdTest() throws BusinessException {
		System.out.println("<======================== Testing get user ========================>");
        UserBean user = userMapper.getByUserId(1L);
        assertNotNull(user);
    }

	@Test
	public void getAllUserTest() throws BusinessException {
		System.out.println("<======================== Testing get all users ========================>");
		List<UserBean> users = userMapper.findAllByUserDefinedWithLimit(10L, 0L);
		assertNotNull(users);
		assertTrue(!users.isEmpty());
	}
	
	 
}
