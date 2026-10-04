package com.self.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.self.taskmanager.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    

}