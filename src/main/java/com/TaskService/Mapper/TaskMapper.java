package com.TaskService.Mapper;



import com.TaskService.dto.TaskResponseDto;
import com.TaskService.dto.TaskCreateDto;
import com.TaskService.Entity.Task;
import java.time.LocalDate;

public class TaskMapper {

    public static TaskResponseDto toDto(Task t) {
        TaskResponseDto dto = new TaskResponseDto();
        dto.setId(t.getId());
        dto.setTitle(t.getTitle());
        dto.setDescription(t.getDescription());
        dto.setStatus(t.getStatus() != null ? t.getStatus().name() : null);
        dto.setPriority(null); // your entity doesn't have priority field; leave null or add
        dto.setStartDate(null);
        dto.setDueDate(t.getDueDate());
        dto.setCompletedDate(null);
        dto.setProjectId(t.getProjectId());
        dto.setAssignedUserId(null); // assigned developers are in UserTask; fetch separately if needed
        dto.setCreatedByUserId(t.getManagerId()); // managerId used as creator/manager in entity
        return dto;
    }

    public static Task fromCreateDto(TaskCreateDto dto) {
        Task t = new Task();
        t.setTitle(dto.getTitle());
        t.setDescription(dto.getDescription());
        t.setStatus(com.TaskService.Enum.TaskStatus.PENDING);
        t.setDueDate(dto.getDueDate());
        t.setProjectId(dto.getProjectId());
        t.setManagerId(dto.getCreatedByUserId()); // store manager/creator
        return t;
    }
}

