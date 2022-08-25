package com.practice.feign.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.practice.feign.model.ScheduledTask;
import com.practice.feign.persistence.TaskRepository;
import com.practice.feign.service.TaskService;

@Service("scheduledTaskService")
public record ScheduledTaskService(TaskRepository taskRepository) implements TaskService<ScheduledTask> {

    @Override
    public List<ScheduledTask> findAll() {
        return taskRepository.findScheduledTasks();
    }

    @Override
    public ScheduledTask findById(Integer taskId) {
        return taskRepository.findScheduledTaskById(taskId)
            .orElseThrow(() -> new RuntimeException("scheduled-task not found with id: " + taskId));
    }

    @Override
    public void statusUp(Integer taskId) {
        final ScheduledTask task = findByIdForStatusUpdate(taskId);
        taskRepository.statusUp(task);
    }
    
    @Override
    public void statusDown(Integer taskId) {
        final ScheduledTask task = findByIdForStatusUpdate(taskId);
        taskRepository.statusDown(task);
    }
    
    private ScheduledTask findByIdForStatusUpdate(Integer taskId) {
        final ScheduledTask task = findScheduledTaskById(taskId)
            .filter(ScheduledTask::notPassedDueTo)
            .orElseThrow(() -> new RuntimeException("scheduled-task not found with id: " + taskId));
        return task;
    }

    private Optional<ScheduledTask> findScheduledTaskById(Integer taskId) {
        return taskRepository.findScheduledTaskById(taskId)
            .map(t -> (ScheduledTask) t);
    }

}
