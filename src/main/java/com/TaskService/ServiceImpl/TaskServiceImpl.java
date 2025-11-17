package com.TaskService.ServiceImpl;

import com.TaskService.dto.*;
import com.TaskService.Mapper.TaskMapper;
import com.TaskService.Repository.TaskRepository;
import com.TaskService.Repository.UserTaskRepository;
import com.TaskService.Repository.TaskSkillRepository;
import com.TaskService.Service.TaskService;
import com.TaskService.Entity.Task;
import com.TaskService.Entity.UserTask;
import com.TaskService.Entity.TaskSkill;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserTaskRepository userTaskRepository;
    private final TaskSkillRepository taskSkillRepository;

    public TaskServiceImpl(TaskRepository taskRepository,
                           UserTaskRepository userTaskRepository,
                           TaskSkillRepository taskSkillRepository) {
        this.taskRepository = taskRepository;
        this.userTaskRepository = userTaskRepository;
        this.taskSkillRepository = taskSkillRepository;
    }

    @Override
    public TaskResponseDto createTask(TaskCreateDto dto) {
        Task task = TaskMapper.fromCreateDto(dto);
        taskRepository.save(task);
        return TaskMapper.toDto(task);
    }

    @Override
    public TaskResponseDto getTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return TaskMapper.toDto(task);
    }

    @Override
    public List<TaskResponseDto> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(TaskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponseDto assignDeveloper(TaskAssignDto dto) {
        Task task = taskRepository.findById(dto.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Check if developer is already assigned
        boolean alreadyAssigned = userTaskRepository.findByTaskId(dto.getTaskId())
                .stream()
                .anyMatch(ut -> ut.getDeveloperId().equals(dto.getDeveloperId()));

        if (!alreadyAssigned) {
            UserTask userTask = new UserTask(dto.getDeveloperId(), task);
            userTaskRepository.save(userTask);
        }

        return TaskMapper.toDto(task);
    }

    @Override
    public List<Long> getDevelopersForTask(Long taskId) {
        return userTaskRepository.findByTaskId(taskId)
                .stream()
                .map(UserTask::getDeveloperId)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        
        // Remove assigned developers and skills first
        userTaskRepository.deleteAll(task.getAssignedDevelopers());
        taskSkillRepository.deleteAll(task.getRequiredSkills());

        taskRepository.delete(task);
    }
}
