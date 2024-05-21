package com.portfolio.SecureConnect.service;

import com.portfolio.SecureConnect.dto.TaskDto;
import com.portfolio.SecureConnect.model.Task;
import com.portfolio.SecureConnect.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task save(TaskDto taskDto) {
        // 1. Retrieve the user's email
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        // 2. Check if a task with the same name already exists for this user
        List<Task> existingTasks = taskRepository.findByUserEmailAndName(userEmail, taskDto.getName());
        if (!existingTasks.isEmpty()) {
            throw new RuntimeException("Une tâche avec le même nom existe déjà."); // Throw an exception with a user-friendly message (e.g., "A task with the same name already exists.")
        }

        // 3. Create a new task and set the userEmail property
        Task task = new Task(
                taskDto.getName(),
                taskDto.getDescription(),
                taskDto.getStatus(),
                taskDto.getDueDate(),
                taskDto.getPriority(),
                userEmail);

        // 4. Save the task
        return taskRepository.save(task);
    }


    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task updateTask(Long id, TaskDto taskDto) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        existingTask.setName(taskDto.getName());
        existingTask.setDescription(taskDto.getDescription());
        existingTask.setStatus(taskDto.getStatus());
        existingTask.setDueDate(taskDto.getDueDate());
        existingTask.setPriority(taskDto.getPriority());

        return taskRepository.save(existingTask);
    }

}
