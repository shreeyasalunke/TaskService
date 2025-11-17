package com.TaskService.dto;


public class TaskAssignDto {

    private Long taskId;
    private Long developerId;

    // getters & setters
    public Long getTaskId() { return taskId; }
    public void setTaskId(Long taskId) { this.taskId = taskId; }

    public Long getDeveloperId() { return developerId; }
    public void setDeveloperId(Long developerId) { this.developerId = developerId; }
}

