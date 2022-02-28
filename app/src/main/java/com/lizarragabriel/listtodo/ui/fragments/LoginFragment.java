package com.lizarragabriel.listtodo.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lizarragabriel.listtodo.R;
import com.lizarragabriel.listtodo.databinding.FragmentLoginBinding;
import com.lizarragabriel.listtodo.viewmodel.MainViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private MainViewModel mMainViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        binding.mLogIn.setOnClickListener(mLogin -> {
            if(mMainViewModel.mLogin(binding.mUserName.getText().toString(), binding.mPasword.getText().toString())) {
                NavController mNavController = Navigation.findNavController(mLogin);
                mNavController.navigate(R.id.action_loginFragment_to_homeFragment);
            }
        });

        binding.mSignIn.setOnClickListener(mSignin -> {
            NavController mNavController = Navigation.findNavController(mSignin);
            mNavController.navigate(R.id.action_loginFragment_to_signinFragment);
        });
    }
}
