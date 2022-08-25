package com.practice.feign.service;

import java.util.List;

import com.practice.feign.model.Task;

public interface TaskService<T extends Task> {

    List<T> findAll();
    T findById(Integer taskId);
    void statusUp(Integer taskId);
    void statusDown(Integer taskId);

}
