package com.practice.feign.model;

import java.time.LocalDateTime;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.practice.feign.model.enumerations.TaskStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ScheduledTask extends Task {

    protected LocalDateTime dueTo;

    public ScheduledTask(Integer id, String name, String description, Integer createdBy, LocalDateTime createdAt, LocalDateTime dueTo, TaskStatus status) {
        super(id, name, description, createdBy, createdAt, status);
        if(dueTo.isBefore(createdAt)) {
            throw new IllegalArgumentException("Invalid datetime for dueTo");
        }
        this.dueTo = dueTo;
    }
    
    @JsonIgnore
    public boolean notPassedDueTo() {
        return dueTo.isAfter(LocalDateTime.now());
    }

    public static ScheduledTask createRandomly() {
        Random random = new Random();
        int taskId = random.nextInt(100);
        int createdBy = random.nextInt(100);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dueTo = now.plusMinutes(2);

        return new ScheduledTask(taskId, "TASK-" + taskId, "desc-" + taskId, createdBy, now, dueTo, TaskStatus.CREATED);
    }

}
