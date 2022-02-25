package com.lizarragabriel.listtodo.viewmodel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lizarragabriel.listtodo.repository.LocalRepository;
import com.lizarragabriel.listtodo.room.entity.TaskEntity;
import com.lizarragabriel.listtodo.room.entity.UserEntity;
import com.lizarragabriel.listtodo.sharedpref.SharedPref;

import java.util.List;

public class MainViewModel extends ViewModel {

    private LocalRepository repository;
    private Context context;
    private LiveData<List<TaskEntity>> mTasks;
    private int userid = 0;


    public MainViewModel() {

    }

    public LiveData<List<TaskEntity>> mGetTasks() {
        return mTasks;
    }


    public void init(Context context) {
        this.context = context;
        repository = new LocalRepository(context);
    }

    public void mRecyclerView(int id) {
        mTasks = repository.mGetTasks(id);
    }

    public boolean mLogin(String mUserName, String mPassword) {
        try {
            UserEntity mUser = repository.mFindUser(mUserName);
            if(mUser != null) {
                if(mUser.getPassword().equals(mPassword)) {
                    SharedPref mShared = new SharedPref(context);
                    mShared.mSetUserSession("user", mUser.getId());
                    userid = mUser.getId();
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

    public boolean mSignIn(String mUserName, String mPassword) {
        try {
            UserEntity mUser = repository.mFindUser(mUserName);
            if(mUser == null) {
                UserEntity mNewUser = new UserEntity(mUserName, mPassword);
                repository.mAddUser(mNewUser);
                return true;
            } else {
                System.out.println("ya existe");
                return false;
            }
        }catch (Exception e) {
            System.out.println("hubo un error " + e.getMessage());
            return false;
        }
    }

    public void mToastMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
