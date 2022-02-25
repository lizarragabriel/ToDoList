package com.lizarragabriel.listtodo.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lizarragabriel.listtodo.databinding.TaskItemBinding;
import com.lizarragabriel.listtodo.room.entity.TaskEntity;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {
    private List<TaskEntity> mList;

    public TaskAdapter(List<TaskEntity> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public TaskAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(TaskItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.MyViewHolder holder, int position) {
        holder.binding.setTask(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TaskItemBinding binding;
        public MyViewHolder(TaskItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
