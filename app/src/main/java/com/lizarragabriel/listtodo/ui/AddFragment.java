package com.lizarragabriel.listtodo.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
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
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar();
        mMainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);

        binding.mAdd.setOnClickListener(mAdd -> {
            if(mMainViewModel.mAddTask(binding.mTitle.getText().toString())) {
                System.out.println("lo agregaré");
                getActivity().onBackPressed();
            } else {
                System.out.println("noes correcto");
            }
        });
    }

    private void mToolbar() {
        binding.mToolbar.setNavigationOnClickListener(mNav -> {
            getActivity().onBackPressed();
        });
    }
}