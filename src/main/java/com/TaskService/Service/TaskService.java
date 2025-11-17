package com.TaskService.Service;


import com.TaskService.dto.*;

import java.util.List;

public interface TaskService {

    TaskResponseDto createTask(TaskCreateDto dto);

    TaskResponseDto getTask(Long taskId);

    List<TaskResponseDto> getAllTasks();

    TaskResponseDto assignDeveloper(TaskAssignDto dto);

    List<Long> getDevelopersForTask(Long taskId);

    void deleteTask(Long taskId);
}
