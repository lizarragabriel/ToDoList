package com.lizarragabriel.listtodo.viewmodel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.lizarragabriel.listtodo.room.entity.TaskEntity;
import com.lizarragabriel.listtodo.usecases.tasks.AddTaskUseCase;
import com.lizarragabriel.listtodo.usecases.tasks.DeleteTasksUseCase;
import com.lizarragabriel.listtodo.usecases.tasks.GetTasksUseCase;
import com.lizarragabriel.listtodo.usecases.tasks.UpdateTaskUseCase;
import com.lizarragabriel.listtodo.usecases.user.AddUserUseCase;
import com.lizarragabriel.listtodo.usecases.user.LoginUserUseCase;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;

@HiltViewModel
public class MainViewModel extends ViewModel {

    private final AddTaskUseCase mAddTaskUseCase;
    private final GetTasksUseCase mGetTasksUseCase;
    private final LoginUserUseCase mLoginUserUseCase;
    private final AddUserUseCase mAddUserUseCase;
    private final UpdateTaskUseCase mUpdateTaskUseCase;
    private final DeleteTasksUseCase mDeleteTasksUseCase;
    private final Context context;

    private LiveData<List<TaskEntity>> mTasks;
    private int userid = 0;

    @Inject
    public MainViewModel(AddUserUseCase mAddUserUseCase, @ApplicationContext Context context,
                         LoginUserUseCase mLoginUserUseCase, GetTasksUseCase mGetTasksUseCase, AddTaskUseCase mAddTaskUseCase,
                         UpdateTaskUseCase mUpdateTaskUseCase, DeleteTasksUseCase mDeleteTasksUseCase) {
        this.mAddUserUseCase = mAddUserUseCase;
        this.mLoginUserUseCase = mLoginUserUseCase;
        this.mGetTasksUseCase = mGetTasksUseCase;
        this.mAddTaskUseCase = mAddTaskUseCase;
        this.mUpdateTaskUseCase = mUpdateTaskUseCase;
        this.mDeleteTasksUseCase = mDeleteTasksUseCase;
        this.context = context;
    }

    public LiveData<List<TaskEntity>> mGetTasks() {
        return mTasks;
    }

    public boolean mLogin(String mUserName, String mPassword) {
        if(mLoginUserUseCase.mLogin(mUserName, mPassword, context)) {
            mToastMessage("Welcome");
            return true;
        } else {
            mToastMessage("Invalid user");
        }
        return false;
    }

    public boolean mSignIn(String mUserName, String mPassword, String mConfirm) {
        if(mAddUserUseCase.mAddUser(mUserName, mPassword, mConfirm)) {
            mToastMessage("Added successfully");
            return true;
        } else {
            mToastMessage("Invalid username");
        }
        return false;
    }

    public void mRecyclerView(int id) {
        this.userid = id;
        mTasks = mGetTasksUseCase.mGetTasks(id);
    }

    public boolean mAddTask(String title) {
        if(mAddTaskUseCase.mAddTask(title, userid)) {
            mToastMessage("Task Added");
            return true;
        }
        return false;
    }

    public void mUpdateTask(TaskEntity mTaskEntity) {
        mUpdateTaskUseCase.mUpdateTask(mTaskEntity);
        mToastMessage("Task updated");
    }

    public void mDeleteNote(TaskEntity mTaskEntity) {
        mDeleteTasksUseCase.mDeleteOneTask(mTaskEntity);
        mToastMessage("Task deleted");
    }

    public void mDeleteAllNotes() {
        mDeleteTasksUseCase.mDeleteAllTasks(userid);
        mToastMessage("All task deleted");
    }

    public void mToastMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
