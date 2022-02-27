package com.lizarragabriel.listtodo.usecases.tasks;

import com.lizarragabriel.listtodo.repository.TaskRepository;
import com.lizarragabriel.listtodo.room.entity.TaskEntity;

import java.util.Calendar;

import javax.inject.Inject;

public class AddTaskUseCase {
    private TaskRepository mTaskRepository;

    @Inject
    public AddTaskUseCase(TaskRepository mTaskRepository) {
        this.mTaskRepository = mTaskRepository;
    }

    public boolean mAddTask(String mBody, int mUserId) {
        try {
            if(mBody.isEmpty()) {
                return false;
            }
            TaskEntity mNewTask = new TaskEntity(mUserId, mBody, mGetDate());
            mTaskRepository.mAddTask(mNewTask);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private String mGetDate() {
        Calendar mCalendar = Calendar.getInstance();
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);
        int month = mCalendar.get(Calendar.MONTH);
        int year = mCalendar.get(Calendar.YEAR);
        int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = mCalendar.get(Calendar.MINUTE);
        int day_week = mCalendar.get(Calendar.DAY_OF_WEEK);
        month++;
        return mGetDay(day_week) + " " + day + " " + mGetMonth(month) + " " + year + " " + hour + ":" + minute;
    }

    private String mGetDay(int day) {
        String mNewDay = "";
        switch (day) {
            case 1:
                mNewDay = "Sun";
                break;
            case 2:
                mNewDay = "Mon";
                break;
            case 3:
                mNewDay = "Tue";
                break;
            case 4:
                mNewDay = "Wed";
                break;
            case 5:
                mNewDay = "Thu";
                break;
            case 6:
                mNewDay = "Fri";
                break;
            case 7:
                mNewDay = "Sat";
                break;
            default:
                mNewDay = "error";
        }
        return mNewDay;
    }

    private String mGetMonth(int month) {
        String mNewMonth = "";
        switch (month) {
            case 1:
                mNewMonth = "January";
                break;
            case 2:
                mNewMonth = "February";
                break;
            case 3:
                mNewMonth = "March";
                break;
            case 4:
                mNewMonth = "April";
                break;
            case 5:
                mNewMonth = "May";
                break;
            case 6:
                mNewMonth = "June";
                break;
            case 7:
                mNewMonth = "July";
                break;
            case 8:
                mNewMonth = "August";
                break;
            case 9:
                mNewMonth = "September";
                break;
            case 10:
                mNewMonth = "October";
                break;
            case 11:
                mNewMonth = "November";
                break;
            case 12:
                mNewMonth = "December";
                break;
            default:
                mNewMonth = "error";
                break;
        }
        return mNewMonth;
    }
}
