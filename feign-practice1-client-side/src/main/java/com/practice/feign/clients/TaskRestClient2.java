package com.practice.feign.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.practice.feign.model.ScheduledTask;
import com.practice.feign.model.Task;
import com.practice.feign.model.annotations.FeignRetry;


@FeignClient(value = "${task-client2.feign.name}", url = "${task-client2.feign.url}")
public interface TaskRestClient2 {
    
    @GetMapping(value = "/tasks")
    List<Task> findNormalTasks();

    @FeignRetry(retry = 5) // custom annoation
    @GetMapping(value = "/scheduled-tasks")
    List<ScheduledTask> findScheduledTasks();

    @GetMapping(value = "/tasks/{taskId}", produces = "application/json")
    Task findTaskById(@PathVariable("taskId") Integer taskId);

    @GetMapping(value = "/scheduled-tasks/{taskId}", produces = "application/json")
    ScheduledTask findScheduledTaskById(@PathVariable("taskId") Integer taskId);

}
