package com.lizarragabriel.listtodo.repository;

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
        mUserDao.mAddUser(mUserEntity);
    }
}
