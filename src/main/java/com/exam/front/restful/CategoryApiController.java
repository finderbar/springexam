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
import com.exam.core.domain.CategoryBean;
import com.exam.core.exception.BusinessException;
import com.exam.core.services.CategoryService;

/**
 * @author thein
 * @createdAt Feb 21, 2019
 */
@RestController
@RequestMapping(value = "/trello-api/v1.0")
public class CategoryApiController {

	public static final Logger logger = LoggerFactory.getLogger(CategoryApiController.class);

	@Autowired private CategoryService categoryService;
	@Autowired RestTemplate restTemplate;
	
	@RequestMapping(value = "/getAllCategory", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<?> getAllCategory(
			@RequestParam(value = "limit", required = true) final Long pageLimit,
			@RequestParam(value = "offset", required = true) final Long pageOffset) throws BusinessException {

		logger.info("Get All Category Params ${limit} ${offset} ");

		ApiBean<List<CategoryBean>> result = new ApiBean<List<CategoryBean>>();
		Long offset = pageLimit * pageOffset;

		try {
			result.setData(categoryService.getAllCategoryByUserDefinedWithLimit(pageLimit, offset));
			result.setStatusCode(200);
			result.setStatus(true);
			result.setMessage("success");
		} catch (Exception ex) {
			result.setStatusCode(502);
			result.setStatus(false);
			result.setMessage(ex.getMessage());

			ex.printStackTrace();
		}

		return new ResponseEntity<ApiBean<List<CategoryBean>>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/getCategory", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<?> getCategory(
			@RequestParam(value = "categorySeq", required = true) final Long categorySeq) throws BusinessException {

		logger.info("Get category Params ${categorySeq}");

		ApiBean<CategoryBean> result = new ApiBean<CategoryBean>();

		try {
			result.setData(categoryService.getCategoryById(categorySeq));
			result.setStatusCode(200);
			result.setStatus(true);
			result.setMessage("success");
		} catch (Exception ex) {
			result.setStatusCode(502);
			result.setStatus(false);
			result.setMessage(ex.getMessage());

			ex.printStackTrace();
		}

		return new ResponseEntity<ApiBean<CategoryBean>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/addCategory", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<?> saveCategory(
			@RequestParam(value = "userSeq", required = true) final Long userSeq,
			@RequestParam(value = "taskName", required = true) final String taskName,
			@RequestParam(value = "taskTitle", required = true) final String taskTitle) throws BusinessException {

		logger.info("Save Category params ${userSeq} ${taskName} ${taskTitle}");

		ApiBean<MutateStatus> result = new ApiBean<MutateStatus>();

		try {
			CategoryBean bean = new CategoryBean();
			bean.setUserSeq(userSeq);
			bean.setTaskName(taskName);
			bean.setTaskTitle(taskTitle);			
			
			result.setData(new MutateStatus(categoryService.saveCategory(bean)));
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

	@RequestMapping(value = "/updateCategory", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody ResponseEntity<?> modifyCategory(
			@RequestParam(value = "categorySeq", required = true) final Long categorySeq,
			@RequestParam(value = "taskName", required = true) final String taskName,
			@RequestParam(value = "taskTitle", required = true) final String taskTitle) throws BusinessException {

		logger.info("Modify Category params ${userSeq} ${taskName} ${taskTitle}");


		ApiBean<Integer> result = new ApiBean<Integer>();

		try {
			CategoryBean bean = new CategoryBean();
			bean.setCategorySeq(categorySeq);
			bean.setTaskName(taskName);
			bean.setTaskTitle(taskTitle);

			result.setData(categoryService.modifyCategory(bean));
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

	@RequestMapping(value = "/removeCategory", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody ResponseEntity<?> removeCategory(
			@RequestParam(value = "categorySeq", required = true) final Long categorySeq) throws BusinessException {

		logger.info("Remove Category params ${categorySeq}");

		ApiBean<Integer> result = new ApiBean<Integer>();

		try {
			result.setData(categoryService.removeCategory(categorySeq));
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