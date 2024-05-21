package com.portfolio.SecureConnect.controller;

import com.portfolio.SecureConnect.dto.TaskDto;
import com.portfolio.SecureConnect.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.portfolio.SecureConnect.model.Task;
import com.portfolio.SecureConnect.repositories.TaskRepository;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/home")
    public String getHomePage(Model model) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Task> tasks = taskRepository.findByUserEmail(userEmail);
        TaskDto newTask = new TaskDto("", "", "TODO", "", 0, userEmail);
        List<Task> completedTasks = tasks.stream().filter(task -> "DONE".equals(task.getStatus())).collect(Collectors.toList());

        model.addAttribute("newTask", newTask);
        model.addAttribute("tasks", tasks);
        model.addAttribute("completedTasks", completedTasks);
        model.addAttribute("task", new TaskDto("", "", "", "", 0, userEmail));

        return "home";
    }

    @PostMapping("/home")
    public String saveTask(@ModelAttribute("task") TaskDto taskDto, RedirectAttributes redirectAttributes) {
        try {
            taskService.save(taskDto);
            redirectAttributes.addFlashAttribute("message", "La tâche a été créée");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/home";
    }

    @DeleteMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        taskService.deleteTask(id);
        redirectAttributes.addFlashAttribute("message", "La tâche a été supprimée");
        return "redirect:/home";
    }

    @PutMapping("/markAsCompleted/{id}")
    public String markAsCompleted(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Task taskToMark = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        taskToMark.setStatus("DONE");
        taskRepository.save(taskToMark);
        redirectAttributes.addFlashAttribute("message", "La tâche a été marquée comme terminée");
        return "redirect:/home";
    }
}
