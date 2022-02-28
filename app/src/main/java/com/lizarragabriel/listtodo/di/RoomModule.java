package com.lizarragabriel.listtodo.di;

import android.content.Context;
import androidx.room.Room;
import com.lizarragabriel.listtodo.room.dao.TaskDao;
import com.lizarragabriel.listtodo.room.dao.UserDao;
import com.lizarragabriel.listtodo.room.database.AppDatabase;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RoomModule {
    private final String BD_NAME = "todolist.db";

    @Singleton
    @Provides
    AppDatabase mProvidesRoom(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, BD_NAME)
                .allowMainThreadQueries()
                .build();
    }

    @Singleton
    @Provides
    UserDao mProvidesUserDao(AppDatabase appDatabase) {
        return appDatabase.mUserDao();
    }

    @Singleton
    @Provides
    TaskDao mProvidesTaskDao(AppDatabase appDatabase) {
        return appDatabase.mTaskDao();
    }
}
