package com.lizarragabriel.listtodo.utils;

import androidx.recyclerview.widget.DiffUtil;

import com.lizarragabriel.listtodo.room.entity.TaskEntity;

import java.util.List;

public class MyDiffUtil extends DiffUtil.Callback {
    private List<TaskEntity> oldList;
    private List<TaskEntity> newList;

    public MyDiffUtil(List<TaskEntity> oldList, List<TaskEntity> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}



