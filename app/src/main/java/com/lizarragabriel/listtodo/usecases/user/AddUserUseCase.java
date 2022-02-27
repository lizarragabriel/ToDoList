package com.lizarragabriel.listtodo.usecases.user;

import com.lizarragabriel.listtodo.repository.UserRepository;
import com.lizarragabriel.listtodo.room.entity.UserEntity;

import javax.inject.Inject;

public class AddUserUseCase {
    private UserRepository mUserRepository;

    @Inject
    public AddUserUseCase(UserRepository mUserRepository) {
        this.mUserRepository = mUserRepository;
    }

    public boolean mAddUser(String mUsername, String mPassword, String mConfirm) {
        try {
            if(mUsername.isEmpty() || mPassword.isEmpty() || mConfirm.isEmpty()) {
                return false;
            }
            UserEntity mFindUser = mUserRepository.mFindUser(mUsername);
            if(mFindUser != null) {
                return false;
            }
            if(!mPassword.equals(mConfirm)) {
                return false;
            }
            UserEntity mNewUser = new UserEntity(mUsername, mPassword);
            mUserRepository.mAddUser(mNewUser);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
