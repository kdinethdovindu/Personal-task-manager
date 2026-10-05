package com.self.taskmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.self.taskmanager.exception.TaskNotFoundException;
import com.self.taskmanager.model.Task;
import com.self.taskmanager.repository.TaskRepository;

@Service 
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }
    
    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id){
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not Found with id : " + id));
    }

    public Task updateTask(Long id, Task updatedTask){
        Task existingTask = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not Found with id : " + id));
        
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setStatus(updatedTask.getStatus());
            existingTask.setPriority(updatedTask.getPriority());
            return taskRepository.save(existingTask);
       
    }

    public boolean deleteTask(Long id){
        if(taskRepository.existsById(id)){
            taskRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
