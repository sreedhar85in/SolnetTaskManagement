package nz.co.solnet.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nz.co.solnet.entitiy.TaskEntity;
import nz.co.solnet.exception.TaskException;
import nz.co.solnet.model.Task;
import nz.co.solnet.repository.TaskDataRestRepository;

@Service
public class TaskServiceImpl implements TaskService {

	private AtomicInteger atomicInteger = new AtomicInteger(1);;
	@Autowired
	private TaskDataRestRepository taskDataRestRepository;

	@Override
	public String saveTask(final Task task, boolean Update) throws TaskException {
		String id = null;

		try {
			TaskEntity taskEntity = new TaskEntity();
			taskEntity.setTitle(task.getTitle());
			taskEntity.setDescription(task.getDescription());
			taskEntity.setCreation_date(task.getCreation_date());
			taskEntity.setDue_date(task.getDue_date());
			taskEntity.setStatus(task.getStatus());
			long numberOfRows = taskDataRestRepository.count();
			atomicInteger.set((int) numberOfRows + 1);
			if (!Update) {
				atomicInteger.set((int) numberOfRows + 1);
				taskEntity.setId(Long.valueOf(atomicInteger.getAndIncrement()));
			} else if (Update) {
				taskEntity.setId(task.getId());
			} else {
				taskEntity.setId(Long.valueOf(atomicInteger.getAndIncrement()));
			}

			TaskEntity result = taskDataRestRepository.save(taskEntity);
			id = result.getId().toString();
		} catch (Exception ex) {
			throw new TaskException(ex.getMessage());
		}
		return id;
	}

	@Override
	public boolean deleteTask(final long id) throws TaskException {
		boolean isDeleted = true;
		try {

			TaskEntity taskEntity = new TaskEntity();
			taskEntity.setId(id);
			taskDataRestRepository.delete(taskEntity);

		} catch (Exception ex) {
			isDeleted = false;
			throw new TaskException(ex.getMessage());
		}
		return isDeleted;
	}

	@Override
	public List<Task> getAllTask() {
		List<Task> tasks = new ArrayList<Task>();
		Iterable<TaskEntity> taskEntities = taskDataRestRepository.findAll();
		for (TaskEntity taskEntity : taskEntities) {
			Task task = new Task();
			task.setTitle(taskEntity.getTitle());
			task.setDescription(taskEntity.getDescription());
			task.setCreation_date(taskEntity.getCreation_date());
			task.setDue_date(taskEntity.getDue_date());
			task.setStatus(taskEntity.getStatus());
			task.setId(taskEntity.getId());
			tasks.add(task);

		}
		return tasks;
	}

	@Override
	public List<Task> getAllOverDueTask() throws ParseException {
		List<Task> tasks = new ArrayList<Task>();

		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

		java.util.Date todayDate = dateFormatter.parse(dateFormatter.format(new java.util.Date()));

		Iterable<TaskEntity> taskEntities = taskDataRestRepository.findAll();
		for (TaskEntity taskEntity : taskEntities) {
			Task task = new Task();
			task.setTitle(taskEntity.getTitle());
			task.setDescription(taskEntity.getDescription());
			task.setCreation_date(taskEntity.getCreation_date());
			task.setDue_date(taskEntity.getDue_date());
			task.setStatus(taskEntity.getStatus());
			task.setId(taskEntity.getId());
			if (task.getDue_date().compareTo(todayDate) < 0) {
				tasks.add(task);
			}

		}
		return tasks;
	}

	@Override
	public List<Task> taskforone(final Long id) {

		List<Task> tasks = new ArrayList<Task>();

		try {

			Long idvalue1 = (long) id;
			TaskEntity taskEntity = new TaskEntity();
			taskEntity.setId(id);

			Optional<TaskEntity> taskEntities = taskDataRestRepository.findById(id);
			if (taskEntities.isPresent()) {

				Task task = new Task();
				task.setTitle(taskEntities.get().getTitle());
				task.setDescription(taskEntities.get().getDescription());
				task.setCreation_date(taskEntities.get().getCreation_date());
				task.setDue_date(taskEntities.get().getDue_date());
				task.setStatus(taskEntities.get().getStatus());
				task.setId(taskEntities.get().getId());
				tasks.add(task);

			} else {
				Task task = new Task();

			}

		} catch (Exception ex) {

			throw ex;
		}

		return tasks;
	}

}
