package com.lizarragabriel.listtodo.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.lizarragabriel.listtodo.databinding.FragmentAddBinding;
import com.lizarragabriel.listtodo.viewmodel.MainViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddFragment extends Fragment {

    private FragmentAddBinding binding;
    private MainViewModel mMainViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        mToolbar();
        mMainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
    }

    private void mToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.mToolbar);
        binding.mToolbar.setNavigationOnClickListener(mNav -> {
            requireActivity().onBackPressed();
        });
    }

    private void mAddTask() {
        if(mMainViewModel.mAddTask(binding.mTitle.getText().toString())) {
            getActivity().onBackPressed();
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.add_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mAddSave){
            mAddTask();
        }
        return super.onOptionsItemSelected(item);
    }
}