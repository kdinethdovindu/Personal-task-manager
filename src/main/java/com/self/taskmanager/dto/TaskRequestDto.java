package com.self.taskmanager.dto;

import com.self.taskmanager.model.TaskPriority;
import com.self.taskmanager.model.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TaskRequestDto {
        @NotBlank(message = "Title is required")
        private String title;

        private String description;

        @NotNull(message = "Status is required")
        private TaskStatus status;

        @NotNull(message = "Priority is required")
        private TaskPriority priority;

    public TaskRequestDto() {
    }
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

}
