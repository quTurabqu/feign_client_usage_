package com.practice.feign.model;

import java.time.LocalDateTime;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.practice.feign.model.enumerations.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

// @Builder
// @Setter
@ToString
@Getter
@AllArgsConstructor
public class Task {
    
    protected final Integer id;
    protected String name;
    protected String description;
    protected final Integer createdBy;
    protected final LocalDateTime createdAt;
    protected TaskStatus status;

    @JsonIgnore
    public boolean isInCreatedStatus() {
        return status == TaskStatus.CREATED;
    }

    public boolean isStatus(TaskStatus status) {
        return this.status == status;
    }

    public void statusUp() {
        switch(this.status) {
            case CREATED -> this.status = TaskStatus.PROGRESSING;
            case PROGRESSING -> this.status = TaskStatus.DONE;
            case DONE -> System.out.println("done status can't be status up");
            case INACTIVE -> System.out.println("inactive can't be status up");
            case LOCKED -> System.out.println("locked can't be status up");
        }
    }

    public void statusDown() {
        switch(this.status) {
            case CREATED -> System.out.println("created status can't be status down");
            case PROGRESSING -> this.status = TaskStatus.CREATED;
            case DONE ->  this.status = TaskStatus.CREATED;
            case INACTIVE -> System.out.println("inactive can't be status down");
            case LOCKED -> System.out.println("locked can't be status down");
        }
    }

    public static Task createRandomly() {
        Random random = new Random();
        int taskId = random.nextInt(100);
        int createdBy = random.nextInt(100);
        return new Task(taskId, "TASK-" + taskId, "desc-" + taskId, createdBy, LocalDateTime.now(), TaskStatus.CREATED);
    }

}
