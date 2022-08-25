package com.practice.feign.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.practice.feign.model.ScheduledTask;
import com.practice.feign.model.Task;
import com.practice.feign.model.TaskFilter;

@FeignClient(
    value = "${task-client.feign.name}",
    url = "${task-client.feign.url}"
)
public interface TaskRestClient {
    
    @GetMapping("/tasks/filter")
    List<Task> findNormalTasksByFilter(@SpringQueryMap TaskFilter taskFilter);

    @GetMapping(value = "/tasks")
    List<Task> findNormalTasks();

    @GetMapping(value = "/scheduled-tasks")
    List<ScheduledTask> findScheduledTasks();

    @GetMapping(value = "/tasks/{taskId}", produces = "application/json")
    Task findTaskById(@PathVariable("taskId") Integer taskId);

    @GetMapping(value = "/scheduled-tasks/{taskId}", produces = "application/json")
    ScheduledTask findScheduledTaskById(@PathVariable("taskId") Integer taskId);

}


