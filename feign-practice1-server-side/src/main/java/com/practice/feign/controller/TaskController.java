package com.practice.feign.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.feign.model.Task;
import com.practice.feign.service.TaskService;

@RestController
@RequestMapping("/api/v1/tasks")
public record TaskController(@Qualifier("normalTaskService") TaskService<Task> normalTaskService) {
    
    @GetMapping("/filter")
    public List<Task> findTasksByFilter(@RequestParam String taskName) {
        return normalTaskService.findAll()
            .stream()
            .filter(t -> t.getName().startsWith(taskName))
            .collect(Collectors.toList());
    }

    @GetMapping
    public List<Task> findTasks() {
        return normalTaskService.findAll();
    }

    @GetMapping("/{taskId}")
    public Task taskById(@PathVariable Integer taskId) {
        return normalTaskService.findById(taskId);
    }

}
