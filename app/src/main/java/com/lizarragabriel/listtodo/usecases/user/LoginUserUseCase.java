package com.lizarragabriel.listtodo.usecases.user;

import android.content.Context;

import com.lizarragabriel.listtodo.repository.UserRepository;
import com.lizarragabriel.listtodo.room.entity.UserEntity;
import com.lizarragabriel.listtodo.sharedpref.SharedPref;

import javax.inject.Inject;

public class LoginUserUseCase {
    private UserRepository mUserRepository;

    @Inject
    public LoginUserUseCase(UserRepository mUserRepository) {
        this.mUserRepository = mUserRepository;
    }

    public boolean mLogin(String mUserName, String mPassword, Context context) {
        try {
            if(mUserName.isEmpty() || mPassword.isEmpty()) {
                return false;
            }
            UserEntity mFindUser = mUserRepository.mFindUser(mUserName);
            if(mFindUser == null) {
                return false;
            }
            SharedPref mShared = new SharedPref(context);
            mShared.mSetUserSession("user", mFindUser.getId());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
