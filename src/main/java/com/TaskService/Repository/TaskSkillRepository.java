package com.TaskService.Repository;

import com.TaskService.Entity.TaskSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskSkillRepository extends JpaRepository<TaskSkill, Long> {

    List<TaskSkill> findByTaskId(Long taskId);
}
