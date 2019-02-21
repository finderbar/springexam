package com.exam.front.restful;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.exam.core.domain.ApiBean;
import com.exam.core.domain.MutateStatus;
import com.exam.core.domain.UserBean;
import com.exam.core.exception.BusinessException;
import com.exam.core.services.UserService;

/**
 * @author thein
 * @createdAt Feb 21, 2019
 */
@RestController
@RequestMapping(value = "/trello-api/v1.0")
public class UserApiController {

	public static final Logger logger = LoggerFactory.getLogger(UserApiController.class);

	@Autowired private UserService userService;
	@Autowired RestTemplate restTemplate;
	
	@RequestMapping(value = "/getAllUser", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<?> getAllUser(
			@RequestParam(value = "limit", required = true) final Long pageLimit,
			@RequestParam(value = "offset", required = true) final Long pageOffset) throws BusinessException {

		logger.info("Get All User Params ${limit} ${offset} ");

		ApiBean<List<UserBean>> result = new ApiBean<List<UserBean>>();
		Long offset = pageLimit * pageOffset;

		try {
			result.setData(userService.getAllUserByDefinedWithLimit(pageLimit, offset));
			result.setStatusCode(200);
			result.setStatus(true);
			result.setMessage("success");
		} catch (Exception ex) {
			result.setStatusCode(502);
			result.setStatus(false);
			result.setMessage(ex.getMessage());

			ex.printStackTrace();
		}

		return new ResponseEntity<ApiBean<List<UserBean>>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/getUser", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<?> getUser(
			@RequestParam(value = "userSeq", required = true) final Long userSeq) throws BusinessException {

		logger.info("Get User Params ${userSeq}");

		ApiBean<UserBean> result = new ApiBean<UserBean>();

		try {
			result.setData(userService.getUserById(userSeq));
			result.setStatusCode(200);
			result.setStatus(true);
			result.setMessage("success");
		} catch (Exception ex) {
			result.setStatusCode(502);
			result.setStatus(false);
			result.setMessage(ex.getMessage());

			ex.printStackTrace();
		}

		return new ResponseEntity<ApiBean<UserBean>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<?> saveUser(
			@RequestParam(value = "userName", required = true) final String userName,
			@RequestParam(value = "email", required = true) final String email,
			@RequestParam(value = "avatar", required = false, defaultValue="https://i.imgur.com/GkO21CF.png") final String avatar) throws BusinessException {

		logger.info("Save User params ${username} ${email} ${avatar}");

		ApiBean<MutateStatus> result = new ApiBean<MutateStatus>();

		try {
			UserBean bean = new UserBean();
			bean.setUserName(userName);
			bean.setEmail(email);
			bean.setStatus("online");
			bean.setAvatar(avatar);
			bean.setRoles("user");
			
			Long userSeq = userService.saveUser(bean);

			result.setData(new MutateStatus(userSeq));
			result.setStatusCode(200);
			result.setStatus(true);
			result.setMessage("success");
		} catch (Exception ex) {
			result.setStatusCode(502);
			result.setStatus(false);
			result.setMessage(ex.getMessage());

			ex.printStackTrace();
		}

		return new ResponseEntity<ApiBean<MutateStatus>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody ResponseEntity<?> modifyUser(
			@RequestParam(value = "userSeq", required = true) final Long userSeq,
			@RequestParam(value = "userName", required = true) final String userName,
			@RequestParam(value = "email", required = false) final String email,
			@RequestParam(value = "avatar", required = false) final String avatar) throws BusinessException {

		logger.info("Modify User params ${userSeq} ${username} ${email} ${avatar}");


		ApiBean<Integer> result = new ApiBean<Integer>();

		try {
			UserBean bean = new UserBean();
			bean.setUserSeq(userSeq);
			bean.setUserName(userName);
			bean.setEmail(email);
			bean.setAvatar(avatar);

			result.setData(userService.modifyUser(bean));
			result.setStatusCode(200);
			result.setStatus(true);
			result.setMessage("success");
		} catch (Exception ex) {
			result.setStatusCode(502);
			result.setStatus(false);
			result.setMessage(ex.getMessage());

			ex.printStackTrace();
		}

		return new ResponseEntity<ApiBean<Integer>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/removeUser", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody ResponseEntity<?> removeUser(
			@RequestParam(value = "userSeq", required = true) final Long userSeq) throws BusinessException {

		logger.info("Remove User params ${username} ${email} ${avatar}");

		ApiBean<Integer> result = new ApiBean<Integer>();

		try {
			result.setData(userService.removeUser(userSeq));
			result.setStatusCode(200);
			result.setStatus(true);
			result.setMessage("success");
		} catch (Exception ex) {
			result.setStatusCode(502);
			result.setStatus(false);
			result.setMessage(ex.getMessage());

			ex.printStackTrace();
		}

		return new ResponseEntity<ApiBean<Integer>>(result, HttpStatus.OK);
	}

}
