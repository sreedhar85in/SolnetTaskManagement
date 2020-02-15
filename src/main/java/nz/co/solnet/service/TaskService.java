package nz.co.solnet.service;

import java.text.ParseException;
import java.util.List;

import nz.co.solnet.exception.TaskException;
import nz.co.solnet.model.Task;

public interface TaskService {

	public String saveTask(final Task task, boolean Update) throws TaskException;

	public List<Task> getAllTask();

	public List<Task> getAllOverDueTask() throws ParseException;

	public List<Task> taskforone(final Long id);

	boolean deleteTask(long id) throws TaskException;

}
