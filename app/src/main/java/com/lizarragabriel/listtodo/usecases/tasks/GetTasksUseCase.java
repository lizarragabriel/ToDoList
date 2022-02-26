package com.lizarragabriel.listtodo.usecases.tasks;

import androidx.lifecycle.LiveData;

import com.lizarragabriel.listtodo.repository.TaskRepository;
import com.lizarragabriel.listtodo.room.entity.TaskEntity;

import java.util.List;

import javax.inject.Inject;

public class GetTasksUseCase {
    private TaskRepository mTaskRepository;

    @Inject
    public GetTasksUseCase(TaskRepository mTaskRepository) {
        this.mTaskRepository = mTaskRepository;
    }

    public LiveData<List<TaskEntity>> mGetTasks(int mUserId) {
        return mTaskRepository.mGetTasks(mUserId);
    }
}
