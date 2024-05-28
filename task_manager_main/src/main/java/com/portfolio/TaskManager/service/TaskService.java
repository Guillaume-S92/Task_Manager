package com.portfolio.TaskManager.service;

import com.portfolio.TaskManager.dto.TaskDto;
import com.portfolio.TaskManager.model.Task;

public interface TaskService {

    Task save (TaskDto taskDto);

    void deleteTask(Long id);

    Task updateTask(Long id, TaskDto taskDto);
}
