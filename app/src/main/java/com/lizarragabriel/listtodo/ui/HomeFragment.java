package com.lizarragabriel.listtodo.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lizarragabriel.listtodo.R;
import com.lizarragabriel.listtodo.databinding.FragmentHomeBinding;
import com.lizarragabriel.listtodo.sharedpref.SharedPref;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPref mShared = new SharedPref(getContext());
        int session = mShared.mGetUserSSession("user");
        if(session == 0) {
            NavController mNavController = Navigation.findNavController(view);
            mNavController.navigate(R.id.action_homeFragment_to_loginFragment);
        } else {
            Toast.makeText(getContext(), "Bienvenido", Toast.LENGTH_SHORT).show();
        }
    }
}