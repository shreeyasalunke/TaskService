package com.TaskService.Repository;


import com.TaskService.Entity.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTaskRepository extends JpaRepository<UserTask, Long> {

    List<UserTask> findByTaskId(Long taskId);

    List<UserTask> findByDeveloperId(Long developerId);
}
