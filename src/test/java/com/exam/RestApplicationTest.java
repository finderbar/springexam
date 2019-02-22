package com.exam;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.exam.core.domain.ApiBean;
import com.exam.core.domain.MutateStatus;
import com.exam.core.domain.UserBean;
import com.exam.front.restful.UserApiController;
import com.google.gson.Gson;

/**
 * @author thein
 * @createdAt Feb 22, 2019
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestApplicationTest {

	@Autowired private MockMvc mockMvc;
	@Autowired private Gson gson;
	@MockBean private UserApiController userApiController;

	/*
	 * @summary - create user api test
	 * 
	 * @param { String } to put userName
	 * 
	 * @param { String } to put email
	 * 
	 * @param { String } to put avatar
	 * 
	 * @return { Object }
	 */
	@Test
	public void createUserTest() throws Exception {
		ApiBean<MutateStatus> bean = new ApiBean<MutateStatus>();
		bean.setStatus(true);
		bean.setStatusCode(200);
		bean.setMessage("success");
		bean.setData(new MutateStatus(1L));
		
		ResponseEntity<ApiBean<MutateStatus>> resultBean = new ResponseEntity<ApiBean<MutateStatus>>(bean, HttpStatus.OK);
		when(userApiController.saveUser("theinlwin", "theinlwin@finderbar.com", "https://i.imgur.com/GkO21CF.png")).thenReturn(resultBean);
		mockMvc.perform(post("/trello-api/v1.0/addUser")
						.param("userName", "theinlwin")
						.param("email", "theinlwin@finderbar.com")
						.param("avatar", "https://i.imgur.com/GkO21CF.png")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON)
						.characterEncoding("UTF-8")
						.content(gson.toJson(bean)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.content().json(gson.toJson(bean))).andDo(print())
				.andExpect(jsonPath("$.status").value(true))
				.andExpect(jsonPath("$.message").value("success"))
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data.seq").value(1));
	}
	
	/*
	 * @summary - update user api test
	 * 
	 * @param { String } to put userName
	 * 
	 * @param { String } to put email
	 * 
	 * @param { String } to put avatar
	 * 
	 * @return { Object }
	 */
	@Test
	public void modifyUserTest() throws Exception {
		ApiBean<Integer> bean = new ApiBean<Integer>();
		bean.setStatus(true);
		bean.setStatusCode(200);
		bean.setMessage("success");
		bean.setData(1);
		
		ResponseEntity<ApiBean<Integer>> resultBean = new ResponseEntity<ApiBean<Integer>>(bean, HttpStatus.OK);
		when(userApiController.modifyUser(1l, "theinlwin", "theinlwin@finderbar.com", "https://i.imgur.com/GkO21CF.png")).thenReturn(resultBean);
		mockMvc.perform(put("/trello-api/v1.0/updateUser")
						.param("userSeq", "1")
						.param("userName", "theinlwin")
						.param("email", "theinlwin@finderbar.com")
						.param("avatar", "https://i.imgur.com/GkO21CF.png")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON)
						.characterEncoding("UTF-8")
						.content(gson.toJson(bean)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.content().json(gson.toJson(bean))).andDo(print())
				.andExpect(jsonPath("$.status").value(true))
				.andExpect(jsonPath("$.message").value("success"))
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data").value(1));
	}
	
	/*
	 * @summary - remove user api test
	 * 
	 * @param { String } to put userseq
	 * 
	 * @return { Object }
	 */
	@Test
	public void removeUserTest() throws Exception {
		ApiBean<Integer> bean = new ApiBean<Integer>();
		bean.setStatus(true);
		bean.setStatusCode(200);
		bean.setMessage("success");
		bean.setData(1);
		
		ResponseEntity<ApiBean<Integer>> resultBean = new ResponseEntity<ApiBean<Integer>>(bean, HttpStatus.OK);

		when(userApiController.removeUser(1l)).thenReturn(resultBean);
		mockMvc.perform(delete("/trello-api/v1.0/removeUser")
						.param("userSeq", "1")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON)
						.characterEncoding("UTF-8")
						.content(gson.toJson(bean)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.content().json(gson.toJson(bean))).andDo(print())
				.andExpect(jsonPath("$.status").value(true))
				.andExpect(jsonPath("$.message").value("success"))
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data").value(1));
	}
	
	
	/*
	 * @summary - get user
	 * 
	 * @param { String } to put userseq
	 * 
	 * @return { Object }
	 */
	@Test
	public void getUserTest() throws Exception {
		UserBean user = new UserBean();
		ApiBean<UserBean> bean = new ApiBean<UserBean>();
		bean.setStatus(true);
		bean.setStatusCode(200);
		bean.setMessage("success");
		user.setUserSeq(1L);
		user.setUserName("theinlwin");
		user.setEmail("theinlwin@gmail.com");
		user.setAvatar("https://i.imgur.com/GkO21CF.png");
		user.setRoles("admin");
		bean.setData(user);
		
		ResponseEntity<ApiBean<UserBean>> resultBean = new ResponseEntity<ApiBean<UserBean>>(bean, HttpStatus.OK);

		when(userApiController.getUser(1l)).thenReturn(resultBean);
		mockMvc.perform(get("/trello-api/v1.0/getUser")
						.param("userSeq", "1")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON)
						.characterEncoding("UTF-8")
						.content(gson.toJson(bean)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.content().json(gson.toJson(bean))).andDo(print())
				.andExpect(jsonPath("$.status").value(true))
				.andExpect(jsonPath("$.message").value("success"))
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data.userSeq").value(1L))
				.andExpect(jsonPath("$.data.userName").value("theinlwin"))
				.andExpect(jsonPath("$.data.email").value("theinlwin@gmail.com"))
				.andExpect(jsonPath("$.data.avatar").value("https://i.imgur.com/GkO21CF.png"))
				.andExpect(jsonPath("$.data.roles").value("admin"));
	}
	
	
	/*
	 * @summary - get user
	 * 
	 * @param { String } to put userseq
	 * 
	 * @return { Object }
	 */
	@Test
	public void getAllUserTest() throws Exception {
		List<UserBean> arrayList = new ArrayList<>();
		UserBean user = new UserBean();
		user.setUserSeq(1L);
		user.setUserName("theinlwin");
		user.setEmail("theinlwin@gmail.com");
		user.setAvatar("https://i.imgur.com/GkO21CF.png");
		user.setRoles("admin");
		
		arrayList.add(user);
		
		
		ApiBean<List<UserBean>> bean = new ApiBean<List<UserBean>>();
		bean.setStatus(true);
		bean.setStatusCode(200);
		bean.setMessage("success");
		bean.setData(arrayList);
		
		ResponseEntity<ApiBean<List<UserBean>>> resultBean = new ResponseEntity<ApiBean<List<UserBean>>> (bean, HttpStatus.OK);

		when(userApiController.getAllUser(10l, 0L)).thenReturn(resultBean);
		mockMvc.perform(get("/trello-api/v1.0/getAllUser")
						.param("limit", "10")
						.param("offset", "0")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON)
						.characterEncoding("UTF-8")
						.content(gson.toJson(bean)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(MockMvcResultMatchers.content().json(gson.toJson(bean))).andDo(print())
				.andExpect(jsonPath("$.status").value(true))
				.andExpect(jsonPath("$.message").value("success"))
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data[0].userSeq").value(1L))
				.andExpect(jsonPath("$.data[0].userName").value("theinlwin"))
				.andExpect(jsonPath("$.data[0].email").value("theinlwin@gmail.com"))
				.andExpect(jsonPath("$.data[0].avatar").value("https://i.imgur.com/GkO21CF.png"))
				.andExpect(jsonPath("$.data[0].roles").value("admin"));
	}

}
