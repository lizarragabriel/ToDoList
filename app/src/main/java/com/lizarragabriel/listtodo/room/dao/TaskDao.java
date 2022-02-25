package com.lizarragabriel.listtodo.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.lizarragabriel.listtodo.room.entity.TaskEntity;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM tasks WHERE userId = :mUserId")
    LiveData<List<TaskEntity>> mGetTasks(int mUserId);

    @Query("DELETE FROM tasks WHERE userId = :mUserId")
    void mDeleteAllTask(int mUserId);

    @Update
    void mUpdateTask(TaskEntity mTask);

    @Delete
    void mDeleteTask(TaskEntity mTask);

    @Insert
    void mAddTask(TaskEntity mTask);
}
