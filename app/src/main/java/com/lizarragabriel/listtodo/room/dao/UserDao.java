package com.lizarragabriel.listtodo.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.lizarragabriel.listtodo.room.entity.UserEntity;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users WHERE username = :mUserName")
    UserEntity mFindUser(String mUserName);

    @Insert
    void mAddUser(UserEntity mUser);
}
