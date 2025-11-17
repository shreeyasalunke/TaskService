package com.TaskService.FeignClient;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.TaskService.Pojo.Project;

@FeignClient(name = "project-service", path = "/api/projects")
public interface ProjectClient {
    @GetMapping("/{id}")
    Project getProjectById(@PathVariable("id") Long id);
}
