package com.self.taskmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.self.taskmanager.dto.TaskRequestDto;
import com.self.taskmanager.exception.TaskNotFoundException;
import com.self.taskmanager.model.Task;
import com.self.taskmanager.model.TaskPriority;
import com.self.taskmanager.model.TaskStatus;
import com.self.taskmanager.repository.TaskRepository;

@Service 
public class TaskService {
    private final TaskRepository taskRepository;

    private final PasswordEncoder passwordEncoder;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }
        
    public Task createTask(TaskRequestDto dto){
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setPriority(dto.getPriority());
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id){
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not Found with id : " + id));
    }

    public Task updateTask(Long id, TaskRequestDto dto){
        Task existingTask = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not Found with id : " + id));
        
            existingTask.setTitle(dto.getTitle());
            existingTask.setDescription(dto.getDescription());
            existingTask.setStatus(dto.getStatus());
            existingTask.setPriority(dto.getPriority());
            return taskRepository.save(existingTask);
       
    }

    public void  deleteTask(Long id){
        Task existingTask = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not Found with id : " + id));
        taskRepository.delete(existingTask);
    }

    public List<Task> getTaskByStatus(TaskStatus status){
        return taskRepository.findByStatus(status);
    }

    public List<Task> getTaskByPriority(TaskPriority priority){
        return taskRepository.findByPriority(priority);
    }

    public Page<Task> getTask(Pageable pageable){
        return taskRepository.findAll(pageable);
    }
}
