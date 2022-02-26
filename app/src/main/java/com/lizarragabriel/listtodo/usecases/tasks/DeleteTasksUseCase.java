package com.lizarragabriel.listtodo.usecases.tasks;

import com.lizarragabriel.listtodo.repository.TaskRepository;
import com.lizarragabriel.listtodo.room.entity.TaskEntity;

import javax.inject.Inject;

public class DeleteTasksUseCase {
    private TaskRepository mTaskRepository;

    @Inject
    public DeleteTasksUseCase(TaskRepository mTaskRepository) {
        this.mTaskRepository = mTaskRepository;
    }

    public void mDeleteOneTask(TaskEntity mTaskEntity) {
        mTaskRepository.mDeleteOneTask(mTaskEntity);
    }

    public void mDeleteAllTasks(int mUserid) {
        mTaskRepository.mDeleteAllTasks(mUserid);
    }
}
