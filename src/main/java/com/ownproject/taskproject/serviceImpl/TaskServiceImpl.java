package com.ownproject.taskproject.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ownproject.taskproject.entity.Task;
import com.ownproject.taskproject.entity.Users;
import com.ownproject.taskproject.exception.Apiexception;
import com.ownproject.taskproject.exception.TaskNotFound;
import com.ownproject.taskproject.exception.UserNotFound;
import com.ownproject.taskproject.payload.TaskDto;
import com.ownproject.taskproject.repository.TaskRepository;
import com.ownproject.taskproject.repository.UserRepository;
import com.ownproject.taskproject.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Override
	public TaskDto saveTask(long userid, TaskDto taskDto) {
		
		Users user = userRepository.findById(userid).orElseThrow(
				() -> new UserNotFound(String.format("User Id %d not foung", userid))
				);
		Task task = modelMapper.map(taskDto,Task.class);
		task.setUsers(user);     //setting user value in task
		Task savedTask = taskRepository.save(task);
		
		return modelMapper.map(savedTask,TaskDto.class);
	}

	@Override
	public List<TaskDto> getAllTasks(long userid) {
		
		Users user = userRepository.findById(userid).orElseThrow(
				() -> new UserNotFound(String.format("User Id %d not foung", userid))
				);
		List<Task> tasks = taskRepository.findAllByUsersId(userid);
		
		return tasks.stream(
				).map(
				task -> modelMapper.map(task, TaskDto.class)
				).collect(Collectors.toList());
	}

	@Override
	public TaskDto getIndTasks(long userid, long taskid) {
		// TODO Auto-generated method stub
		Users user = userRepository.findById(userid).orElseThrow(
				() -> new UserNotFound(String.format("User Id %d not foung", userid))
				);
		Task task = taskRepository.findById(taskid).orElseThrow(
				() -> new TaskNotFound(String.format("Task Id %d not found", taskid))
				);
		if(user.getId() != task.getUsers().getId() ) {
			throw new Apiexception(String.format("Task Id %d is not belongs to User Id %d", taskid,userid));
		}
		return modelMapper.map(task,TaskDto.class);
	}

	@Override
	public void deleteIndTasks(long userid, long taskid) {
		Users user = userRepository.findById(userid).orElseThrow(
				() -> new UserNotFound(String.format("User Id %d not foung", userid))
				);
		Task task = taskRepository.findById(taskid).orElseThrow(
				() -> new TaskNotFound(String.format("Task Id %d not found", taskid))
				);
		if(user.getId() != task.getUsers().getId() ) {
			throw new Apiexception(String.format("Task Id %d is not belongs to User Id %d", taskid,userid));
		}
		
		taskRepository.deleteById(taskid);
		
	}

}
