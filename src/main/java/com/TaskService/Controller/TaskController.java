package com.TaskService.Controller;

import com.TaskService.dto.*;
import com.TaskService.Service.TaskService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // CREATE TASK
    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskCreateDto dto) {
        return ResponseEntity.ok(taskService.createTask(dto));
    }

    // GET SINGLE TASK
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponseDto> getTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.getTask(taskId));
    }

    // GET ALL TASKS
    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    // ASSIGN DEVELOPER
    @PostMapping("/assign")
    public ResponseEntity<TaskResponseDto> assignDeveloper(@RequestBody TaskAssignDto dto) {
        return ResponseEntity.ok(taskService.assignDeveloper(dto));
    }

    // GET DEVELOPERS FOR A TASK
    @GetMapping("/{taskId}/developers")
    public ResponseEntity<List<Long>> getDevelopers(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.getDevelopersForTask(taskId));
    }

    // DELETE TASK
    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok("Task deleted successfully");
    }
}
