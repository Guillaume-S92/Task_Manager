package com.portfolio.SecureConnect.service;

import com.portfolio.SecureConnect.dto.TaskDto;
import com.portfolio.SecureConnect.model.Task;

public interface TaskService {

    Task save (TaskDto taskDto);

    void deleteTask(Long id);

    Task updateTask(Long id, TaskDto taskDto);
}
