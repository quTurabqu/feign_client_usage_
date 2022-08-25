package com.practice.feign.persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.practice.feign.model.ScheduledTask;
import com.practice.feign.model.Task;

@Repository
public class TaskRepository {

    private static final List<Task> TASKS = Arrays.asList(
        Task.createRandomly(),
        Task.createRandomly(),
        Task.createRandomly(),
        Task.createRandomly(),
        Task.createRandomly()
    );

    private static final List<ScheduledTask> SCHEDULED_TASKS = Arrays.asList(
        ScheduledTask.createRandomly(),
        ScheduledTask.createRandomly(),
        ScheduledTask.createRandomly(),
        ScheduledTask.createRandomly(),
        ScheduledTask.createRandomly()
    );

    public List<Task> findAll() {
        List<Task> ALL_TASKS = new ArrayList<>(TASKS);
        ALL_TASKS.addAll(SCHEDULED_TASKS);
        return ALL_TASKS;
    }

    public List<Task> findNormalTasks() {
        return TASKS;
    }

    public List<ScheduledTask> findScheduledTasks() {
        return SCHEDULED_TASKS;
    }

    public Optional<Task> findNormalTaskById(Integer taskId) {
        return TASKS.stream()
            .filter(t -> t.getId() == taskId)
            .findFirst();
    }

    public Optional<ScheduledTask> findScheduledTaskById(Integer taskId) {
        return SCHEDULED_TASKS.stream()
            .filter(t -> t.getId() == taskId)
            .findFirst();
    }

    public Task statusUp(Task task) {
        Objects.requireNonNull(task);
        System.out.println("status up for task: " + task.getId());
        task.statusUp();
        return task;
    }

    public Task statusDown(Task task) {
        Objects.requireNonNull(task);
        System.out.println("status down for task: " + task.getId());
        task.statusDown();
        return task;
    }

    

    
}
