package com.tim.task_api.service;

import com.tim.task_api.model.Task;
import com.tim.task_api.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public Task createTask(Task task) {
    if(task.getTitle()==null || task.getTitle().trim().isEmpty()) {
        throw new IllegalArgumentException("Task title cannot be empty");
    }
    if (task.getStatus()== null) {
        task.setStatus(Task.TaskStatus.PENDING);
    }
    if (task.getPriority() == null) {
        task.setPriority(Task.Priority.MEDIUM);
    }
    return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = getTaskById(id);
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStatus(updatedTask.getStatus());
        existingTask.setPriority(updatedTask.getPriority());
        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long id) {
        Task existingTask = getTaskById(id);
        taskRepository.deleteById(id);

    }
}
