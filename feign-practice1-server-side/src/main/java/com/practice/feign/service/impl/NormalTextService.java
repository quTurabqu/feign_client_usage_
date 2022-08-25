package com.practice.feign.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.practice.feign.model.Task;
import com.practice.feign.persistence.TaskRepository;
import com.practice.feign.service.TaskService;


@Service("normalTaskService")
public record NormalTextService(TaskRepository taskRepository) implements TaskService<Task> {

    @Override
    public List<Task> findAll() {
        return taskRepository.findNormalTasks();
    }

    @Override
    public Task findById(Integer taskId) {
        return taskRepository.findNormalTaskById(taskId)
            .orElseThrow(() -> new RuntimeException("normal-task not found with id: " + taskId));
    }

    @Override
    public void statusUp(Integer taskId) {
        final Task task = findById(taskId);
        taskRepository.statusUp(task);
    }
    
    @Override
    public void statusDown(Integer taskId) {
        final Task task = findById(taskId);
        taskRepository.statusDown(task);
    }

}
