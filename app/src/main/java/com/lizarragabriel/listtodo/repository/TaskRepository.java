package com.lizarragabriel.listtodo.repository;

import androidx.lifecycle.LiveData;
import com.lizarragabriel.listtodo.room.dao.TaskDao;
import com.lizarragabriel.listtodo.room.entity.TaskEntity;
import java.util.List;
import javax.inject.Inject;

public class TaskRepository {

    private TaskDao mTaskDao;

    @Inject
    public TaskRepository(TaskDao mTaskDao) {
        this.mTaskDao = mTaskDao;
    }

    public LiveData<List<TaskEntity>> mGetTasks(int mUserId) {
        return mTaskDao.mGetTasks(mUserId);
    }

    public void mAddTask(TaskEntity mTaskEntity) {
        mTaskDao.mAddTask(mTaskEntity);
    }

    public void mUpdateTask(TaskEntity mTaskEntity) {
        mTaskDao.mUpdateTask(mTaskEntity);
    }

    public void mDeleteOneTask(TaskEntity mTaskEntity) {
        mTaskDao.mDeleteTask(mTaskEntity);
    }

    public void mDeleteAllTasks(int mUserId) {
        mTaskDao.mDeleteAllTask(mUserId);
    }
}
