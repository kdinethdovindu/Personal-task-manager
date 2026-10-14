package com.self.taskmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.self.taskmanager.dto.TaskRequestDto;
import com.self.taskmanager.exception.TaskNotFoundException;
import com.self.taskmanager.model.Task;
import com.self.taskmanager.model.TaskPriority;
import com.self.taskmanager.model.TaskStatus;
import com.self.taskmanager.model.User;
import com.self.taskmanager.repository.TaskRepository;
import com.self.taskmanager.repository.UserRepository;

@Service 
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(
            TaskRepository taskRepository,
            UserRepository userRepository) {

        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }
        
    public Task createTask(TaskRequestDto dto){
        User currentUser = getCurrentUser();
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setPriority(dto.getPriority());
        task.setUser(currentUser);
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks(){
        User currentUser = getCurrentUser();
        return taskRepository.findByUser(currentUser);
    }

    public Task getTaskById(Long id){
        User currentUser = getCurrentUser();
        return taskRepository.findByIdAndUser(id, currentUser).orElseThrow(() -> new TaskNotFoundException("Task not Found with id : " + id));
    }

    public Task updateTask(Long id, TaskRequestDto dto){
        User currentUser = getCurrentUser();
        Task existingTask = taskRepository.findByIdAndUser(id, currentUser).orElseThrow(() -> new TaskNotFoundException("Task not Found with id : " + id));
        
            existingTask.setTitle(dto.getTitle());
            existingTask.setDescription(dto.getDescription());
            existingTask.setStatus(dto.getStatus());
            existingTask.setPriority(dto.getPriority());
            return taskRepository.save(existingTask);
       
    }

    public void  deleteTask(Long id){
        User currentUser = getCurrentUser();
        Task existingTask = taskRepository.findByIdAndUser(id, currentUser).orElseThrow(() -> new TaskNotFoundException("Task not Found with id : " + id));
        taskRepository.delete(existingTask);
    }

    public List<Task> getTaskByStatus(TaskStatus status){
        User currentUser = getCurrentUser();
        return taskRepository.findByUserAndStatus(currentUser, status);
    }

    public List<Task> getTaskByPriority(TaskPriority priority){
        User currentUser = getCurrentUser();
        return taskRepository.findByUserAndPriority(currentUser, priority);
    }

    public Page<Task> getTask(Pageable pageable){
        User currentUser = getCurrentUser();
        return taskRepository.findAllByUser(currentUser, pageable);
    }

    private String getCurrentUserEmail(){
            Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }

    private User getCurrentUser() {

        String email = getCurrentUserEmail();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Authenticated user not found"));
    }
}
