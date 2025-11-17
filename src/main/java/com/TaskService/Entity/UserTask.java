package com.TaskService.Entity;
import com.TaskService.Entity.Task;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "user_tasks", uniqueConstraints = {
    @UniqueConstraint(name = "uk_task_developer", columnNames = {"task_id","developer_id"})
})
public class UserTask {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // developer reference from user-service (store id only)
    @Column(name = "developer_id", nullable = false)
    private Long developerId;

    // local many-to-one to Task
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    public UserTask() {}

    public UserTask(Long developerId, Task task) {
        this.developerId = developerId;
        this.task = task;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getDeveloperId() { return developerId; }
    public void setDeveloperId(Long developerId) { this.developerId = developerId; }

    public Task getTask() { return task; }
    public void setTask(Task task) { this.task = task; }
}