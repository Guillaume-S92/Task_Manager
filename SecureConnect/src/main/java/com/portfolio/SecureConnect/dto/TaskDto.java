package com.portfolio.SecureConnect.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TaskDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotNull
    private String status;
    private String dueDate;
    private int priority;
    private String userEmail;


    public TaskDto(String name, String description, String status, String dueDate, int priority, String userEmail) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.priority = priority;
        this.userEmail = userEmail;

    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
