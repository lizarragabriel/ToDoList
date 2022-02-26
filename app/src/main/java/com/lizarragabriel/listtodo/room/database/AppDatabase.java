package com.lizarragabriel.listtodo.room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.lizarragabriel.listtodo.room.dao.TaskDao;
import com.lizarragabriel.listtodo.room.dao.UserDao;
import com.lizarragabriel.listtodo.room.entity.TaskEntity;
import com.lizarragabriel.listtodo.room.entity.UserEntity;

@Database(entities = {UserEntity.class, TaskEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao mTaskDao();
    public abstract UserDao mUserDao();
}

