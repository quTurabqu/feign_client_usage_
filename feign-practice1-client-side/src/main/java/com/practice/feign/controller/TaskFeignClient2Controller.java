package com.practice.feign.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.feign.clients.TaskRestClient2;
import com.practice.feign.model.ScheduledTask;
import com.practice.feign.model.Task;

@RestController
@RequestMapping("/clients/2")
public record TaskFeignClient2Controller(TaskRestClient2 taskRestClient) {
    
    @GetMapping("/tasks")
    public List<Task> normalTasks() {
        return taskRestClient.findNormalTasks();
    }

    @GetMapping("/scheduled-tasks")
    public List<ScheduledTask> scheduledTasks() {
        return taskRestClient.findScheduledTasks();
    }

}
