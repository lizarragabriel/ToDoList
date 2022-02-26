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
            System.out.println("ya la gregué");
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
        month++;
        return day + " " + mGetMonth(month) + " " + year + " " + hour + ":" + minute;
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
}
