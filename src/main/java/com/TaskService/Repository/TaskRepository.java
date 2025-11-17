package com.TaskService.Repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.TaskService.Entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProjectId(Long projectId);

    
    @Query("SELECT t FROM com.TaskService.Entity.Task t JOIN t.assignedDevelopers ut WHERE ut.developerId = :developerId")
    List<Task> findByDeveloperId(@Param("developerId") Long developerId);
}
