package com.practice.feign.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.feign.model.ScheduledTask;
import com.practice.feign.service.TaskService;

@RestController
@RequestMapping("/api/v1/scheduled-tasks")
public record ScheduledTaskController(@Qualifier("scheduledTaskService") TaskService<ScheduledTask> scheduledTaskService) {
    
    @GetMapping
    public List<ScheduledTask> findTasks() {
        int[] sleeps = new int[] { 1000, 1500, 2000 };
        Random random = new Random();
        try {
            int rand = random.nextInt(3);
            System.out.println("rand: " + rand);
            Thread.sleep(sleeps[rand]);
            if(rand < 2) {
                throw new RuntimeException("exc thrown1");
            }
            return scheduledTaskService.findAll();
        } catch(InterruptedException exc) {
            exc.printStackTrace();
        }
        throw new RuntimeException("exc thrown2");
    }

    @GetMapping("/{taskId}")
    public ScheduledTask taskById(@PathVariable Integer taskId) {
        return scheduledTaskService.findById(taskId);
    }
}
