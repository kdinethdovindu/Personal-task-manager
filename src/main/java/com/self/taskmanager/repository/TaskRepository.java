package com.self.taskmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.self.taskmanager.model.Task;
import com.self.taskmanager.model.TaskPriority;
import com.self.taskmanager.model.TaskStatus;

public interface TaskRepository extends JpaRepository<Task, Long> {
    
    List<Task> findByStatus(TaskStatus status);

    List<Task> findByPriority(TaskPriority priority);

}