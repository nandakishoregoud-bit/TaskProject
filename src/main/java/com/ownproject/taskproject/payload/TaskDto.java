package com.ownproject.taskproject.payload;

import lombok.Getter;
import lombok.Setter;



public class TaskDto {
	
	private long id;
	private String taskname;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

}
