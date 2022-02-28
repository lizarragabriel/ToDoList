package com.lizarragabriel.listtodo.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import com.lizarragabriel.listtodo.room.dao.TaskDao;
import com.lizarragabriel.listtodo.room.entity.TaskEntity;
import java.util.List;
import javax.inject.Inject;

public class TaskRepository {

    private TaskDao mTaskDao;

    @Inject
    public TaskRepository(TaskDao mTaskDao) {
        this.mTaskDao = mTaskDao;
    }

    public LiveData<List<TaskEntity>> mGetTasks(int mUserId) {
        return mTaskDao.mGetTasks(mUserId);
    }

    public void mAddTask(TaskEntity mTaskEntity) {
        new InsertNoteAsyncTask(mTaskDao).execute(mTaskEntity);
    }

    public void mUpdateTask(TaskEntity mTaskEntity) {
        new UpdateNoteAsyncTask(mTaskDao).execute(mTaskEntity);
    }

    public void mDeleteOneTask(TaskEntity mTaskEntity) {
        new DeleteNoteAsyncTask(mTaskDao).execute(mTaskEntity);
    }

    public void mDeleteAllTasks(int mUserId) {
        new DeleteAllNotesAsyncTask(mTaskDao).execute(mUserId);
    }

    // AsyncTask for Tasks
    // Add Task
    private static class InsertNoteAsyncTask extends AsyncTask<TaskEntity, Void, Void> {
        private TaskDao taskdao;

        private InsertNoteAsyncTask(TaskDao taskdao) {
            this.taskdao = taskdao;
        }
        @Override
        protected Void doInBackground(TaskEntity... tasks) {
            System.out.println("add in asynk");
            taskdao.mAddTask(tasks[0]);
            return null;
        }
    }

    // Update Task
    private static class UpdateNoteAsyncTask extends AsyncTask<TaskEntity, Void, Void> {
        private TaskDao taskDao;

        private UpdateNoteAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(TaskEntity... tasks) {
            taskDao.mUpdateTask(tasks[0]);
            System.out.println("updated enasynmk");
            return null;
        }
    }

    // Delete One Task
    private static class DeleteNoteAsyncTask extends AsyncTask<TaskEntity, Void, Void> {
        private TaskDao taskDao;

        private DeleteNoteAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(TaskEntity... tasks) {
            taskDao.mDeleteTask(tasks[0]);
            System.out.println("borré en asynk");
            return null;
        }
    }

    // Delete All Tasks
    private static class DeleteAllNotesAsyncTask extends AsyncTask<Integer, Void, Void> {
        private TaskDao taskDao;

        private DeleteAllNotesAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            taskDao.mDeleteAllTask(integers[0]);
            System.out.println("borré todo en asynk");
            return null;
        }
    }
}
