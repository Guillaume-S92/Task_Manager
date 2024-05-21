package com.portfolio.SecureConnect.model;


import jakarta.persistence.*;

@Entity
@Table(name = "tasks", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private String status;
    private String dueDate;
    private int priority;

    // Nouveau champ pour stocker l'email de l'utilisateur
    private String userEmail;

    // Constructeur par défaut
    public Task() {
        super();
    }

    // Constructeur avec tous les champs, y compris userEmail
    public Task(String name, String description, String status, String dueDate, int priority, String userEmail) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.priority = priority;
        this.userEmail = userEmail;
    }

    // Getters et setters pour id, name, description, status, dueDate, priority et userEmail
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
