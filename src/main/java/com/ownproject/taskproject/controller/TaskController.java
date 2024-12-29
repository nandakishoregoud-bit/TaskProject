package com.ownproject.taskproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ownproject.taskproject.payload.TaskDto;
import com.ownproject.taskproject.service.TaskService;
import com.ownproject.taskproject.service.UserService;

@RestController
@RequestMapping("/api")
public class TaskController {
	
	@Autowired
	private TaskService taskService;

	@Autowired
	private UserService userservice;
	
	// save the task
	@PostMapping("/{userid}/tasks")
	public ResponseEntity<TaskDto> saveTask(
			@PathVariable(name= "userid") Long userid,
			@RequestBody TaskDto taskDto
			){
		
	    TaskDto saved =	taskService.saveTask(userid, taskDto);
		
	    return new ResponseEntity<>(saved,HttpStatus.CREATED);
	}
	
	//get all task
	@GetMapping("/{userid}/tasks")
	public ResponseEntity<List<TaskDto>> getAllTasks(@PathVariable(name = "userid") Long userid){
		
		List<TaskDto> get = taskService.getAllTasks(userid);
		
		return new ResponseEntity<>(get, HttpStatus.OK);
		
	}
	
	//indv task
	@GetMapping("/{userid}/tasks/{taskid}")
	public ResponseEntity<TaskDto> getIndTask(@PathVariable(name = "userid") Long userid,@PathVariable(name = "taskid") Long taskid){
		
		TaskDto Indv = taskService.getIndTasks(userid,taskid);
		
		return new ResponseEntity<>(Indv, HttpStatus.OK);
	}
	
	
	//delete indv task
	@DeleteMapping("/{userid}/tasks/{taskid}")
    public ResponseEntity<String> deleteIndTask(@PathVariable(name = "userid") Long userid,@PathVariable(name = "taskid") Long taskid){
		
		taskService.deleteIndTasks(userid,taskid);
		
		return new ResponseEntity<>("Task deleted successfully", HttpStatus.OK);
	}
}
