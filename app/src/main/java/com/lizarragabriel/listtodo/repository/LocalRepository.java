package com.lizarragabriel.listtodo.repository;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.lizarragabriel.listtodo.room.dao.TaskDao;
import com.lizarragabriel.listtodo.room.dao.UserDao;
import com.lizarragabriel.listtodo.room.database.AppDatabase;
import com.lizarragabriel.listtodo.room.entity.TaskEntity;
import com.lizarragabriel.listtodo.room.entity.UserEntity;

import java.util.List;

public class LocalRepository {
    private TaskDao mTaskdao;
    private UserDao mUserDao;

    public LocalRepository(Context context) {
        AppDatabase mAppDatabase = AppDatabase.getInstance(context);
        mTaskdao = mAppDatabase.mTaskDao();
        mUserDao = mAppDatabase.mUserDao();
    }

    public UserEntity mFindUser(String mUsername) {
        return mUserDao.mFindUser(mUsername);
    }

    public void mAddUser(UserEntity mUserEntity) {
        mUserDao.mAddUser(mUserEntity);
    }

    public LiveData<List<TaskEntity>> mGetTasks(int mUserId) {
        return mTaskdao.mGetTasks(mUserId);
    }

    public void mAddTask(TaskEntity mTaskEntity) {
        mTaskdao.mAddTask(mTaskEntity);
    }







}
