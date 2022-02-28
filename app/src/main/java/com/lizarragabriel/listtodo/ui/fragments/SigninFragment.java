package com.lizarragabriel.listtodo.ui.fragments;

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
import com.lizarragabriel.listtodo.databinding.FragmentSigninBinding;
import com.lizarragabriel.listtodo.viewmodel.MainViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SigninFragment extends Fragment {

    private FragmentSigninBinding binding;
    private MainViewModel mMainViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signin, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);

        binding.mToolbar.setNavigationOnClickListener(mClick -> {
            getActivity().onBackPressed();
        });

        binding.mAdd.setOnClickListener(mSign -> {
            if(mMainViewModel.mSignIn(binding.mUserName.getText().toString(), binding.mPasword.getText().toString(), binding.mConfirmPasword.getText().toString())) {
                requireActivity().onBackPressed();
            }
        });
    }
}