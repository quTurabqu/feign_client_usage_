package com.practice.feign.controller;

import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
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
    public List<Task> findTasks(
            // @RequestParam("c_id") String clientId,
            // @RequestParam("c_secret") String clientSecret,
            @RequestHeader("Client-Id") String clientId,
            @RequestHeader("Client-Secret") String clientSecret
    ) {
        System.out.println(">> waiting 3 seconds: SECRETS = " + clientId + " <----> " + clientSecret);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException exc) {
            exc.printStackTrace();
        }
        return normalTaskService.findAll();
    }

    @GetMapping("/{taskId}")
    public Task taskById(@PathVariable Integer taskId) {
        return normalTaskService.findById(taskId);
    }

}
