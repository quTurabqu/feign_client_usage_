package com.practice.feign.model;

import java.time.LocalDateTime;
import java.util.Random;

import com.practice.feign.model.enumerations.TaskStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScheduledTask extends Task {

    protected LocalDateTime dueTo;

    public ScheduledTask(Integer id, String name, String description, Integer createdBy, LocalDateTime createdAt, LocalDateTime dueTo, TaskStatus status) {
        super(id, name, description, createdBy, createdAt, status);
        if(dueTo.isBefore(createdAt)) {
            throw new IllegalArgumentException("Invalid datetime for dueTo");
        }
        this.dueTo = dueTo;
    }
    
    public boolean notPassedDueTo() {
        return dueTo.isAfter(LocalDateTime.now());
    }

    public static ScheduledTask createRandomly() {
        Random random = new Random();
        int taskId = random.nextInt(100);
        int createdBy = random.nextInt(10);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dueTo = now.plusMinutes(2);

        return new ScheduledTask(taskId, "TASK-" + taskId, "desc-" + taskId, createdBy, now, dueTo, TaskStatus.CREATED);
    }

    @Override
    public String toString() {
        return "Task(id=" + id + 
            ", name=" + name + 
            ", description=" + description +
            ", createdBy=" + createdBy + 
            ", createdAt=" + createdAt + 
            ", dueTo=" + dueTo + 
            ", status=" + status +
        ")";       
    }
    
}
