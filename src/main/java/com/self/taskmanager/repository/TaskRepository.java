package com.self.taskmanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.self.taskmanager.model.Task;
import com.self.taskmanager.model.TaskPriority;
import com.self.taskmanager.model.TaskStatus;
import com.self.taskmanager.model.User;

public interface TaskRepository extends JpaRepository<Task, Long> {
    
    List<Task> findByStatus(TaskStatus status);

    List<Task> findByPriority(TaskPriority priority);

    List<Task> findByUser(User user);

    Optional<Task> findByIdAndUser(Long id, User user);

    List<Task> findByUserAndStatus(User user, TaskStatus status);

    List<Task> findByUserAndPriority(User user, TaskPriority priority);

    Page<Task> findAllByUser(User user, Pageable pageable);

}