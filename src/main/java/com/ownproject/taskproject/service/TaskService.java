package com.ownproject.taskproject.service;

import java.util.List;

import com.ownproject.taskproject.payload.TaskDto;


public interface TaskService {

	public TaskDto saveTask(long userid, TaskDto taskDto);
	
	public List<TaskDto> getAllTasks(long userid);
	
	public TaskDto getIndTasks(long userid, long taskid);
	

	public void deleteIndTasks(long userid, long taskid);
}
