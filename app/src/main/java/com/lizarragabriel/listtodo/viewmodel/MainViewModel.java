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

import java.util.Calendar;
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



    public boolean mLogin(String mUserName, String mPassword) {
        try {
            UserEntity mUser = repository.mFindUser(mUserName);
            if(mUser != null) {
                if(mUser.getPassword().equals(mPassword)) {
                    SharedPref mShared = new SharedPref(context);
                    mShared.mSetUserSession("user", mUser.getId());
                    userid = mUser.getId();
                    System.out.println("hola soy " + userid);
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

    public void mRecyclerView(int id) {
        this.userid = id;
        mTasks = repository.mGetTasks(id);
    }

    public boolean mAddTask(String title) {
        try {
            if(!title.isEmpty()) {
                TaskEntity mNewTask = new TaskEntity(userid, title, mDate());
                repository.mAddTask(mNewTask);
                System.out.println("lo agrego");
                return true;
            } else {
                System.out.println("está vacio");
                return false;
            }
        }catch (Exception e) {
            System.out.println("error. " + e.getMessage());
            return false;
        }
    }

    private String mDate() {
        Calendar mCalendar = Calendar.getInstance();
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);
        int month = mCalendar.get(Calendar.MONTH);
        int year = mCalendar.get(Calendar.YEAR);
        month++;
        return day + " " + mGetMonth(month) + " " + year;
    }

    private String mGetMonth(int month) {
        String mNewMonth = "";
        switch (month) {
            case 1:
                mNewMonth = "Enero";
                break;
            case 2:
                mNewMonth = "Febrero";
                break;
            case 3:
                mNewMonth = "Marzo";
                break;
            case 4:
                mNewMonth = "Abril";
                break;
            case 5:
                mNewMonth = "Mayo";
                break;
            case 6:
                mNewMonth = "Junio";
                break;
            case 7:
                mNewMonth = "Julio";
                break;
            case 8:
                mNewMonth = "Agosto";
                break;
            case 9:
                mNewMonth = "Septiembre";
                break;
            case 10:
                mNewMonth = "Octubre";
                break;
            case 11:
                mNewMonth = "Noviembre";
                break;
            case 12:
                mNewMonth = "Diciembre";
                break;
            default:
                mNewMonth = "error";
                break;
        }
        return mNewMonth;
    }

    public void mToastMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
