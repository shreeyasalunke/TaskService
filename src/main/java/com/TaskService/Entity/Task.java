package com.TaskService.Entity;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.TaskService.Enum.TaskStatus;

@Entity
@Table(name = "tasks")
public class Task {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    private LocalDate dueDate;

    // References to other services (store ids only)
    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Column(name = "manager_id", nullable = false)
    private Long managerId; // the user id of manager (user-service)

    // Owned relations within task-service
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserTask> assignedDevelopers = new ArrayList<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskSkill> requiredSkills = new ArrayList<>();

    // auditing
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    public Task() {}

    // getters / setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public TaskStatus getStatus() {
        // dynamic computation: if task overdue and not completed/in-review, show OVERDUE
        if (this.status != null
                && this.status != TaskStatus.COMPLETED
                && this.status != TaskStatus.INREVIEW
                && this.dueDate != null
                && this.dueDate.isBefore(LocalDate.now())) {
            return TaskStatus.OVERDUE;
        }
        return this.status;
    }

    public void setStatus(TaskStatus status) { this.status = status; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }

    public Long getManagerId() { return managerId; }
    public void setManagerId(Long managerId) { this.managerId = managerId; }

    public List<UserTask> getAssignedDevelopers() { return assignedDevelopers; }
    public void setAssignedDevelopers(List<UserTask> assignedDevelopers) { this.assignedDevelopers = assignedDevelopers; }

    public List<TaskSkill> getRequiredSkills() { return requiredSkills; }
    public void setRequiredSkills(List<TaskSkill> requiredSkills) { this.requiredSkills = requiredSkills; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    // convenience helpers to maintain bidirectional lists
//    public void addUserTask(UserTask ut) {
//        ut.setTask(this);
//        this.assignedDevelopers.add(ut);
//    }
//    public void removeUserTask(UserTask ut) {
//        ut.setTask(null);
//        this.assignedDevelopers.remove(ut);
//    }
//    public void addTaskSkill(TaskSkill ts) {
//        ts.setTask(this);
//        this.requiredSkills.add(ts);
//    }
//    public void removeTaskSkill(TaskSkill ts) {
//        ts.setTask(null);
//        this.requiredSkills.remove(ts);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task that = (Task) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}