package com.self.taskmanager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.self.taskmanager.dto.TaskRequestDto;
import com.self.taskmanager.model.Task;
import com.self.taskmanager.model.TaskPriority;
import com.self.taskmanager.model.TaskStatus;
import com.self.taskmanager.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }
    
    @PostMapping        
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskRequestDto dto) {

        Task createdTask = taskService.createTask(dto);

        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping 
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam(required = false) TaskStatus status , @RequestParam(required = false) TaskPriority priority){
            List<Task> tasks;

            if (status != null) {
                tasks = taskService.getTaskByStatus(status);
            } else if (priority != null) {
                tasks = taskService.getTaskByPriority(priority);
            } else {
                tasks = taskService.getAllTasks();
            }

            return ResponseEntity.ok(tasks);
    }
    
    @GetMapping ("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id){
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id,@Valid @RequestBody TaskRequestDto updatedto){
        Task task = taskService.updateTask(id, updatedto);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Task>> getTasks(Pageable pageable) {

        Page<Task> tasks = taskService.getTask(pageable);

        return ResponseEntity.ok(tasks);
    }

    
    

}    