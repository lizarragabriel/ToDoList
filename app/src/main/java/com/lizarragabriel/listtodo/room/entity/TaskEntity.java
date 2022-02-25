package com.lizarragabriel.listtodo.room.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class TaskEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int userId;
    private String title;
    private String date;

    public TaskEntity(int userId, String title, String date) {
        this.userId = userId;
        this.title = title;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
