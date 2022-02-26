package com.lizarragabriel.listtodo.room.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class TaskEntity implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int userId;
    private String title;
    private String date;

    public TaskEntity(int userId, String title, String date) {
        this.userId = userId;
        this.title = title;
        this.date = date;
    }

    protected TaskEntity(Parcel in) {
        id = in.readInt();
        userId = in.readInt();
        title = in.readString();
        date = in.readString();
    }

    public static final Creator<TaskEntity> CREATOR = new Creator<TaskEntity>() {
        @Override
        public TaskEntity createFromParcel(Parcel in) {
            return new TaskEntity(in);
        }

        @Override
        public TaskEntity[] newArray(int size) {
            return new TaskEntity[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(userId);
        parcel.writeString(title);
        parcel.writeString(date);
    }
}
