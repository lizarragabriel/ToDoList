package com.lizarragabriel.listtodo.ui.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.lizarragabriel.listtodo.R;
import com.lizarragabriel.listtodo.databinding.FragmentDetailBinding;
import com.lizarragabriel.listtodo.room.entity.TaskEntity;
import com.lizarragabriel.listtodo.viewmodel.MainViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;
    private TaskEntity mTask;
    private MainViewModel mMainViewModel;
    private AlertDialog.Builder dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTask = DetailFragmentArgs.fromBundle(getArguments()).getTask();
        binding.setTask(mTask);
        mToolbar();
        dialog = new AlertDialog.Builder(getContext());
        mMainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
    }

    private void mToolbar() {
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.mToolbar);
        binding.mToolbar.setNavigationOnClickListener(mNav -> {
            requireActivity().onBackPressed();
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.detail_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mSave){
            mTask.setTitle(binding.mText.getText().toString());
            mMainViewModel.mUpdateTask(mTask);
            getActivity().onBackPressed();
        }
        if(item.getItemId() == R.id.mDetailDelete){
            mShowDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void mShowDialog() {
        dialog
                .setTitle(getString(R.string.delete_text))
                .setMessage(getString(R.string.delete_question))
                .setNegativeButton(getString(R.string.cancel_button), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(getString(R.string.delete_button), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mMainViewModel.mDeleteNote(mTask);
                        getActivity().onBackPressed();
                    }
                });
        dialog.show();
    }
}