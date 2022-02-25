package com.lizarragabriel.listtodo.room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.lizarragabriel.listtodo.room.dao.TaskDao;
import com.lizarragabriel.listtodo.room.dao.UserDao;
import com.lizarragabriel.listtodo.room.entity.TaskEntity;
import com.lizarragabriel.listtodo.room.entity.UserEntity;

@Database(entities = {UserEntity.class, TaskEntity.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;

    public abstract TaskDao mTaskDao();
    public abstract UserDao mUserDao();

    public static AppDatabase getInstance(Context context) {
        if(appDatabase == null) {
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, "tasks.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return appDatabase;
    }
}

