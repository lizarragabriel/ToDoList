package com.lizarragabriel.listtodo.viewmodel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.lizarragabriel.listtodo.repository.LocalRepository;
import com.lizarragabriel.listtodo.room.entity.UserEntity;
import com.lizarragabriel.listtodo.sharedpref.SharedPref;

public class MainViewModel extends ViewModel {
    private LocalRepository repository;
    private Context context;
    private int userid = 0;


    public MainViewModel() {

    }

    public void init(Context context) {
        this.context = context;
        repository = new LocalRepository(context);
    }

    public boolean mLogin(String mUserName, String mPassword) {
        try {
            UserEntity mUser = repository.mFindUser(mUserName);
            if(mUser != null) {
                if(mUser.getPassword().equals(mPassword)) {
                    SharedPref mShared = new SharedPref(context);
                    mShared.mSetUserSession("user", mUser.getId());
                    int value = mShared.mGetUserSSession("user");
                    System.out.println("ahora vale " + value);
                    System.out.println("si hay sesion");
                    return true;
                } else {
                    System.out.println("no hay sesion");
                    return false;
                }
            } else {
                System.out.println("no existe");
                return false;
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public void mToastMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
