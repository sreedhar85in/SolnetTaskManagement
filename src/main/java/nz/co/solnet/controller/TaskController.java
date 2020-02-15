package nz.co.solnet.controller;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import nz.co.solnet.exception.TaskException;
import nz.co.solnet.model.BaseMessage;
import nz.co.solnet.model.Task;
import nz.co.solnet.service.TaskService;

@RestController
public class TaskController {

	private Logger logger = LoggerFactory.getLogger(TaskController.class);
	@Autowired
	private TaskService taskService;

	/**
	 * Fetch data for all the tasks.
	 * 
	 * @return
	 */
	@ApiOperation(value = "Fetch data for all the tasks", notes = "Fetch data for all the tasks")
	@GetMapping("/task")
	public List<Task> getAllTask() {
		logger.info("Received request for get all task details");
		return taskService.getAllTask();
	}

	/**
	 * Fetch data for all the overDue tasks.
	 * 
	 * @return
	 */
	@ApiOperation(value = "Fetch data for all the overduetasks", notes = "Fetch data for all the overduetasks")
	@GetMapping("/taskoverdue")
	public List<Task> getAllOverDueTask() {
		logger.info("Received request for get all over due task details");
		try {
			return taskService.getAllOverDueTask();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * Add a new record.
	 * 
	 * @param task
	 * @return
	 * @throws TaskException
	 */
	@ApiOperation(value = "Add a new record", notes = "Add a new record")
	@PostMapping("/task")
	public ResponseEntity<BaseMessage> saveTask(@RequestBody Task task) throws TaskException {
		logger.info("Received request for saving task ", task);
		BaseMessage baseMessage = new BaseMessage();
		String id = taskService.saveTask(task, false);
		if (id != null) {

			baseMessage.setStatus("Sucessfully created the task event with id " + id);
			return ResponseEntity.status(HttpStatus.CREATED).body(baseMessage);
		} else {
			baseMessage.setStatus("Failed to create task event");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseMessage);
		}

	}

	/***
	 * Modify a record.
	 * 
	 * @param task
	 * @return
	 * @throws TaskException
	 */
	@ApiOperation(value = "Modify a record", notes = "Modify a record")
	@PutMapping("/task")
	public ResponseEntity<BaseMessage> updateTask(@RequestBody Task task) throws TaskException {
		logger.info("Received request for updating task ", task);
		BaseMessage baseMessage = new BaseMessage();
		String id = taskService.saveTask(task, true);
		if (id != null) {

			baseMessage.setStatus("Sucessfully updated the task event with id " + id);
			return ResponseEntity.status(HttpStatus.CREATED).body(baseMessage);
		} else {
			baseMessage.setStatus("Failed to update task event");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseMessage);
		}

	}

	/**
	 * Delete a record.
	 * 
	 * @param
	 * @param
	 * @param
	 * @return
	 * @throws TaskException
	 */
	@ApiOperation(value = "Delete a record", notes = "Delete a record")
	@DeleteMapping("/task")
	public ResponseEntity<BaseMessage> deleteTask(
			@ApiParam(value = "Enter the ID") @RequestParam(value = "ID") Long id) {
		logger.info("Received request for deleting task ", id);
		boolean isDeleted = false;
		try {
			isDeleted = taskService.deleteTask(id);
		} catch (TaskException e) {
			BaseMessage baseMessage = new BaseMessage();
			baseMessage.setStatus("failed to delete ");
			return ResponseEntity.accepted().body(baseMessage);
		}
		if (isDeleted) {
			BaseMessage baseMessage = new BaseMessage();
			baseMessage.setStatus("Successfully deleted ");
			return ResponseEntity.accepted().body(baseMessage);
		} else {
			BaseMessage baseMessage = new BaseMessage();
			baseMessage.setStatus("failed to delete ");
			return ResponseEntity.accepted().body(baseMessage);
		}

	}

	/**
	 * Fetch data for a record.
	 * 
	 * @return
	 */
	@ApiOperation(value = "Fetch data for a record", notes = "Fetch data for a record")
	@GetMapping("/taskForOne")
	public List<Task> getTaskForaSingleRecord(@ApiParam(value = "Enter the ID") @RequestParam(value = "ID") Long id) {
		logger.info("Received request for one city task details");
		return taskService.taskforone(id);
	}

}
