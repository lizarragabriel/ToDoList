package com.lizarragabriel.listtodo.repository;

import android.os.AsyncTask;

import com.lizarragabriel.listtodo.room.dao.UserDao;
import com.lizarragabriel.listtodo.room.entity.UserEntity;

import javax.inject.Inject;

public class UserRepository {
    private UserDao mUserDao;

    @Inject
    public UserRepository(UserDao mUserDao) {
        this.mUserDao = mUserDao;
    }

    public UserEntity mFindUser(String mUsername) {
        return mUserDao.mFindUser(mUsername);
    }

    public void mAddUser(UserEntity mUserEntity) {
        new AddUserAsyncTask(mUserDao).execute(mUserEntity);
    }

    // AsyncTask for Users
    private static class AddUserAsyncTask extends AsyncTask<UserEntity, Void, Void> {
        private UserDao userDao;

        private AddUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(UserEntity... users) {
            userDao.mAddUser(users[0]);
            return null;
        }
    }
}
