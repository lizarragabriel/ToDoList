package com.lizarragabriel.listtodo.usecases.tasks;

import com.lizarragabriel.listtodo.repository.TaskRepository;
import com.lizarragabriel.listtodo.room.entity.TaskEntity;

import javax.inject.Inject;

public class UpdateTaskUseCase {
    private TaskRepository mTaskRepository;

    @Inject
    public UpdateTaskUseCase(TaskRepository mTaskRepository) {
        this.mTaskRepository = mTaskRepository;
    }

    public void mUpdateTask(TaskEntity mTaskEntity) {
        mTaskRepository.mUpdateTask(mTaskEntity);
    }
}
