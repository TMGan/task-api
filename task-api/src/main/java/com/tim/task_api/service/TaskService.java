package com.tim.task_api.service;

import com.tim.task_api.model.Task;
import com.tim.task_api.repository.TaskRepository;
import org.springframework.stereotype.Service;

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
}
