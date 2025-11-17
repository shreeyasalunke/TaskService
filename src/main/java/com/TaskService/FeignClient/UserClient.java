package com.TaskService.FeignClient;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.TaskService.Pojo.User;

@FeignClient(name = "user-service", path = "/api/users")
public interface UserClient {
    @GetMapping("/{id}")
    User getUserById(@PathVariable("id") Long id);
}

