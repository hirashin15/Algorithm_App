package com.example.algorithm_app;

import java.util.Date;

public class AlgoModel {
    private int id;
    private Long date;
    private String name;
    private String level;
    private String category;
    private boolean completed;

    public AlgoModel() {
    }

    public AlgoModel(int id, long date, String name, String level, String category, boolean completed) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.level = level;
        this.category = category;
        this.completed = completed;
    }

    // toString to print content to class


    @Override
    public String toString() {
        return "AlgoModel{" +
                "id=" + id +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", category='" + category + '\'' +
                ", completed=" + completed +
                '}';
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
