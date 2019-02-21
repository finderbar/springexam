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
import com.exam.core.domain.TodoBean;
import com.exam.core.exception.BusinessException;
import com.exam.core.services.TodoService;

/**
 * @author thein
 * @createdAt Feb 21, 2019
 */
@RestController
@RequestMapping(value = "/trello-api/v1.0")
public class TodoApiController {

	public static final Logger logger = LoggerFactory.getLogger(TodoApiController.class);

	@Autowired private TodoService todoService;
	@Autowired RestTemplate restTemplate;
	
	@RequestMapping(value = "/getAllTodo", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<?> getAllTodo(
			@RequestParam(value = "limit", required = true) final Long pageLimit,
			@RequestParam(value = "offset", required = true) final Long pageOffset) throws BusinessException {

		logger.info("Get All Todo Params ${limit} ${offset} ");

		ApiBean<List<TodoBean>> result = new ApiBean<List<TodoBean>>();
		Long offset = pageLimit * pageOffset;

		try {
			result.setData(todoService.getAllTodoByUserDefinedWithLimit(pageLimit, offset));
			result.setStatusCode(200);
			result.setStatus(true);
			result.setMessage("success");
		} catch (Exception ex) {
			result.setStatusCode(502);
			result.setStatus(false);
			result.setMessage(ex.getMessage());

			ex.printStackTrace();
		}

		return new ResponseEntity<ApiBean<List<TodoBean>>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/getTodo", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<?> getTodo(
			@RequestParam(value = "todoSeq", required = true) final Long todoSeq) throws BusinessException {

		logger.info("Get Todo Params ${todoSeq}");

		ApiBean<TodoBean> result = new ApiBean<TodoBean>();

		try {
			result.setData(todoService.getTodoById(todoSeq));
			result.setStatusCode(200);
			result.setStatus(true);
			result.setMessage("success");
		} catch (Exception ex) {
			result.setStatusCode(502);
			result.setStatus(false);
			result.setMessage(ex.getMessage());

			ex.printStackTrace();
		}

		return new ResponseEntity<ApiBean<TodoBean>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/addTodo", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<?> saveTodo(
			@RequestParam(value = "categorySeq", required = true) final Long categorySeq,
			@RequestParam(value = "todoProgress", required = true) final Integer todoProgress,
			@RequestParam(value = "description", required = false) final String description) throws BusinessException {

		logger.info("Save Todo params ${categorySeq} ${todoProgess} ${description}");

		ApiBean<MutateStatus> result = new ApiBean<MutateStatus>();

		try {
			TodoBean bean = new TodoBean();
			bean.setCategorySeq(categorySeq);
			bean.setTodoProgress(todoProgress);
			bean.setDescription(description);
			
			
			result.setData(new MutateStatus(todoService.saveTodo(bean)));
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

	@RequestMapping(value = "/updateTodo", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody ResponseEntity<?> modifyTdo(
			@RequestParam(value = "todoSeq", required = true) final Long todoSeq,
			@RequestParam(value = "todoProgress", required = true) final Integer todoProgress,
			@RequestParam(value = "description", required = false) final String description)  throws BusinessException {

		logger.info("Mdify Todo params ${todSeq} ${todoProgess} ${description}");


		ApiBean<Integer> result = new ApiBean<Integer>();

		try {
			TodoBean bean = new TodoBean();
			bean.setTodoSeq(todoSeq);
			bean.setTodoProgress(todoProgress);
			bean.setDescription(description);

			result.setData(todoService.modifyTodo(bean));
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

	@RequestMapping(value = "/removeTodo", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody ResponseEntity<?> removeTodo(
			@RequestParam(value = "todoSeq", required = true) final Long todoSeq) throws BusinessException {

		logger.info("Temove Todo params ${todoSeq}");

		ApiBean<Integer> result = new ApiBean<Integer>();

		try {
			result.setData(todoService.removeTodo(todoSeq));
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