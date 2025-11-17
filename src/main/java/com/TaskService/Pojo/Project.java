package com.TaskService.Pojo;

import java.time.LocalDate;

public class Project {
	 private Long id;
	    private String title;
	    private String status; // string representation from project service
	    private LocalDate startDate;
	    private LocalDate endDate;
	    private Long managerId; // optional: adapt if project-service exposes this

	    public Project() {}
	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }
	    public String getTitle() { return title; }
	    public void setTitle(String title) { this.title = title; }
	    public String getStatus() { return status; }
	    public void setStatus(String status) { this.status = status; }
	    public LocalDate getStartDate() { return startDate; }
	    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
	    public LocalDate getEndDate() { return endDate; }
	    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
	    public Long getManagerId() { return managerId; }
	    public void setManagerId(Long managerId) { this.managerId = managerId; }
	}
	